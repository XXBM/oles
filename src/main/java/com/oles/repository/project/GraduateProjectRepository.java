package com.oles.repository.project;

import com.oles.JpaRepository.MyRepository;
import com.oles.domain.project.GraduateProject;
import org.springframework.stereotype.Repository;


@Repository
public interface GraduateProjectRepository extends MyRepository<GraduateProject,Long> {

}
