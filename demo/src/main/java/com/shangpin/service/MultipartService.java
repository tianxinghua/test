package com.shangpin.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Company: www.shangpin.com
 * @Author: Raymond
 * @Date: 2018/11/14 15:03
 * @Description:
 */

public interface MultipartService {
    public String uploadExcel(MultipartFile file);
}
