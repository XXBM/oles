package com.oles.repository;

import com.oles.JpaRepository.MyRepository;
import com.oles.domain.StudentTestDetail;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentTestDetailRepository extends MyRepository<StudentTestDetail,Long> {
    StudentTestDetail findByStudentIdAndTestDetailId(Long studentId, Long testId);
}
