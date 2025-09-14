package lmsprojekat.service.teachingservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.teachingdto.EvaluationInstrumentDTO;
import lmsprojekat.model.File;
import lmsprojekat.model.teaching.EvaluationInstrument;
import lmsprojekat.model.teaching.KnowledgeEvaluation;
import lmsprojekat.repository.FileRepository;
import lmsprojekat.repository.SoftDeleteRepository;
import lmsprojekat.repository.teachingrepo.EvaluationInstrumentRepository;
import lmsprojekat.repository.teachingrepo.KnowledgeEvaluationRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class EvaluationInstrumentService
		extends AbstractCrudService<EvaluationInstrumentDTO, EvaluationInstrument, Long> {

	private final EvaluationInstrumentRepository evaluationInstrumentRepository;
	private final KnowledgeEvaluationRepository knowledgeEvaluationRepository;
	private final FileRepository fileRepository;

	public EvaluationInstrumentService(EvaluationInstrumentRepository evaluationInstrumentRepository,
			KnowledgeEvaluationRepository knowledgeEvaluationRepository, FileRepository fileRepository) {
		this.evaluationInstrumentRepository = evaluationInstrumentRepository;
		this.knowledgeEvaluationRepository = knowledgeEvaluationRepository;
		this.fileRepository = fileRepository;
	}

	@Override
	public SoftDeleteRepository<EvaluationInstrument, Long> getRepository() {
		return evaluationInstrumentRepository;
	}

	@Override
	public EvaluationInstrumentDTO toDTO(EvaluationInstrument entity) {
		EvaluationInstrumentDTO dto = new EvaluationInstrumentDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setEvaluationIds(entity.getEvaluations() != null
				? entity.getEvaluations().stream().map(KnowledgeEvaluation::getId).collect(Collectors.toList())
				: List.of());
		dto.setFileId(entity.getFile() != null ? entity.getFile().getId() : null);
		return dto;
	}

	@Override
	public EvaluationInstrument toEntity(EvaluationInstrumentDTO dto) {
		EvaluationInstrument entity = new EvaluationInstrument();
		entity.setId(dto.getId());
		entity.setName(dto.getName());

		if (dto.getEvaluationIds() != null) {
			List<KnowledgeEvaluation> evaluations = dto.getEvaluationIds().stream()
					.map(id -> knowledgeEvaluationRepository.findById(id)
							.orElseThrow(() -> new IllegalArgumentException("Invalid KnowledgeEvaluation ID: " + id)))
					.collect(Collectors.toList());
			entity.setEvaluations(evaluations);
		}

		if (dto.getFileId() != null) {
			File file = fileRepository.findById(dto.getFileId())
					.orElseThrow(() -> new IllegalArgumentException("Invalid File ID: " + dto.getFileId()));
			entity.setFile(file);
		}

		return entity;
	}

	@Override
	protected void updateEntity(EvaluationInstrument entity, EvaluationInstrumentDTO dto) {
		if (dto.getName() != null) {
			entity.setName(dto.getName());
		}

		if (dto.getEvaluationIds() != null) {
			List<KnowledgeEvaluation> evaluations = dto.getEvaluationIds().stream()
					.map(id -> knowledgeEvaluationRepository.findById(id)
							.orElseThrow(() -> new IllegalArgumentException("Invalid KnowledgeEvaluation ID: " + id)))
					.collect(Collectors.toList());
			entity.setEvaluations(evaluations);
		}

		if (dto.getFileId() != null) {
			File file = fileRepository.findById(dto.getFileId())
					.orElseThrow(() -> new IllegalArgumentException("Invalid File ID: " + dto.getFileId()));
			entity.setFile(file);
		}
	}
}
