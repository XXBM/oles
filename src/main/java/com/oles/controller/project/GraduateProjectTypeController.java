package com.oles.controller.project;

import com.oles.domain.project.GraduateProjectType;
import com.oles.service.project.GraduateProjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GraduateProjectTypeController {
    @Autowired
    GraduateProjectTypeService graduateProjectTypeService;

    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllGraduateProjectTypes", method = RequestMethod.GET)
    public List<GraduateProjectType> findGraduateProjectType()throws Exception {
        List<GraduateProjectType> graduateProjectTypes = graduateProjectTypeService.findAllT();
        return graduateProjectTypes;
    }

}
