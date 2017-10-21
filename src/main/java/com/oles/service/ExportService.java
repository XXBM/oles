package com.oles.service;

import com.oles.domain.TestDetail;
import com.oles.domain.message.Result;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.swing.filechooser.FileSystemView;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.List;

/**
 * Created by kene213 on 2017/4/25.
 */
@Service
public class ExportService {
    final Logger logger = LoggerFactory.getLogger(ExportService.class);
    public Result exportExcel(Long id,List<TestDetail> testDetails) {
        //创建一个workbook， 相当于一个excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        //在workbook中创建一个sheet，相当于excel的sheet
        HSSFSheet sheet = workbook.createSheet("学生成绩信息");
        //在sheet中添加表头第0行
        HSSFRow row = sheet.createRow(0);
        //设置表头单元格居中
        HSSFCellStyle style = workbook.createCellStyle();
        //水平居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //垂直居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);


        //TODO 导出
        //数据
//        for(int i=0;i<testDetails.size();i++){
//            //写入题目
//            List<StudentTestDetail> studentTestDetails = testDetails.get(i).getStudentTestDetails();
//            for(int x=0;x<studentTestDetails.size();x++){
//                //写入某个学生的答案
//
//            }
//        }


        //为每一列设置自动宽度
        for (int i = 0; i < 21; i++) {
            sheet.autoSizeColumn(i);
        }

//        //获取桌面路径
        FileSystemView fsv = FileSystemView.getFileSystemView();

        String url = fsv.getHomeDirectory().getPath();

        //输出文件
        FileOutputStream fileOutputStream = null;

        Result result = new Result();

        String fileName = "", filePath = "";

        //判断系统类型，系统不同，对应路径不同
//        String url = System.getProperty("user.dir") + "/src/main/resources/static";
//        String url1 = System.getProperty("user.dir");
//        int indexOf = url1.indexOf("bin");
//        String url2= url1.substring(0, indexOf);
//        String url = url2 + "webapps/oles" + "/WEB-INF/classes/static";

        url += "/test.xls";

        try {
            fileOutputStream = new FileOutputStream(url);
            //给文件上锁
            FileChannel fileChannel = fileOutputStream.getChannel();
            FileLock fl = fileChannel.tryLock();
            //fl为null时代表上锁失败，文件已被打开
            if (fl == null) {
                result.setMsg("导出失败，请关闭文件后再次操作。");
                return result;
            }
            //上锁成功，继续执行写入
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            result.setMsg("导出成功。");
            result.setSuccess(true);
            return result;
        } catch (FileNotFoundException e) {
            if(e.getMessage().contains("另一个程序正在使用此文件，进程无法访问。")){
                result.setMsg("导出失败，请关闭文件后再次操作。");
            }else{
                result.setMsg("导出失败，请稍后再次操作!");
            }
            logger.debug(e.getMessage());
            return result;
        } catch (IOException e) {
            result.setMsg("导出失败，请再次操作。");
            logger.debug(e.getMessage());
            return result;
        }

    }

}
