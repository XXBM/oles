package com.oles.controller;

import com.oles.domain.Test;
import com.oles.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    TestService testService;

    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllTests", method = RequestMethod.GET)
    public List<Test> findTest()throws Exception {
        List<Test> tests = testService.findAllT();
        return tests;
    }


    //实现分页
    @RequestMapping(value = "/displayAllTests", method = RequestMethod.GET)
    public Map<String, Object> findAllTest(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        Page<Test> list = this.testService.findAllT(new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.testService.findAllT().size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //修改学院信息    完成 改
    @RequestMapping(value = "/updateTest", method = RequestMethod.PUT)
    public Map<String, Object> updateTest(@RequestBody Test test)throws Exception {
        this.testService.update(test);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("test", test);
        return map;
    }

    //添加
    @RequestMapping(value = "/addTest", method = RequestMethod.POST)
    public Map<String, Object> addTest(@RequestBody Test test)throws Exception {
        test.setToConduct(false);
        this.testService.add(test);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("test", test);
        return map;
    }

    //删除一个学院   完成 删
    @RequestMapping(value = "/deleteTest", method = RequestMethod.DELETE)
    public void deleteTest(@RequestParam("id") Long id)throws Exception {
        this.testService.deleteById(id);
    }


    //批量删除   完成 删
    @RequestMapping(value = "/deleteTests",method = RequestMethod.DELETE)
    public void deleteTests(@RequestParam("ids") String ids){
        String deleteIds[] = ids.split(",");
        for(int i = 0; i<deleteIds.length; i++){
            this.testService.deleteById(Long.parseLong(deleteIds[i]));
        }
    }
}
