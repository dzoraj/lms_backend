package lmsprojekat.service.teachingservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.teachingdto.EvaluationInstrumentDTO;
import lmsprojekat.dto.teachingdto.KnowledgeEvaluationDTO;
import lmsprojekat.model.File;
import lmsprojekat.model.teaching.EvaluationInstrument;
import lmsprojekat.model.teaching.KnowledgeEvaluation;
import lmsprojekat.repository.FileRepository;
import lmsprojekat.repository.SoftDeleteRepository;
import lmsprojekat.repository.teachingrepo.EvaluationInstrumentRepository;
import lmsprojekat.repository.teachingrepo.KnowledgeEvaluationRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class EvaluationInstrumentService extends AbstractCrudService<EvaluationInstrumentDTO, EvaluationInstrument, Long> {

    private final EvaluationInstrumentRepository evaluationInstrumentRepository;
    private final KnowledgeEvaluationRepository knowledgeEvaluationRepository;
    private final FileRepository fileRepository;

    public EvaluationInstrumentService(EvaluationInstrumentRepository evaluationInstrumentRepository,
                                       KnowledgeEvaluationRepository knowledgeEvaluationRepository,
                                       FileRepository fileRepository) {
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
        List<KnowledgeEvaluationDTO> evaluationDTOs = null;
        if (entity.getEvaluations() != null) {
            evaluationDTOs = entity.getEvaluations().stream()
                    .map(this::convertKnowledgeEvaluationToDTO)
                    .collect(Collectors.toList());
        }


        File file = entity.getFile();

        return new EvaluationInstrumentDTO(
                entity.getId(),
                entity.getName(),
                evaluationDTOs,
                file
        );
    }

    @Override
    public EvaluationInstrument toEntity(EvaluationInstrumentDTO dto) {
        List<KnowledgeEvaluation> evaluations = null;
        if (dto.getEvaluations() != null) {
            evaluations = dto.getEvaluations().stream()
                    .map(keDto -> knowledgeEvaluationRepository.findById(keDto.getId())
                            .orElseThrow(() -> new IllegalArgumentException("Invalid KnowledgeEvaluation ID: " + keDto.getId())))
                    .collect(Collectors.toList());
        }

        File file = null;
        if (dto.getFile() != null && dto.getFile().getId() != null) {
            file = fileRepository.findById(dto.getFile().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid File ID: " + dto.getFile().getId()));
        }

        EvaluationInstrument entity = new EvaluationInstrument();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEvaluations(evaluations);
        entity.setFile(file);

        return entity;
    }

    @Override
    protected void updateEntity(EvaluationInstrument entity, EvaluationInstrumentDTO dto) {
        entity.setName(dto.getName());

        if (dto.getEvaluations() != null) {
            List<KnowledgeEvaluation> evaluations = dto.getEvaluations().stream()
                    .map(keDto -> knowledgeEvaluationRepository.findById(keDto.getId())
                            .orElseThrow(() -> new IllegalArgumentException("Invalid KnowledgeEvaluation ID: " + keDto.getId())))
                    .collect(Collectors.toList());
            entity.setEvaluations(evaluations);
        } else {
            entity.setEvaluations(null);
        }

        if (dto.getFile() != null && dto.getFile().getId() != null) {
            File file = fileRepository.findById(dto.getFile().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid File ID: " + dto.getFile().getId()));
            entity.setFile(file);
        } else {
            entity.setFile(null);
        }
    }

    private KnowledgeEvaluationDTO convertKnowledgeEvaluationToDTO(KnowledgeEvaluation entity) {
        return new KnowledgeEvaluationDTO(
                entity.getId(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getPoints(),
                null,
                null,
                null,
                null

        );
    }
}
