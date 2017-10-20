package com.oles.repository;

import com.oles.JpaRepository.MyRepository;
import com.oles.domain.Test;
import org.springframework.stereotype.Repository;


@Repository
public interface TestRepository extends MyRepository<Test,Long> {

}
