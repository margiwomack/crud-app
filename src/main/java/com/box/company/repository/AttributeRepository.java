package com.box.company.repository;

import com.box.company.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Owner on 5/17/14.
 */
public interface AttributeRepository extends JpaRepository<Attribute,Long>, JpaSpecificationExecutor<Attribute> {
}

