package com.oles.service;

import com.oles.domain.StudentTestDetail;
import com.oles.domain.Test;
import com.oles.domain.TestDetail;
import com.oles.domain.message.Result;
import com.oles.util.Utils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
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
    public Result exportExcel(Test test, List<TestDetail> testDetails) {
        //创建一个workbook， 相当于一个excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        //在workbook中创建一个sheet，相当于excel的sheet
        HSSFSheet sheet = workbook.createSheet("学生成绩信息");
        //在sheet中添加表头第0行
        HSSFRow row = sheet.createRow(0);
        row.setHeightInPoints(30);//设置行高
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

        //设置列宽
        for(int i=0;i<4;i++){
            sheet.setColumnWidth(i, 10 * 512);
        }

        //第一行
        HSSFCell cell = null;
        cell = row.createCell(0);
        cell.setCellValue(test.getName());
        cell.setCellStyle(style);
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 2);
        sheet.addMergedRegion(region);
        cell = row.createCell(3);
        cell.setCellValue("日期："+Utils.getDate(test.getDateTime()));

        //第二行
        HSSFRow row1 = sheet.createRow(1);
        row1.setHeightInPoints(25);//设置行高
        cell = row1.createCell(0);
        cell.setCellValue("题目");
        cell = row1.createCell(1);
        cell.setCellValue("学生");
        cell = row1.createCell(2);
        cell.setCellValue("自然语言答案");
        cell = row1.createCell(3);
        cell.setCellValue("代码答案");

        //TODO 导出
        //数据
        for(int i=0,x=2;i<testDetails.size() && x < 92;i++,x+=15){
            //写入题目
            HSSFRow newRow = null;
            List<StudentTestDetail> studentTestDetails = testDetails.get(i).getStudentTestDetails();
            for(int y=0;y<studentTestDetails.size();y++){
                //写入某个学生的答案
                newRow = sheet.createRow(x+y);
                cell = newRow.createCell(0);
                cell.setCellValue(testDetails.get(i).getContents());
                cell = newRow.createCell(1);
                cell.setCellValue(studentTestDetails.get(y).getStudent().getName());
                cell = newRow.createCell(2);
                cell.setCellValue(studentTestDetails.get(y).getAnswerWithAlgorithm());
                cell = newRow.createCell(3);
                cell.setCellValue(studentTestDetails.get(y).getAnswerWithCode());
            }
        }


        CellRangeAddress region1 = new CellRangeAddress(2, 16, 0, 0);
        sheet.addMergedRegion(region1);
        CellRangeAddress region2 = new CellRangeAddress(17, 31, 0, 0);
        sheet.addMergedRegion(region2);
        CellRangeAddress region3 = new CellRangeAddress(32, 46, 0, 0);
        sheet.addMergedRegion(region3);
        CellRangeAddress region4 = new CellRangeAddress(47, 61, 0, 0);
        sheet.addMergedRegion(region4);
        CellRangeAddress region5 = new CellRangeAddress(62, 76, 0, 0);
        sheet.addMergedRegion(region5);
        CellRangeAddress region6 = new CellRangeAddress(77, 91, 0, 0);
        sheet.addMergedRegion(region6);

        //获取桌面路径
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
