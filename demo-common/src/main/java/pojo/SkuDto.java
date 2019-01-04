package pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Company: www.shangpin.com
 * @Author: Raymond
 * @Date: 2018/12/25 14:35
 * @Description:
 */
@Getter
@Setter
@ToString
public class SkuDto {
    private String skuId;
    private String channel;
    private Long supplierId;
    private String supplierNo;
    private String supplierSkuNo;
    private Date createTime;
    private Date updateTime;
    private String spuId;
    private Float marketPrice;
    private Float salePrice;
    private String size;
}
