package lmsprojekat.repository.export;

import java.util.List;

import lmsprojekat.model.users.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentExportRepository
        extends org.springframework.data.repository.Repository<Student, Long> {

    @Query(value = """
        SELECT 
          u.id      AS id,
          u.name    AS name,
          u.email   AS email,
          u.jmbg    AS jmbg,
          a.address AS street,
          a.number  AS number,
          a.city    AS city,
          a.country AS country
        FROM student s
          JOIN registered_user ru ON ru.id = s.id
          JOIN users u            ON u.id  = ru.id
          LEFT JOIN address a     ON a.id  = s.address_id
        WHERE (u.deleted IS NULL OR u.deleted = 0)
        """, nativeQuery = true)
    List<StudentExportRow> findAllStudentsFlat();

    @Query(value = """
        SELECT 
          u.id      AS id,
          u.name    AS name,
          u.email   AS email,
          u.jmbg    AS jmbg,
          a.address AS street,
          a.number  AS number,
          a.city    AS city,
          a.country AS country
        FROM student s
          JOIN registered_user ru ON ru.id = s.id
          JOIN users u            ON u.id  = ru.id
          LEFT JOIN address a     ON a.id  = s.address_id
        WHERE (u.deleted IS NULL OR u.deleted = 0)
          AND s.id = :studentId
        """, nativeQuery = true)
    List<StudentExportRow> findStudentFlatById(@Param("studentId") Long studentId);
}
