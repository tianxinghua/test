package com.shangpin.mapper.item;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pojo.SkuDto;
import pojo.SpuDto;

import java.util.List;

/**
 * @Company: www.shangpin.com
 * @Author: Raymond
 * @Date: 2018/12/25 12:00
 * @Description:
 */
@Mapper
@Repository
public interface UpLoadMapper {
    int inserSpu(SpuDto spuDto);
    int inserSku(SkuDto skuDto);
}
