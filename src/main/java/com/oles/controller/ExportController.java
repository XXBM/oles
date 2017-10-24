package com.oles.controller;

import com.oles.domain.Test;
import com.oles.domain.TestDetail;
import com.oles.domain.message.Result;
import com.oles.service.ExportService;
import com.oles.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by hua on 2017/10/20.
 */
@RestController
public class ExportController {

    @Autowired
    ExportService exportService;
    @Autowired
    TestService testService;
    /**
     * 导出excel
     * @param
     */
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public Result exportExcel(@RequestParam("id") Long id,
                              HttpServletResponse response,
                              HttpServletRequest request) throws IOException {
        //1.考试名称+考试时间
        Test test = testService.findOne(id);
        //2.考试题目
        List<TestDetail> testDetails = test.getTestDetail();
        //导出excel
       return this.exportService.exportExcel(test,testDetails,response,request);
    }
}
