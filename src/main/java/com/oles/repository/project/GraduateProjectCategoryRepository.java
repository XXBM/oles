package com.oles.repository.project;

import com.oles.JpaRepository.MyRepository;
import com.oles.domain.project.GraduateProjectCategory;
import org.springframework.stereotype.Repository;


@Repository
public interface GraduateProjectCategoryRepository extends MyRepository<GraduateProjectCategory,Long> {

}
