package lmsprojekat.repository;

import org.springframework.data.jpa.domain.Specification;

import lmsprojekat.model.SoftDeletableEntity;

public class SoftDeleteSpecification {

    @SuppressWarnings("unused")
	public static <T extends SoftDeletableEntity> Specification<T> notDeleted() {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.isFalse(root.get("deleted"));
    }
}
