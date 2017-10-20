package com.oles.controller;

import com.oles.domain.Student;
import com.oles.service.RoleService;
import com.oles.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    RoleService roleService;

    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllStudents", method = RequestMethod.GET)
    public List<Student> findStudent()throws Exception {
        List<Student> students = studentService.findAllT();
        return students;
    }


    //实现分页
    @RequestMapping(value = "/displayAllStudents", method = RequestMethod.GET)
    public Map<String, Object> findAllStudent(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        Page<Student> list = this.studentService.findAllT(new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.studentService.findAllT().size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //修改学院信息    完成 改
    @RequestMapping(value = "/updateStudent", method = RequestMethod.PUT)
    public Map<String, Object> updateStudent(@RequestBody Student student)throws Exception {
        this.studentService.update(student);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("student", student);
        return map;
    }

    //添加
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public Map<String, Object> addStudent(@RequestBody Student student)throws Exception {
        student.setUserName(student.getIdNo());
        student.setPassword(student.getIdNo());
        student.setRole(roleService.findOne((long)2));
        this.studentService.add(student);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("student", student);
        return map;
    }

    //删除一个学院   完成 删
    @RequestMapping(value = "/deleteStudent", method = RequestMethod.DELETE)
    public void deleteStudent(@RequestParam("id") Long id)throws Exception {
        this.studentService.deleteById(id);
    }


    //批量删除   完成 删
    @RequestMapping(value = "/deleteStudents",method = RequestMethod.DELETE)
    public void deleteStudents(@RequestParam("ids") String ids){
        String deleteIds[] = ids.split(",");
        for(int i = 0; i<deleteIds.length; i++){
            this.studentService.deleteById(Long.parseLong(deleteIds[i]));
        }
    }
}
