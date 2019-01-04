package com.example.demo;

import com.csvreader.CsvReader;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    private static ResourceBundle rbl = null;
    private String signSplit = ",";
    private static String readPath = "";
    private static String writePath = "";

    static {
        if (rbl == null) {
            rbl = ResourceBundle.getBundle("configValue");
            readPath = rbl.getString("csvFilePath");
            writePath = rbl.getString("csvWritePath");
        }
    }

    @Test
    public List contextLoads() {
        File localFile = new File(readPath);
        System.out.println(localFile.length());
        String sep = "\t";
        try {
            InputStream fi = new FileInputStream(localFile);
            CsvReader csvReader = new CsvReader(fi, Charset.forName("GBK"));
            boolean b = csvReader.readRecord();
            System.out.println(csvReader);
            String rowString = "";
            String[] columns;
            List<List> rowList = new ArrayList<>();
            List columnValueList = new ArrayList<>();
            while (csvReader.readRecord()) {
                rowString = csvReader.getRawRecord();
                //System.out.println(rowString);
                if (!StringUtils.isBlank(rowString)) {
                    columns = rowString.split(",");
                    columnValueList = Arrays.asList(columns);
                    rowList.add(columnValueList);
                }
            }
            System.out.println("行数为:" + rowList.size());
            /*for (Object o:rowList){
                System.out.println(o);
            }*/
            return rowList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void readerAndSaveMethod(List<List> originalList) {
        StringBuffer buffer = new StringBuffer(
                "gender" + signSplit + "brand" + signSplit + "category" + signSplit + "SPU" + signSplit +
                        "productModel" + signSplit + "season" + signSplit + "material" + signSplit + "color" + signSplit +
                        "size" + signSplit + "proName" + signSplit + "国外市场价" + signSplit + "国内市场价" + signSplit +
                        "salePrice" + signSplit + "qty" + signSplit + "made" + signSplit + "desc" + signSplit + "pics" +
                        signSplit + "detailLink" + signSplit + "measurement" + signSplit + "supplierId" + signSplit +
                        "supplierId" + signSplit + "supplierNo" + signSplit + "supplierSkuNo" + signSplit + "channel" + signSplit
        ).append("\r\n");
        for (int i = 0; i < originalList.size(); i++) {
            for (int j = 0; j < originalList.get(i).size(); j++) {
                List indexList = originalList.get(i);
                buffer.append(indexList.get(j)).append(signSplit);
            }
            buffer.append("\r\n");
        }
        File file = new File(writePath);
        try {
            OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(file, false), Charset.forName("GBK"));
            BufferedWriter bw = new BufferedWriter(os);
            bw.write(buffer.toString());
            bw.flush();
            bw.close();
            System.out.println("输出完毕！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DemoApplicationTests demoApplicationTests = new DemoApplicationTests();
        List list = demoApplicationTests.contextLoads();
        demoApplicationTests.readerAndSaveMethod(list);
    }

}
