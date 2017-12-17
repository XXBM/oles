package com.oles.controller.project;

import com.oles.domain.project.GraduateProjectStatus;
import com.oles.service.project.GraduateProjectStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GraduateProjectStatusController {
    @Autowired
    GraduateProjectStatusService graduateProjectStatusService;

    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllGraduateProjectStatuss", method = RequestMethod.GET)
    public List<GraduateProjectStatus> findGraduateProjectStatus()throws Exception {
        List<GraduateProjectStatus> graduateProjectStatuses = graduateProjectStatusService.findAllT();
        return graduateProjectStatuses;
    }

}
