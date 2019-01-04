package com.shangpin.service.impls;

import com.shangpin.mapper.item.UpLoadMapper;
import com.shangpin.service.MultipartService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pojo.Excelpojo;
import pojo.SkuDto;
import pojo.SpuDto;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @Company: www.shangpin.com
 * @Author: Raymond
 * @Date: 2018/11/14 15:04
 * @Description:
 */
@Service
public class MultipartServiceImpl implements MultipartService {
    @Autowired
    private UpLoadMapper upLoadMapper;
    /*@Autowired
    private ExcelDao excelDao;*/
    @Transactional
    @Override
    public String uploadExcel( MultipartFile file) {
        List<Excelpojo> excelList = null;
        try {
            excelList = getExcelList(file);
        } catch (Exception e) {
            e.printStackTrace();
            return "Excel格式异常！";
        }
        List<SpuDto> spuList=new ArrayList<>();
        List<SkuDto> skuList=new ArrayList<>();
        String spuTemp="";
        int skuCount=1;
        for (Excelpojo excelRow:excelList){
            String nextSPU="";

            if (excelRow.getGender().equalsIgnoreCase("gender")){
                continue;
            }
            nextSPU=excelRow.getSPU();
            if (!(spuTemp.equals(nextSPU))){
                spuTemp=nextSPU;
                skuCount=1;
                SpuDto spuDto = setSpuDto(excelRow);
                spuList.add(spuDto);
                SkuDto skuDto=setSkuDto(excelRow,spuDto.getSpuId(),skuCount);
                skuCount++;
                skuList.add(skuDto);
            }else {
                String spuId = spuList.get(spuList.size() - 1).getSpuId();
                SkuDto skuDto=setSkuDto(excelRow,spuId,skuCount);
                skuCount++;
                skuList.add(skuDto);
            }
        }
        System.out.println("准备导入spu数目:"+spuList.size());
        System.out.println("准备导入sku数目:"+skuList.size());
        //插入spu
        insertSpuDB(spuList);
        insertSkuDB(skuList);
        return "导入成功！";
    }
    @Transactional
    protected void insertSkuDB(List<SkuDto> skuList){
        for (SkuDto skuDto:skuList){
            System.out.println(skuDto);
            int insertSkuResult = upLoadMapper.inserSku(skuDto);
        }
    }
    @Transactional
    protected void insertSpuDB(List<SpuDto> spuList) {
        for (SpuDto spuDto:spuList){
            System.out.println(spuDto);
            int insertSpuResult = upLoadMapper.inserSpu(spuDto);
        }
    }

