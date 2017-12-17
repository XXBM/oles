package com.oles.repository.project;

import com.oles.JpaRepository.MyRepository;
import com.oles.domain.project.GraduateProjectStatus;
import org.springframework.stereotype.Repository;


@Repository
public interface GraduateProjectStatusRepository extends MyRepository<GraduateProjectStatus,Long> {

}
