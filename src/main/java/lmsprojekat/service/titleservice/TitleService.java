package lmsprojekat.service.titleservice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.titledto.ScientificFieldDTO;
import lmsprojekat.dto.titledto.TitleDTO;
import lmsprojekat.dto.titledto.TitleTypeDTO;
import lmsprojekat.dto.userdto.TeacherDTO;
import lmsprojekat.model.title.ScientificField;
import lmsprojekat.model.title.Title;
import lmsprojekat.model.title.TitleType;
import lmsprojekat.model.users.Teacher;
import lmsprojekat.repository.SoftDeleteRepository;
import lmsprojekat.repository.titlerepo.ScientificFieldRepository;
import lmsprojekat.repository.titlerepo.TitleRepository;
import lmsprojekat.repository.titlerepo.TitleTypeRepository;
import lmsprojekat.repository.userrepo.TeacherRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class TitleService extends AbstractCrudService<TitleDTO, Title, Long> {

    private final TitleRepository titleRepository;
    private final TeacherRepository teacherRepository;
    private final ScientificFieldRepository scientificFieldRepository;
    private final TitleTypeRepository titleTypeRepository;

    public TitleService(TitleRepository titleRepository,
                        TeacherRepository teacherRepository,
                        ScientificFieldRepository scientificFieldRepository,
                        TitleTypeRepository titleTypeRepository) {
        this.titleRepository = titleRepository;
        this.teacherRepository = teacherRepository;
        this.scientificFieldRepository = scientificFieldRepository;
        this.titleTypeRepository = titleTypeRepository;
    }

    @Override
    protected SoftDeleteRepository<Title, Long> getRepository() {
        return titleRepository;
    }

    @Override
    protected TitleDTO toDTO(Title entity) {
        Teacher teacher = entity.getTeacher();
        TeacherDTO teacherDTO = null;

        if (teacher != null) {
            teacherDTO = new TeacherDTO();
            teacherDTO.setId(teacher.getId());
            teacherDTO.setName(teacher.getName());
            teacherDTO.setBiography(teacher.getBiography());
            teacherDTO.setJmbg(teacher.getJmbg());
        }

        List<ScientificFieldDTO> fieldDTOs = entity.getScientificFields().stream()
                .map(f -> new ScientificFieldDTO(f.getId(), f.getName(), null))
                .collect(Collectors.toList());

        List<TitleTypeDTO> titleTypeDTOs = entity.getTitleTypes().stream()
                .map(t -> new TitleTypeDTO(t.getId(), t.getName(), null))
                .collect(Collectors.toList());

        return new TitleDTO(
                entity.getId(),
                entity.getSelectionDate(),
                entity.getEndDate(),
                teacherDTO,
                fieldDTOs,
                titleTypeDTOs
        );
    }

    @Override
    protected Title toEntity(TitleDTO dto) {
        Teacher teacher = null;
        if (dto.getTeacher() != null && dto.getTeacher().getId() != null) {
            teacher = teacherRepository.findById(dto.getTeacher().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + dto.getTeacher().getId()));
        }

        List<ScientificField> fields = Optional.ofNullable(dto.getScientificFields()).orElse(List.of()).stream()
                .map(f -> scientificFieldRepository.findById(f.getId())
                        .orElseThrow(() -> new EntityNotFoundException("ScientificField not found with id: " + f.getId())))
                .collect(Collectors.toList());

        List<TitleType> titleTypes = Optional.ofNullable(dto.getTitleTypes()).orElse(List.of()).stream()
                .map(t -> titleTypeRepository.findById(t.getId())
                        .orElseThrow(() -> new EntityNotFoundException("TitleType not found with id: " + t.getId())))
                .collect(Collectors.toList());

        return new Title(
                dto.getId(),
                dto.getSelectionDate(),
                dto.getEndDate(),
                teacher,
                fields,
                titleTypes
        );
    }

    @Override
    protected void updateEntity(Title entity, TitleDTO dto) {
        entity.setSelectionDate(dto.getSelectionDate());
        entity.setEndDate(dto.getEndDate());

        if (dto.getTeacher() != null && dto.getTeacher().getId() != null) {
            Teacher teacher = teacherRepository.findById(dto.getTeacher().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + dto.getTeacher().getId()));
            entity.setTeacher(teacher);
        }

        List<ScientificField> fields = Optional.ofNullable(dto.getScientificFields()).orElse(List.of()).stream()
                .map(f -> scientificFieldRepository.findById(f.getId())
                        .orElseThrow(() -> new EntityNotFoundException("ScientificField not found with id: " + f.getId())))
                .collect(Collectors.toList());
        entity.setScientificFields(fields);

        List<TitleType> titleTypes = Optional.ofNullable(dto.getTitleTypes()).orElse(List.of()).stream()
                .map(t -> titleTypeRepository.findById(t.getId())
                        .orElseThrow(() -> new EntityNotFoundException("TitleType not found with id: " + t.getId())))
                .collect(Collectors.toList());
        entity.setTitleTypes(titleTypes);
    }
}
