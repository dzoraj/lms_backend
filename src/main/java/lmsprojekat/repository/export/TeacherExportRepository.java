package lmsprojekat.repository.export;

import java.util.List;

import lmsprojekat.model.users.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherExportRepository
        extends org.springframework.data.repository.Repository<Teacher, Long> {

    // All teachers
    @Query(value = """
        SELECT 
          u.id        AS id,
          u.name      AS name,
          u.email     AS email,
          u.jmbg      AS jmbg,
          t.biography AS biography,
          a.address   AS street,
          a.number    AS number,
          a.city      AS city,
          a.country   AS country
        FROM teacher t
          JOIN registered_user ru ON ru.id = t.id
          JOIN users u            ON u.id  = ru.id
          LEFT JOIN address a     ON a.id  = t.address_id
        WHERE COALESCE(u.deleted, 0) = 0
        """, nativeQuery = true)
    List<TeacherExportRow> findAllTeachersFlat();

    // Single teacher by id
    @Query(value = """
        SELECT 
          u.id        AS id,
          u.name      AS name,
          u.email     AS email,
          u.jmbg      AS jmbg,
          t.biography AS biography,
          a.address   AS street,
          a.number    AS number,
          a.city      AS city,
          a.country   AS country
        FROM teacher t
          JOIN registered_user ru ON ru.id = t.id
          JOIN users u            ON u.id  = ru.id
          LEFT JOIN address a     ON a.id  = t.address_id
        WHERE COALESCE(u.deleted, 0) = 0
          AND u.id = :teacherId
        """, nativeQuery = true)
    List<TeacherExportRow> findTeacherFlatById(@Param("teacherId") Long teacherId);

    // Titles + title types
    @Query(value = """
        SELECT 
          DATE_FORMAT(tt.selection_date, '%Y-%m-%d') AS selectionDate,
          DATE_FORMAT(tt.end_date,   '%Y-%m-%d')     AS endDate,
          tt.teacher_id                               AS teacherId,
          tt.id                                       AS titleId,
          ttype.name                                  AS typeName
        FROM title tt
          LEFT JOIN title_type ttype ON ttype.title_id = tt.id
          JOIN teacher t             ON t.id  = tt.teacher_id
          JOIN registered_user ru    ON ru.id = t.id
          JOIN users u               ON u.id  = ru.id
        WHERE COALESCE(u.deleted, 0) = 0
          AND (tt.deleted = 0 OR tt.deleted IS NULL)
          AND tt.teacher_id IN (:teacherIds)
        """, nativeQuery = true)
    List<TeacherTitleRow> findTitlesForTeachers(@Param("teacherIds") List<Long> teacherIds);
}
