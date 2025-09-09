package lmsprojekat.service.dashboardservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.subjectdto.SubjectDTO;
import lmsprojekat.dto.teachingdto.TeacherOnCourseDTO;
import lmsprojekat.repository.teachingrepo.TeacherOnCourseRepository;
import lmsprojekat.service.subjectservice.SubjectService;
import lmsprojekat.service.teachingservice.TeacherOnCourseService;

@Service
public class TeacherDashboardService {

    private final TeacherOnCourseRepository teacherOnCourseRepository;
    private final TeacherOnCourseService teacherOnCourseService;
    private final SubjectService subjectService;

    public TeacherDashboardService(TeacherOnCourseRepository teacherOnCourseRepository,
                                   TeacherOnCourseService teacherOnCourseService,
                                   SubjectService subjectService) {
        this.teacherOnCourseRepository = teacherOnCourseRepository;
        this.teacherOnCourseService = teacherOnCourseService;
        this.subjectService = subjectService;
    }

    public List<SubjectDTO> getSubjectsForTeacher(Long teacherId) {
        return teacherOnCourseRepository.findSubjectsByTeacherId(teacherId)
                .stream()
                .map(subjectService::toDTO)
                .collect(Collectors.toList());
    }

    public List<TeacherOnCourseDTO> getTeacherCourses(Long teacherId) {
        return teacherOnCourseRepository.findById(teacherId)
                .stream()
                .map(teacherOnCourseService::toDTO)
                .collect(Collectors.toList());
    }


}
