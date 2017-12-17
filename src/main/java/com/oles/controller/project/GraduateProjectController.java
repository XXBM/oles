package com.oles.controller.project;

import com.oles.domain.project.GraduateProject;
import com.oles.service.project.GraduateProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GraduateProjectController {
    @Autowired
    GraduateProjectService graduateProjectService;

    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllGraduateProjects", method = RequestMethod.GET)
    public List<GraduateProject> findGraduateProject()throws Exception {
        List<GraduateProject> tests = graduateProjectService.findAllT();
        return tests;
    }


    //实现分页
    @RequestMapping(value = "/displayAllGraduateProjects", method = RequestMethod.GET)
    public Map<String, Object> findAllGraduateProject(@RequestParam(value = "page") Integer page,
                                                      @RequestParam(value = "rows") Integer size)throws Exception {
        Page<GraduateProject> list = this.graduateProjectService.findAllT(new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.graduateProjectService.findAllT().size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //修改课题信息    完成 改
    @RequestMapping(value = "/updateGraduateProject", method = RequestMethod.PUT)
    public Map<String, Object> updateGraduateProject(@RequestBody GraduateProject graduateProject)throws Exception {
        this.graduateProjectService.update(graduateProject);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("graduateProject", graduateProject);
        return map;
    }

    //添加
    @RequestMapping(value = "/addGraduateProject", method = RequestMethod.POST)
    public Map<String, Object> addGraduateProject(@RequestBody GraduateProject test)throws Exception {
        this.graduateProjectService.add(test);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("test", test);
        return map;
    }

    //删除一个课题   完成 删
    @RequestMapping(value = "/deleteGraduateProject", method = RequestMethod.DELETE)
    public void deleteGraduateProject(@RequestParam("id") Long id)throws Exception {
        this.graduateProjectService.deleteById(id);
    }


    //批量删除   完成 删
    @RequestMapping(value = "/deleteGraduateProjects",method = RequestMethod.DELETE)
    public void deleteGraduateProjects(@RequestParam("ids") String ids){
        String deleteIds[] = ids.split(",");
        for(int i = 0; i<deleteIds.length; i++){
            this.graduateProjectService.deleteById(Long.parseLong(deleteIds[i]));
        }
    }
}
