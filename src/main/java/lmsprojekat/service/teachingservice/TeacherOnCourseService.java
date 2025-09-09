package lmsprojekat.service.teachingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lmsprojekat.dto.teachingdto.TeacherOnCourseDTO;
import lmsprojekat.model.teaching.TeacherOnCourse;
import lmsprojekat.repository.teachingrepo.TeacherOnCourseRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class TeacherOnCourseService extends AbstractCrudService<TeacherOnCourseDTO, TeacherOnCourse, Long> {

    @Autowired
    private TeacherOnCourseRepository repository;

    @Override
    protected TeacherOnCourseRepository getRepository() {
        return repository;
    }

    @Override
    public TeacherOnCourseDTO toDTO(TeacherOnCourse entity) {
        if (entity == null) return null;

        TeacherOnCourseDTO dto = new TeacherOnCourseDTO();
        dto.setId(entity.getId());
        dto.setNumberOfClasses(entity.getNumberOfClasses());
        dto.setTeacher(entity.getTeacher()); // for now just reference, mapping  later
        dto.setTeachingType(entity.getTeachingType());
        dto.setCourseRealization(entity.getCourseRealization());
        dto.setNotifications(entity.getNotifications());

        return dto;
    }

    @Override
	public TeacherOnCourse toEntity(TeacherOnCourseDTO dto) {
        if (dto == null) return null;

        TeacherOnCourse entity = new TeacherOnCourse();
        entity.setId(dto.getId());
        entity.setNumberOfClasses(dto.getNumberOfClasses());
        entity.setTeacher(dto.getTeacher());
        entity.setTeachingType(dto.getTeachingType());
        entity.setCourseRealization(dto.getCourseRealization());
        entity.setNotifications(dto.getNotifications());

        return entity;
    }

    @Override
    protected void updateEntity(TeacherOnCourse entity, TeacherOnCourseDTO dto) {
        if (dto.getNumberOfClasses() != null) {
            entity.setNumberOfClasses(dto.getNumberOfClasses());
        }
        if (dto.getTeacher() != null) {
            entity.setTeacher(dto.getTeacher());
        }
        if (dto.getTeachingType() != null) {
            entity.setTeachingType(dto.getTeachingType());
        }
        if (dto.getCourseRealization() != null) {
            entity.setCourseRealization(dto.getCourseRealization());
        }
        if (dto.getNotifications() != null) {
            entity.setNotifications(dto.getNotifications());
        }
    }
}