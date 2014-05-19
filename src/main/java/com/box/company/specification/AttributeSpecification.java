package com.box.company.specification;

import com.box.company.entity.Attribute;
import com.box.company.entity.Attribute_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Owner on 5/17/14.
 */
public class AttributeSpecification {
    public static Specification<Attribute> nameMatches(final String name) {
        return new Specification<Attribute>() {
            @Override
            public Predicate toPredicate(Root<Attribute> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.equal(root.get(Attribute_.getName()), name);
            }
        };
    }
}
