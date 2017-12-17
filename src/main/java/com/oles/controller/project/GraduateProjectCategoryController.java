package com.oles.controller.project;

import com.oles.domain.project.GraduateProjectCategory;
import com.oles.service.project.GraduateProjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GraduateProjectCategoryController {
    @Autowired
    GraduateProjectCategoryService graduateProjectCategoryService;

    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllGraduateProjectCategorys", method = RequestMethod.GET)
    public List<GraduateProjectCategory> findGraduateProjectCategory()throws Exception {
        List<GraduateProjectCategory> graduateProjectCategories = graduateProjectCategoryService.findAllT();
        return graduateProjectCategories;
    }

}
