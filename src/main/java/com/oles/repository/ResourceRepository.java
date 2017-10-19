package com.oles.repository;


import com.oles.JpaRepository.MyRepository;
import com.oles.domain.Resource;
import org.springframework.stereotype.Repository;


@Repository
public interface ResourceRepository extends MyRepository<Resource,Long> {
}
