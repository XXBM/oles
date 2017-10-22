package com.oles.controller;

import com.oles.domain.Student;
import com.oles.domain.StudentTestDetail;
import com.oles.domain.TestDetail;
import com.oles.domain.User;
import com.oles.service.StudentTestDetailService;
import com.oles.service.TestDetailService;
import com.oles.service.UserService;
import com.oles.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentTestDetailController {
    @Autowired
    StudentTestDetailService studentTestDetailService;
    @Autowired
    TestDetailService testDetailService;
    @Autowired
    UserService userService;

    //生成试题
    @RequestMapping(value = "/generateSubject", method = RequestMethod.GET)
    public List<TestDetail> generateSubject(HttpServletRequest request)throws Exception {
        //TODO 根据IP地址生成A卷B卷
        String ipStr = Utils.getIpAddr(request);
        long ip = Utils.ipToLong("255.248.0.0");
        System.out.println("ip地址是："+ip);
        Specification<TestDetail> testDetailSpecification;
        if(ip%2==0){
             testDetailSpecification = this.testDetailService.query("A");
        }else{
             testDetailSpecification = this.testDetailService.query("B");
        }
        List<TestDetail> testDetails = this.testDetailService.findBySepc(testDetailSpecification);
        return testDetails;
    }

    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllStudentTestDetails", method = RequestMethod.GET)
    public List<StudentTestDetail> findStudentTestDetail(HttpServletRequest request)throws Exception {
        System.out.println("IP地址"+Utils.getIpAddr(request));
        List<StudentTestDetail> studentTestDetails = studentTestDetailService.findAllT();
        return studentTestDetails;
    }


    //实现分页
    @RequestMapping(value = "/displayAllStudentTestDetails", method = RequestMethod.GET)
    public Map<String, Object> findAllStudentTestDetail(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        Page<StudentTestDetail> list = this.studentTestDetailService.findAllT(new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.studentTestDetailService.findAllT().size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //修改学院信息    完成 改
    @RequestMapping(value = "/updateStudentTestDetail", method = RequestMethod.PUT)
    public Map<String, Object> updateStudentTestDetail(@RequestBody StudentTestDetail studentTestDetail)throws Exception {
        this.studentTestDetailService.update(studentTestDetail);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("studentTestDetail", studentTestDetail);
        return map;
    }

    //添加
    @RequestMapping(value = "/addStuTestDetail", method = RequestMethod.POST)
    public Map<String, Object> addStuTestDetail(@RequestBody StudentTestDetail studentTestDetail)throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User storedUser = userService.findByUsername(username);
        //TODO 判断
        StudentTestDetail std = studentTestDetailService.getByStudentIdAndTestDetailId(storedUser.getId(),studentTestDetail.getTestDetail().getId());
        if(std==null){
            studentTestDetail.setStudent((Student)storedUser);
            this.studentTestDetailService.add(studentTestDetail);
        }else {
            studentTestDetail = std;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("studentTestDetail", studentTestDetail);
        return map;
    }

    //添加
    @RequestMapping(value = "/addStudentTestDetail", method = RequestMethod.POST)
    public Map<String, Object> addStudentTestDetail(@RequestBody StudentTestDetail studentTestDetail)throws Exception {
        this.studentTestDetailService.add(studentTestDetail);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("studentTestDetail", studentTestDetail);
        return map;
    }

    //删除一个学院   完成 删
    @RequestMapping(value = "/deleteStudentTestDetail", method = RequestMethod.DELETE)
    public void deleteStudentTestDetail(@RequestParam("id") Long id)throws Exception {
        this.studentTestDetailService.deleteById(id);
    }


    //批量删除   完成 删
    @RequestMapping(value = "/deleteStudentTestDetails",method = RequestMethod.DELETE)
    public void deleteStudentTestDetails(@RequestParam("ids") String ids){
        String deleteIds[] = ids.split(",");
        for(int i = 0; i<deleteIds.length; i++){
            this.studentTestDetailService.deleteById(Long.parseLong(deleteIds[i]));
        }
    }
}
