package com.oles.repository;

import com.oles.JpaRepository.MyRepository;
import com.oles.domain.RoleAssResource;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleAssResourceRepository extends MyRepository<RoleAssResource, Long> {
}