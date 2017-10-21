package com.oles.repository;

import com.oles.JpaRepository.MyRepository;
import com.oles.domain.TestDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TestDetailRepository extends MyRepository<TestDetail,Long> {

    List<TestDetail> findByTestId(Long id);
    Page<TestDetail> findByTestId(Long id, Pageable pageable);

}
