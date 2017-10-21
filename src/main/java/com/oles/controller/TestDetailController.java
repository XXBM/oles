package com.oles.controller;

import com.oles.domain.TestDetail;
import com.oles.service.TestDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestDetailController {
    @Autowired
    TestDetailService testDetailService;

    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllTestDetails", method = RequestMethod.GET)
    public List<TestDetail> findTestDetail()throws Exception {
        List<TestDetail> testDetails = testDetailService.findAllT();
        return testDetails;
    }

    @RequestMapping(value = "/findByTestId", method = RequestMethod.GET)
    public Map<String, Object> findByTestId(@RequestParam("id") Long id,
                                            @RequestParam(value = "page") Integer page,
                                            @RequestParam(value = "rows") Integer size) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Pageable pageable = new PageRequest(page - 1, size);
        Page<TestDetail> list = this.testDetailService.findByTestId(id, pageable);
        int total = this.testDetailService.findByTestId(id).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //实现分页
    @RequestMapping(value = "/displayAllTestDetails", method = RequestMethod.GET)
    public Map<String, Object> findAllTestDetail(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        Page<TestDetail> list = this.testDetailService.findAllT(new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.testDetailService.findAllT().size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //修改学院信息    完成 改
    @RequestMapping(value = "/updateTestDetail", method = RequestMethod.PUT)
    public Map<String, Object> updateTestDetail(@RequestBody TestDetail testDetail)throws Exception {
        this.testDetailService.update(testDetail);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("testDetail", testDetail);
        return map;
    }

    //添加
    @RequestMapping(value = "/addTestDetail", method = RequestMethod.POST)
    public Map<String, Object> addTestDetail(@RequestBody TestDetail testDetail)throws Exception {
        this.testDetailService.add(testDetail);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("testDetail", testDetail);
        return map;
    }

    //删除一个学院   完成 删
    @RequestMapping(value = "/deleteTestDetail", method = RequestMethod.DELETE)
    public void deleteTestDetail(@RequestParam("id") Long id)throws Exception {
        this.testDetailService.deleteById(id);
    }


    //批量删除   完成 删
    @RequestMapping(value = "/deleteTestDetails",method = RequestMethod.DELETE)
    public void deleteTestDetails(@RequestParam("ids") String ids){
        String deleteIds[] = ids.split(",");
        for(int i = 0; i<deleteIds.length; i++){
            this.testDetailService.deleteById(Long.parseLong(deleteIds[i]));
        }
    }
}
