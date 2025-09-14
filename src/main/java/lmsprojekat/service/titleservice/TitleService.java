package lmsprojekat.service.titleservice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.titledto.TitleDTO;
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

	public TitleService(TitleRepository titleRepository, TeacherRepository teacherRepository,
			ScientificFieldRepository scientificFieldRepository, TitleTypeRepository titleTypeRepository) {
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
		return new TitleDTO(entity.getId(), entity.getSelectionDate(), entity.getEndDate(),
				entity.getTeacher() != null ? entity.getTeacher().getId() : null,
				entity.getScientificFields() != null
						? entity.getScientificFields().stream().map(ScientificField::getId).collect(Collectors.toList())
						: List.of(),
				entity.getTitleTypes() != null
						? entity.getTitleTypes().stream().map(TitleType::getId).collect(Collectors.toList())
						: List.of());
	}

	@Override
	protected Title toEntity(TitleDTO dto) {
		Teacher teacher = null;
		if (dto.getTeacherId() != null) {
			teacher = teacherRepository.findById(dto.getTeacherId())
					.orElseThrow(() -> new EntityNotFoundException("Teacher not found id=" + dto.getTeacherId()));
		}

		List<ScientificField> fields = Optional.ofNullable(dto.getScientificFieldIds()).orElse(List.of()).stream()
				.map(id -> scientificFieldRepository.findById(id)
						.orElseThrow(() -> new EntityNotFoundException("ScientificField not found id=" + id)))
				.collect(Collectors.toList());

		List<TitleType> titleTypes = Optional.ofNullable(dto.getTitleTypeIds()).orElse(List.of()).stream()
				.map(id -> titleTypeRepository.findById(id)
						.orElseThrow(() -> new EntityNotFoundException("TitleType not found id=" + id)))
				.collect(Collectors.toList());

		return new Title(dto.getId(), dto.getSelectionDate(), dto.getEndDate(), teacher, fields, titleTypes);
	}

	@Override
	protected void updateEntity(Title entity, TitleDTO dto) {
		if (dto.getSelectionDate() != null) {
			entity.setSelectionDate(dto.getSelectionDate());
		}
		if (dto.getEndDate() != null) {
			entity.setEndDate(dto.getEndDate());
		}

		if (dto.getTeacherId() != null) {
			Teacher teacher = teacherRepository.findById(dto.getTeacherId())
					.orElseThrow(() -> new EntityNotFoundException("Teacher not found id=" + dto.getTeacherId()));
			entity.setTeacher(teacher);
		}

		if (dto.getScientificFieldIds() != null) {
			List<ScientificField> fields = dto.getScientificFieldIds().stream()
					.map(id -> scientificFieldRepository.findById(id)
							.orElseThrow(() -> new EntityNotFoundException("ScientificField not found id=" + id)))
					.collect(Collectors.toList());
			entity.setScientificFields(fields);
		}

		if (dto.getTitleTypeIds() != null) {
			List<TitleType> titleTypes = dto.getTitleTypeIds().stream()
					.map(id -> titleTypeRepository.findById(id)
							.orElseThrow(() -> new EntityNotFoundException("TitleType not found id=" + id)))
					.collect(Collectors.toList());
			entity.setTitleTypes(titleTypes);
		}
	}
}
