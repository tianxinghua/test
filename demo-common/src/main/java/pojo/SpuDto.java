package pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Company: www.shangpin.com
 * @Author: Raymond
 * @Date: 2018/12/25 12:21
 * @Description:
 */
@Getter
@Setter
@ToString
public class SpuDto {
    private String spuId;
    private String gender;
    private String brand;
    private String category;
    private String SPU;
    private String productModel;
    private String season;
    private String material;
    private String color;
    private String proName;
    private Float marketPrice;
    private Float domesticPrice;
    private Float salePrice;
    private String made;
    private String productDesc;
    private String pics;
    private String detailLink;
    private String measurement;
    private Long supplierId;
    private String supplierNo;
    private String channel;
    private Date createTime;
}
