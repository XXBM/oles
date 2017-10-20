package com.oles.repository;

import com.oles.JpaRepository.MyRepository;
import com.oles.domain.TestDetail;
import org.springframework.stereotype.Repository;


@Repository
public interface TestDetailRepository extends MyRepository<TestDetail,Long> {

}