    private  SkuDto setSkuDto(Excelpojo excelRow,String spuId,int skuCount){
        SkuDto skuDto = new SkuDto();
        skuDto.setSkuId(getId(spuId,skuCount));
        skuDto.setChannel(excelRow.getChannel());
        try {
        skuDto.setMarketPrice(Float.valueOf(excelRow.getForeignPrice()));
        skuDto.setSalePrice(Float.valueOf(excelRow.getSalePrice()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new RuntimeException("数据价格格式错误！");
        }
        skuDto.setSpuId(spuId);
        skuDto.setSupplierId(Long.valueOf(excelRow.getSupplierId()));
        skuDto.setSupplierNo(excelRow.getSupplierNo());
        skuDto.setSize(excelRow.getSize());
        if (StringUtils.isNotBlank(excelRow.getSupplierSkuNo())){
            skuDto.setSupplierSkuNo(excelRow.getSupplierSkuNo());
        }else {
            skuDto.setSupplierSkuNo(excelRow.getSPU()+"_"+excelRow.getSize());
        }
        return skuDto;
    }
    private String getId (String spuId,int skuNum) {
        String skuStr = spuId.toString() + "00"+skuNum;
        return skuStr;
    }
    protected String getId (){
        UUID uuid = UUID.randomUUID();
       /* Long random = (long) (Math.random() * 10000L);
        Long time = System.currentTimeMillis();
        String idstr=random.toString()+time.toString();
        Long id = Long.valueOf(idstr);*/

        return uuid.toString();
    }
    private SpuDto setSpuDto(Excelpojo excelRow){
        SpuDto spuDto = new SpuDto();
        spuDto.setSpuId(getId());
        spuDto.setGender(excelRow.getGender());
        spuDto.setBrand(excelRow.getBrand());
        spuDto.setCategory(excelRow.getCategory());
        spuDto.setSPU(excelRow.getSPU());
        spuDto.setProductModel(excelRow.getProductModel());
        spuDto.setSeason(excelRow.getSeason());
        spuDto.setMaterial(excelRow.getMaterial());
        spuDto.setColor(excelRow.getColor());
        spuDto.setProName(excelRow.getProName());
        try {
            spuDto.setMarketPrice(Float.valueOf(excelRow.getForeignPrice()));
            spuDto.setDomesticPrice(Float.valueOf(excelRow.getForeignPrice()));
            spuDto.setSalePrice(Float.valueOf(excelRow.getSalePrice()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new RuntimeException("数据价格格式错误！");
        }
        spuDto.setMade(excelRow.getMade());
        spuDto.setProductDesc(excelRow.getDesc());
        spuDto.setPics(excelRow.getPics());
        spuDto.setDetailLink(excelRow.getDetailLink());
        spuDto.setMeasurement(excelRow.getMeasurement());
        spuDto.setSupplierId(Long.valueOf(excelRow.getSupplierId()));
        spuDto.setSupplierNo(excelRow.getSupplierNo());
        spuDto.setChannel(excelRow.getChannel());
        return spuDto;
    }


    private List<Excelpojo> getExcelList(MultipartFile file){
        List<Excelpojo> excelList = new ArrayList<>();
        Workbook wb=null;
        try {
            System.out.println(file.getOriginalFilename());
            if (file.getOriginalFilename().contains("xlsx")){
                wb = new XSSFWorkbook(file.getInputStream());
            }else {
                wb = new HSSFWorkbook(file.getInputStream());
            }
            //new POIFSFileSystem(new FileInputStream(file));
            //System.out.println(wb);
            Sheet sheet = wb.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            System.out.println(lastRowNum);

            for (int i = 0; i < lastRowNum; i++) {
                Excelpojo excelpojo = new Excelpojo();
                Class cls=excelpojo.getClass();
                //Map<String, Object> map = new LinkedHashMap<>();
                Row row = sheet.getRow(i);
                System.out.println(row);
                short lastCellNum = row.getLastCellNum();
                System.out.println(lastCellNum);
                for (int w=0;w<excelpojo.getDtoTemplate().length;w++){
                    Cell cell = row.getCell(w);
                    if (cell==null){
                        System.out.println("行："+(i+1)+"列"+(w+1)+"为空");
                        String fieldSetName="set"+excelpojo.getDtoTemplate()[w].toUpperCase().charAt(0)
                                +excelpojo.getDtoTemplate()[w].substring(1);
                        Method setMethod = cls.getDeclaredMethod(fieldSetName,String.class);
                        setMethod.invoke(excelpojo,"");
                    }else {
                        cell.setCellType(CellType.STRING);
                        String fieldSetName="set"+excelpojo.getDtoTemplate()[w].toUpperCase().charAt(0)
                                +excelpojo.getDtoTemplate()[w].substring(1);
                        Method setMethod = cls.getDeclaredMethod(fieldSetName,String.class);
                            setMethod.invoke(excelpojo,cell.toString());
                    }
                }
                System.out.println(excelpojo);
                /*for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell==null){
                        System.out.println("行："+(i+1)+"列"+(j+1)+"为空");
                    }else {
                        CellAddress address = cell.getAddress();
                        System.out.println(address);
                        int columnIndex = cell.getColumnIndex();
                        System.out.println(columnIndex);
                        if (cell != null) {
                            CellStyle cellStyle = cell.getCellStyle();
                            System.out.println(cellStyle);
                            cell.setCellType(CellType.STRING);
                        }*//*else {
                        cell.setCellValue("");
                    }*//*
                        System.out.println(cell);
                    }
                }*/
                excelList.add(excelpojo);
            }
            //excelDao.insertExcelData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return excelList;
    }
}
