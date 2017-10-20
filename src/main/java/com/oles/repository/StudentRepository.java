package com.oles.repository;

import com.oles.JpaRepository.MyRepository;
import com.oles.domain.Student;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends MyRepository<Student,Long> {

}
