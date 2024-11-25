package com.two_weeks_backend.two_weeks_backend.utils.specification;

import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("rawtypes")
@Getter
@Setter
public class Specification<E extends BaseEntity> implements org.springframework.data.jpa.domain.Specification<E> {
    private String query;

    public Specification(String query) {
        this.query = query;
    }

    @Override
    public jakarta.persistence.criteria.Predicate toPredicate(Root<E> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        if (query==null || query.length()==0){
            return cb.and();
        }
        return new Group(this.query, root, cb).getAsJavaxPredicate();
    }
}
