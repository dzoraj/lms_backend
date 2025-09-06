package lmsprojekat.service.teachingservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.teachingdto.TeachingTypeDTO;
import lmsprojekat.model.teaching.TeacherOnCourse;
import lmsprojekat.model.teaching.TeachingSession;
import lmsprojekat.model.teaching.TeachingType;
import lmsprojekat.repository.teachingrepo.TeacherOnCourseRepository;
import lmsprojekat.repository.teachingrepo.TeachingSessionRepository;
import lmsprojekat.repository.teachingrepo.TeachingTypeRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class TeachingTypeService extends AbstractCrudService<TeachingTypeDTO, TeachingType, Long> {

    private final TeachingTypeRepository teachingTypeRepository;
    private final TeacherOnCourseRepository teacherOnCourseRepository;
    private final TeachingSessionRepository teachingSessionRepository;

    public TeachingTypeService(
        TeachingTypeRepository teachingTypeRepository,
        TeacherOnCourseRepository teacherOnCourseRepository,
        TeachingSessionRepository teachingSessionRepository
    ) {
        this.teachingTypeRepository = teachingTypeRepository;
        this.teacherOnCourseRepository = teacherOnCourseRepository;
        this.teachingSessionRepository = teachingSessionRepository;
    }

    @Override
    protected TeachingTypeRepository getRepository() {
        return teachingTypeRepository;
    }

    @Override
    public TeachingTypeDTO toDTO(TeachingType entity) {
        if (entity == null) return null;

        List<Long> teacherOnCourseIds = entity.getCourses() != null
            ? entity.getCourses().stream()
                .map(TeacherOnCourse::getId)
                .collect(Collectors.toList())
            : null;

        List<Long> teachingSessionIds = entity.getTeachingSessions() != null
            ? entity.getTeachingSessions().stream()
                .map(TeachingSession::getId)
                .collect(Collectors.toList())
            : null;

        return new TeachingTypeDTO(
            entity.getId(),
            entity.getName(),
            teacherOnCourseIds,
            teachingSessionIds
        );
    }

    @Override
    public TeachingType toEntity(TeachingTypeDTO dto) {
        if (dto == null) return null;

        TeachingType entity = new TeachingType();
        entity.setId(dto.getId());
        entity.setName(dto.getName());

        if (dto.getTeacherOnCourseIds() != null) {
            List<TeacherOnCourse> courses = dto.getTeacherOnCourseIds().stream()
                .map(id -> teacherOnCourseRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("TeacherOnCourse not found with id: " + id)))
                .collect(Collectors.toList());
            entity.setCourses(courses);
        } else {
            entity.setCourses(null);
        }

        if (dto.getTeachingSessionIds() != null) {
            List<TeachingSession> sessions = dto.getTeachingSessionIds().stream()
                .map(id -> teachingSessionRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("TeachingSession not found with id: " + id)))
                .collect(Collectors.toList());
            entity.setTeachingSessions(sessions);
        } else {
            entity.setTeachingSessions(null);
        }

        return entity;
    }

    @Override
    protected void updateEntity(TeachingType entity, TeachingTypeDTO dto) {
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }

        if (dto.getTeacherOnCourseIds() != null) {
            List<TeacherOnCourse> courses = dto.getTeacherOnCourseIds().stream()
                .map(id -> teacherOnCourseRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("TeacherOnCourse not found with id: " + id)))
                .collect(Collectors.toList());
            entity.setCourses(courses);
        } else {
            entity.setCourses(null);
        }

        if (dto.getTeachingSessionIds() != null) {
            List<TeachingSession> sessions = dto.getTeachingSessionIds().stream()
                .map(id -> teachingSessionRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("TeachingSession not found with id: " + id)))
                .collect(Collectors.toList());
            entity.setTeachingSessions(sessions);
        } else {
            entity.setTeachingSessions(null);
        }
    }
}
