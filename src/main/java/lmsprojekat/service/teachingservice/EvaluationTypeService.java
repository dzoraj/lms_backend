package lmsprojekat.service.teachingservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.teachingdto.EvaluationTypeDTO;
import lmsprojekat.model.teaching.EvaluationType;
import lmsprojekat.repository.SoftDeleteRepository;
import lmsprojekat.repository.teachingrepo.EvaluationTypeRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class EvaluationTypeService extends AbstractCrudService<EvaluationTypeDTO, EvaluationType, Long> {

    private final EvaluationTypeRepository evaluationTypeRepository;

    public EvaluationTypeService(EvaluationTypeRepository evaluationTypeRepository) {
        this.evaluationTypeRepository = evaluationTypeRepository;
    }

    @Override
    protected SoftDeleteRepository<EvaluationType, Long> getRepository() {
        return evaluationTypeRepository;
    }

    @Override
    public EvaluationTypeDTO toDTO(EvaluationType entity) {
        List<Long> evalIds = null;
        if (entity.getEvaluations() != null) {
            evalIds = entity.getEvaluations().stream()
                .map(e -> e.getId())
                .collect(Collectors.toList());
        }
        return new EvaluationTypeDTO(entity.getId(), entity.getName(), evalIds);
    }

    @Override
    public EvaluationType toEntity(EvaluationTypeDTO dto) {
        return new EvaluationType(dto.getId(), dto.getName(), null);
    }

    @Override
    protected void updateEntity(EvaluationType entity, EvaluationTypeDTO dto) {
        entity.setName(dto.getName());
    }
}
