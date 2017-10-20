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
    @RequestMapping(value = "/generateSubject", method = RequestMethod.POST)
    public List<StudentTestDetail> generateSubject()throws Exception {
        //1.获取当前用户
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User storedUser = userService.findByUsername(username);
        //2.找到当前考试的题目(条件：当前考试+试卷类型)
        //TODO 根据IP地址生成A卷B卷
        Specification<TestDetail> testDetailSpecification = this.testDetailService.query("A");
        List<TestDetail> testDetails = this.testDetailService.findBySepc(testDetailSpecification);
        //3.开始生成考试题目
        for(int i=0;i<testDetails.size();i++){
            StudentTestDetail studentTestDetail = new StudentTestDetail((Student)storedUser,testDetails.get(i));
            studentTestDetailService.add(studentTestDetail);
        }
        //4.查当前StudentTestDetail
        Specification<StudentTestDetail> studentTestDetailSpecification = this.studentTestDetailService.query(storedUser.getId());
        List<StudentTestDetail> studentTestDetail = this.studentTestDetailService.findBySepc(studentTestDetailSpecification);
        return studentTestDetail;
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
