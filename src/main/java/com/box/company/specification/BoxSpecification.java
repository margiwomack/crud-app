package com.box.company.specification;

import com.box.company.entity.Box;
import com.box.company.entity.Box_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Owner on 5/17/14.
 */
public class BoxSpecification {
    public static Specification<Box> nameMatches(final String name) {
        return new Specification<Box>() {
            @Override
            public Predicate toPredicate(Root<Box> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.equal(root.get(Box_.getName()), name);
            }
        };
    }

}
