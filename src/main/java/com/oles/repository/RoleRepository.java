package com.oles.repository;

import com.oles.JpaRepository.MyRepository;
import com.oles.domain.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MyRepository<Role,Long> {

}
