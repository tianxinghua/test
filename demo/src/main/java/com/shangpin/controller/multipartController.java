package com.shangpin.controller;

import com.shangpin.service.MultipartService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pojo.ResultResp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Company: www.shangpin.com
 * @Author: Raymond
 * @Date: 2018/11/14 11:52
 * @Description:
 */
@RestController
@RequestMapping("/read/")
public class multipartController {
    @Autowired
    private MultipartService multipartService;

    @RequestMapping(value = "uploadExcel",method =RequestMethod.POST)
    public ResultResp uploadExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "filename") MultipartFile file) {
        String result= "none";
        try {
            result = multipartService.uploadExcel(file);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultResp().setEerrrResp(e.getMessage());
        }
        return new ResultResp().setSuccessResp(result);
    }
}
