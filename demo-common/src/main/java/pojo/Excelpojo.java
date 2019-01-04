package pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @Company: www.shangpin.com
 * @Author: Raymond
 * @Date: 2018/11/14 15:57
 * @Description:
 */
@Getter
@Setter
@ToString
public class Excelpojo {
    private String gender;

    /*@Override
    public String toString() {
        return "Excelpojo{" +
                "gender='" + gender + '\'' +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                ", SPU=" + SPU +
                ", productModel=" + productModel +
                ", season='" + season + '\'' +
                ", material='" + material + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", proName='" + proName + '\'' +
                ", foreignPrice='" + foreignPrice + '\'' +
                ", domesticPrice='" + domesticPrice + '\'' +
                ", qty='" + qty + '\'' +
                ", made='" + made + '\'' +
                ", desc='" + desc + '\'' +
                ", pics='" + pics + '\'' +
                ", measurement='" + measurement + '\'' +
                ", supplierId=" + supplierId +
                ", supplierNo='" + supplierNo + '\'' +
                ", supplierSkuNo='" + supplierSkuNo + '\'' +
                ", channel='" + channel + '\'' +
                ", createTime=" + createTime +
                '}';
    }*/

    private String brand;
    private String category;
    private String SPU;
    private String productModel;
    private String season;
    private String material;
    private String color;
    private String size;
    private String proName;
    private String foreignPrice;
    private String domesticPrice;



    private String salePrice;
    private String qty;
    private String made;
    private String desc;
    private String pics;

    public String getDetailLink() {
        return detailLink;
    }

    public void setDetailLink(String detailLink) {
        this.detailLink = detailLink;
    }

    private String detailLink;
    private String measurement;
    private String supplierId;
    private String supplierNo;
    private String supplierSkuNo;
    private String channel;


    public String[] getDtoTemplate(){
        String[] dtoTemplate={"gender","brand","category","SPU","productModel","season"
                ,"material","color","size","proName","foreignPrice","domesticPrice","salePrice"
                ,"qty","made","desc","pics","detailLink","measurement","supplierId","supplierNo","supplierSkuNo"
                ,"channel"};
        return dtoTemplate;
    }
}
