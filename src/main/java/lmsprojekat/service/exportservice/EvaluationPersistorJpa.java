package lmsprojekat.service.exportservice;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import lmsprojekat.dto.exportdto.ImportResult;
import lmsprojekat.export.xml.EvaluationAttemptXml;
import lmsprojekat.export.xml.EvaluationXml;

@Service
public class EvaluationPersistorJpa {

    private final EntityManager em;
    public enum ImportMode { MERGE, REPLACE }

    public EvaluationPersistorJpa(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public ImportResult persist(EvaluationXml xml, ImportMode mode) {
        Long evalId = xml.getId();
        if (!evaluationExists(evalId)) throw new IllegalArgumentException("Evaluation not found: " + evalId);

        if (mode == ImportMode.REPLACE) {
            em.createNativeQuery("delete from evaluation_attempt where evaluation_id = :e")
              .setParameter("e", evalId)
              .executeUpdate();
        }

        Map<Long, Long> existingBySiy = loadExistingAttemptIds(evalId);
        int total = 0, inserted = 0, updated = 0, skipped = 0;
        Set<Long> latestSiyIds = new HashSet<>();

        if (xml.getAttempts() != null) {
            for (EvaluationAttemptXml a : xml.getAttempts()) {
                total++;
                Long siyId = resolveStudentInYearId(a);
                if (siyId == null) { skipped++; continue; }
                if (Boolean.TRUE.equals(a.getLatest())) latestSiyIds.add(siyId);

                if (mode == ImportMode.MERGE && existingBySiy.containsKey(siyId)) {
                    Long attemptId = existingBySiy.get(siyId);
                    int n = em.createNativeQuery(
                                "update evaluation_attempt set points=:p, is_latest=:l, note=:n where id=:id")
                            .setParameter("p", a.getPoints())
                            .setParameter("l", toBit(a.getLatest()))
                            .setParameter("n", safe(a.getNote()))
                            .setParameter("id", attemptId)
                            .executeUpdate();
                    if (n > 0) updated++;
                } else {
                    em.createNativeQuery(
                            "insert into evaluation_attempt (evaluation_id, student_in_year_id, points, is_latest, note, deleted) " +
                            "values (:e,:siy,:p,:l,:n,0)")
                        .setParameter("e", evalId)
                        .setParameter("siy", siyId)
                        .setParameter("p", a.getPoints())
                        .setParameter("l", toBit(a.getLatest()))
                        .setParameter("n", safe(a.getNote()))
                        .executeUpdate();
                    inserted++;
                }
            }
        }

        if (!latestSiyIds.isEmpty()) {
            em.createNativeQuery("update evaluation_attempt set is_latest=0 where evaluation_id=:e")
              .setParameter("e", evalId)
              .executeUpdate();
            for (Long siy : latestSiyIds) {
                em.createNativeQuery(
                        "update evaluation_attempt set is_latest=1 where evaluation_id=:e and student_in_year_id=:siy")
                  .setParameter("e", evalId)
                  .setParameter("siy", siy)
                  .executeUpdate();
            }
        }

        if (inserted == 0 && updated == 0) {
            throw new IllegalArgumentException("No attempts imported: could not resolve student_in_year for provided XML rows. Provide student_in_year.id or indexNumber (and optionally email).");
        }

        return new ImportResult(evalId, total, inserted, updated, skipped, mode == ImportMode.REPLACE);
    }

    private boolean evaluationExists(Long id) {
        Number n = (Number) em.createNativeQuery("select count(1) from knowledge_evaluation where id=:id")
            .setParameter("id", id)
            .getSingleResult();
        return n.intValue() > 0;
    }

    @SuppressWarnings("unchecked")
    private Map<Long, Long> loadExistingAttemptIds(Long evalId) {
        List<Object[]> rows = em.createNativeQuery(
                "select id, student_in_year_id from evaluation_attempt where evaluation_id=:e")
            .setParameter("e", evalId)
            .getResultList();
        Map<Long, Long> map = new HashMap<>();
        for (Object[] r : rows) {
            Long id = ((Number) r[0]).longValue();
            Long siy = ((Number) r[1]).longValue();
            map.put(siy, id);
        }
        return map;
    }

    private int toBit(Boolean b) { return (b != null && b) ? 1 : 0; }
    private String safe(String s) { return (s == null) ? "" : s; }

    private Long resolveStudentInYearId(EvaluationAttemptXml a) {
        if (a == null || a.getStudent() == null) return null;

        Long providedId = a.getStudent().getId();
        String indexNo = a.getStudent().getIndexNumber();
        String email = a.getStudent().getEmail();

        if (providedId != null && existsById("student_in_year", providedId)) return providedId;

        if (providedId != null && existsById("student", providedId)) {
            Long viaIdx = findSiyByStudentAndIndexOrOnly(providedId, indexNo);
            if (viaIdx != null) return viaIdx;
        }

        if (indexNo != null && !indexNo.isBlank()) {
            Long byIndexOnly = findSiyByIndex(indexNo);
            if (byIndexOnly != null) return byIndexOnly;
        }

        if (email != null && !email.isBlank()) {
            Long studentId = findStudentIdByEmail(email);
            if (studentId != null) {
                Long viaIdx = findSiyByStudentAndIndexOrOnly(studentId, indexNo);
                if (viaIdx != null) return viaIdx;
            }
        }

        return null;
    }

    private boolean existsById(String table, Long id) {
        Number n = (Number) em.createNativeQuery("select count(1) from " + table + " where id=:id")
                .setParameter("id", id)
                .getSingleResult();
        return n.intValue() > 0;
    }

    @SuppressWarnings("unchecked")
    private Long findSiyByIndex(String indexNo) {
        List<Number> ids = em.createNativeQuery(
                "select id from student_in_year where index_number=:idx")
            .setParameter("idx", indexNo)
            .getResultList();
        return ids.size() == 1 ? ids.get(0).longValue() : null;
    }

    @SuppressWarnings("unchecked")
    private Long findStudentIdByEmail(String email) {
        List<Number> ids = em.createNativeQuery(
                "select s.id from student s join registered_user ru on s.id=ru.id join users u on ru.id=u.id where u.email=:e")
            .setParameter("e", email)
            .getResultList();
        return ids.size() == 1 ? ids.get(0).longValue() : null;
    }

    private Long findSiyByStudentAndIndexOrOnly(Long studentId, String indexNo) {
        if (indexNo != null && !indexNo.isBlank()) {
            @SuppressWarnings("unchecked")
            List<Number> ids = em.createNativeQuery(
                    "select id from student_in_year where student_id=:sid and index_number=:idx")
                .setParameter("sid", studentId)
                .setParameter("idx", indexNo)
                .getResultList();
            return ids.isEmpty() ? null : ids.get(0).longValue();
        } else {
            @SuppressWarnings("unchecked")
            List<Number> ids = em.createNativeQuery(
                    "select id from student_in_year where student_id=:sid")
                .setParameter("sid", studentId)
                .getResultList();
            return ids.size() == 1 ? ids.get(0).longValue() : null;
        }
    }
}
