package com.box.company.repository;

import com.box.company.entity.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Owner on 5/15/14.
 */
public interface BoxRepository extends JpaRepository<Box,Long>, JpaSpecificationExecutor<Box> {
}
