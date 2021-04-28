package com.bookee.bookee.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bookee.bookee.entity.ExportHead;
import com.bookee.bookee.entity.StatisticsEo;
import com.bookee.bookee.mapper.StatisticsMapper;
import com.bookee.bookee.service.StatisticsService;
import com.bookee.bookee.utils.EasyExcelUtil;
import com.google.common.collect.Lists;
import io.swagger.models.auth.In;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author jinyang
 * @date 2021/4/22 10:01 上午
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/exportTest")
    void exportTest(HttpServletResponse response) throws Exception {
        List<String> typeList = Arrays.asList("JPG","JPEG","PNG","GIF","SVG","BMP","TIF","TIFF","HEIC","MP4","AVI"
                ,"MKV","MGB","RMBV","MOV","M4A","RM","MPG","FLV","M4V","TS","PDF","DOC","DOCX","PPT","PPTX","WPS","PAGES","KEY","TXT","MP3","WAV","XLS","XLSX","CSV","NUMBERS","PSD","PSB","AI","SKETCH","C4D"
                ,"PS","CORELDRAW","TTF","TTC","OTF","ZIP","RAR","7Z","GZ");
        Set<String> typeSet = new HashSet<>(typeList);

        List<String> noTenant = Arrays.asList("t2","t4","t5");

        Set<String> tenantIds = statisticsService.selectAllTenant().stream().collect(Collectors.toSet());
        List<String> ids = tenantIds.stream().filter(t->!noTenant.contains(t)).collect(Collectors.toList());
        //单sheet,单table导出测试
        List<StatisticsEo> mapList = statisticsService.selectAllTypeCount(ids);
        List<StatisticsEo> mapList1 = statisticsService.selectAllTypeCountMore10(ids);

        List<String> tIds = tenantIds.stream().collect(Collectors.toList());
        List<StatisticsEo> mapList2 = statisticsService.selectDetailTenantCount(tIds);
        List<StatisticsEo> mapList3 = statisticsService.selectDetailTenantCountMore10(tIds);

        mapList = mapList.stream().filter(t->
                !ObjectUtils.isEmpty(t)&&typeSet.contains(t.getExtension())
        ).sorted(Comparator.comparing(StatisticsEo::getCount).reversed()).collect(Collectors.toList());

//        EasyExcelUtil.exprotExcel(response,mapList,"导出测试","素材总量明细数据",StatisticsEo.class);

        mapList1 = mapList1.stream().filter(t->
                !ObjectUtils.isEmpty(t)&&typeSet.contains(t.getExtension())
        ).sorted(Comparator.comparing(StatisticsEo::getCount).reversed()).collect(Collectors.toList());


        List<ExportHead> exportHeads =  translateData(mapList2);
        List<ExportHead> exportHeads2 =  translateData(mapList3);

        EasyExcelUtil.writeWithSheetsWeb(response, "导出测试")
                .writeModel(StatisticsEo.class, mapList, "素材总量明细数据")
                .writeModel(StatisticsEo.class, mapList1,"大于10M素材总量明细数据")
                .writeModel(ExportHead.class, exportHeads,"各租户明细数据")
                .writeModel(ExportHead.class, exportHeads2,"大于10M各租户明细数据")
                .finish();
    }

    public List<ExportHead> translateData(List<StatisticsEo> mapList){
        Field field = null;
        Class<?> clazz = ExportHead.class;
        Map<String,List<StatisticsEo>> map = mapList.stream().collect(Collectors.groupingBy(StatisticsEo::getTenant));
        List<ExportHead> exportHeads = new ArrayList<>();
        try {
            for (Map.Entry<String, List<StatisticsEo>> m : map.entrySet()) {
                List<StatisticsEo> list = m.getValue();
                ExportHead exportHead = new ExportHead();
                for (StatisticsEo eo : list) {
                    if(!ObjectUtils.isEmpty(eo.getExtension())){
                        try {
                            String extension = eo.getExtension();
                            if("7Z".equals(extension)){
                                extension = "Z";
                            }
                            field = clazz.getDeclaredField(extension);
                            if(field.getName().equals(extension)){
                                exportHead.setTenant(eo.getTenant());
                                String methodName = "set" + extension.substring(0,1).toUpperCase()+extension.substring(1);
                                Method method =  clazz.getDeclaredMethod(methodName,new Class[]{field.getType()});
                                method.invoke(exportHead,eo.getCount());
                            }
                        } catch (Exception e) {

                        }
                    }
                }
                exportHeads.add(exportHead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exportHeads.stream().sorted(Comparator.comparing(ExportHead::getTenant)).collect(Collectors.toList());
    }

//    public static void main(String[] args) {
//        //182有问题
//        //select count(1),b.type from material_core_attr a left join tbl_asset b on a.asset_id=b.id where b.id is not null group by b.type
//        //select distinct GROUP_CONCAT(right(tenant_id,length(tenant_id)-1)) from tenant_baseinfo where tenant_type in (1,3) and op_status in (1,2) and status=0
//
//        //去掉1
//        //t5 asset 租户单独统计（因为内部的使用场景和外部客户不完全一样）
//        List<Integer> tenantList = Arrays.asList(6,7,8,9,10,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,33,34,35,36,37,38,40,41,42,43,44,45,46,47,48,49,50,51,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,128,129,130,131,132,133,134,135,136,137,138,139,140,142,143,144,145,146,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,170,175,176,177,179,180,181,182,183,184,185,186,188,189,190,191,194,195,196,198,200,201,202,203,206,208,209,210,212,214,215,216,217,218,219,220,178,213,222,223,224,226,227,229,230,232,233,234,235,236,237,241,242,244,248,249,250,251,255,256,257,258,259,260,261,263,265,238,245,246,247,253);
//        StringBuffer startSql = new StringBuffer("select a.extension,count(1) from (select id,extension from t1.material_core_attr where status=0");
//        String formatSql = " union ALL select id,extension from t%s.material_core_attr where status=0";
//        String endSql = ") a group by extension;";
//        for(Integer j:tenantList){
//            startSql.append(String.format(formatSql,j));
//        }
//        startSql.append(endSql);
//        System.out.println(startSql);
//        System.out.println();
//
//        StringBuffer startSql1 = new StringBuffer("select a.extension,count(1) from (select id,extension from t1.material_core_attr where size>10240 and status=0");
//        String formatSql1 = " union ALL select id,extension from t%s.material_core_attr where size>10240 and status=0";
//        for(Integer j:tenantList){
//            startSql1.append(String.format(formatSql1,j));
//        }
//        startSql1.append(endSql);
//        System.out.println(startSql1);
//        System.out.println();
//
//        StringBuffer startSql2 = new StringBuffer("select extension,count(1) count,'t1' tenant from t1.material_core_attr where status=0 group by extension");
//        String formatSql2 = " union ALL select extension,count(1) count,'t%s' tenant from t%s.material_core_attr where status=0 group by extension";
//        for(Integer j:tenantList){
//            startSql2.append(String.format(formatSql2,j,j));
//        }
//        System.out.println(startSql2);
//        System.out.println();
//
//        StringBuffer startSql3 = new StringBuffer("select extension,count(1) count,'t1' tenant from t1.material_core_attr where size>10240 and status=0 group by extension");
//        String formatSql3 = " union ALL select extension,count(1) count,'t%s' tenant from t%s.material_core_attr where size>10240 and status=0 group by extension";
//        for(Integer j:tenantList){
//            startSql3.append(String.format(formatSql3,j,j));
//        }
//        System.out.println(startSql3);
//
//
////        printSql();
//        //printSql10();
//
//        List<StatisticsEo> mapList2 = new ArrayList<>();
//        StatisticsEo statisticsEo = new StatisticsEo();
//        statisticsEo.setExtension("7Z");
//        statisticsEo.setTenant("t1");
//        statisticsEo.setCount(20);
//        mapList2.add(statisticsEo);
//        Field field = null;
//        Class<?> clazz = ExportHead.class;
//        List<ExportHead> exportHeads = new ArrayList<>();
//        try {
//            ExportHead exportHead = new ExportHead();
//            for (StatisticsEo eo : mapList2) {
//                String extension = eo.getExtension();
//                if("7Z".equals(extension)){
//                    extension = "Z";
//                }
//                field = clazz.getDeclaredField(extension);
//                if(field.getName().equals(extension)){
//                    exportHead.setTenant(eo.getTenant());
//                    String methodName = "set" + extension.substring(0,1).toUpperCase()+extension.substring(1);
//                    Method method =  clazz.getDeclaredMethod(methodName,new Class[]{field.getType()});
//                    method.invoke(exportHead,eo.getCount());
//                }
//            }
//            exportHeads.add(exportHead);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void printSql(){
//        String deleteTable = "drop table IF EXISTS statistics;\n";
//
//        StringBuffer startSb = new StringBuffer("CREATE TABLE `statistics` (\n" +
//                "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',").append(System.getProperty("line.separator"));
//        String text = "`%s` bigint(20) DEFAULT NULL COMMENT '类型数量',";
//        String end = "`tenant` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '租户',\n" +
//                "  PRIMARY KEY (`id`)\n" +
//                ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='统计';";
//
//        JSONObject jobj = JSON.parseObject(readJsonFile("/Users/tezign/Downloads/statistics.json"));
//        JSONArray records = jobj.getJSONArray("RECORDS");//构建JSONArray数组
//        LinkedHashSet<String> typeSet = new LinkedHashSet<>(Arrays.asList("JPG","JPEG","PNG","GIF","SVG","BMP","TIF","TIFF","HEIC","MP4","AVI"
//                ,"MKV","MGB","RMBV","MOV","M4A","RM","MPG","FLV","M4V","TS","PDF","DOC","DOCX","PPT","PPTX","WPS","PAGES","KEY","TXT","MP3","WAV","XLS","XLSX","CSV","NUMBERS","PSD","PSB","AI","SKETCH","C4D"
//                ,"PS","CORELDRAW","TTF","TTC","OTF","ZIP","RAR","7Z","GZ"));
//        List<Statistics> list = new ArrayList<>();
//        for (int i = 0 ; i < records.size();i++){
//            JSONObject key = (JSONObject)records.get(i);
//            String extension = ((String)key.get("extension")).toUpperCase();
//            Long count = Long.valueOf((String)key.get("count"));
//            String tenant=((String)key.get("tenant"));
////            typeSet.add(extension);
//            Statistics statistics = new Statistics();
//            statistics.setCount(count);
//            statistics.setTenant(tenant);
//            statistics.setExtension(extension);
//            list.add(statistics);
//        }
//
//        typeSet.forEach(t->{
//            if(StringUtils.isBlank(t)){
//                t = "NULL";
//            }
//            startSb.append(String.format(text,t)).append(System.getProperty("line.separator"));
//        });
//        startSb.append(end).append(System.getProperty("line.separator"));
//
//        Set<String> fields = new HashSet<>();
//
//        List<String> insertSQL = new ArrayList<>();
//        Map<String,List<Statistics>> tenantMap = list.stream().collect(Collectors.groupingBy(Statistics::getTenant));
//        tenantMap.entrySet().forEach(t->{
//            StringBuffer insertSb = new StringBuffer("INSERT INTO `statistics` ( ");
//            String f = "`%s`,";
//            StringBuffer midText = new StringBuffer("`tenant`) VALUES (");
//            String endText = "'%s');";
//            t.getValue().forEach(a->{
//                if(StringUtils.isBlank(a.getExtension())){
//                    a.setExtension("NULL");
//                }
//                String fieldName = String.format(f,a.getExtension());
//                insertSb.append(fieldName);
//                midText.append(a.getCount()).append(",");
//                fields.add(fieldName);
//            });
//            insertSb.append(midText);
//            insertSb.append(String.format(endText,t.getKey())).append(System.getProperty("line.separator"));
//            insertSQL.add(insertSb.toString());
//        });
//
//        System.out.println();
//        fields.forEach(t->{
//            System.out.print(t);
//        });
//
//
//        try {
//            PrintWriter printWriter = new PrintWriter(new File("/Users/tezign/Downloads/脚本.sql"));//替换后输出的文件位置
//            printWriter.write(deleteTable.toCharArray());
//            printWriter.write(startSb.toString().toCharArray());
//            insertSQL.forEach(t-> printWriter.write(t.toString().toCharArray()));
//            printWriter.flush();
//            printWriter.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void printSql10(){
//        String deleteTable = "drop table IF EXISTS statistics10;\n";
//
//        StringBuffer startSb = new StringBuffer("CREATE TABLE `statistics10` (\n" +
//                "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',").append(System.getProperty("line.separator"));
//        String text = "`%s` bigint(20) DEFAULT NULL COMMENT '类型数量',";
//        String end = "`tenant` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '租户',\n" +
//                "  PRIMARY KEY (`id`)\n" +
//                ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='统计';";
//
//        JSONObject jobj = JSON.parseObject(readJsonFile("/Users/tezign/Downloads/statistics10.json"));
//        JSONArray records = jobj.getJSONArray("RECORDS");//构建JSONArray数组
//        Set<String> typeSet = new HashSet<>();
//        List<Statistics> list = new ArrayList<>();
//        for (int i = 0 ; i < records.size();i++){
//            JSONObject key = (JSONObject)records.get(i);
//            String extension = ((String)key.get("extension")).toUpperCase();
//            Long count = Long.valueOf((String)key.get("count"));
//            String tenant=((String)key.get("tenant"));
//            typeSet.add(extension);
//            Statistics statistics = new Statistics();
//            statistics.setCount(count);
//            statistics.setTenant(tenant);
//            statistics.setExtension(extension);
//            list.add(statistics);
//        }
//
//        typeSet.forEach(t->{
//            if(StringUtils.isBlank(t)){
//                t = "NULL";
//            }
//            startSb.append(String.format(text,t)).append(System.getProperty("line.separator"));
//        });
//        startSb.append(end).append(System.getProperty("line.separator"));
//
//        Set<String> fields = new HashSet<>();
//
//        List<String> insertSQL = new ArrayList<>();
//        Map<String,List<Statistics>> tenantMap = list.stream().collect(Collectors.groupingBy(Statistics::getTenant));
//        tenantMap.entrySet().forEach(t->{
//            StringBuffer insertSb = new StringBuffer("INSERT INTO `statistics10` ( ");
//            String f = "`%s`,";
//            StringBuffer midText = new StringBuffer("`tenant`) VALUES (");
//            String endText = "'%s');";
//            t.getValue().forEach(a->{
//                if(StringUtils.isBlank(a.getExtension())){
//                    a.setExtension("NULL");
//                }
//                String fieldName = String.format(f,a.getExtension());
//                insertSb.append(fieldName);
//                midText.append(a.getCount()).append(",");
//                fields.add(fieldName);
//            });
//            insertSb.append(midText);
//            insertSb.append(String.format(endText,t.getKey())).append(System.getProperty("line.separator"));
//            insertSQL.add(insertSb.toString());
//        });
//
//        System.out.println();
//        fields.forEach(t->{
//            System.out.print(t);
//        });
//
//
//        try {
//            PrintWriter printWriter = new PrintWriter(new File("/Users/tezign/Downloads/脚本10.sql"));//替换后输出的文件位置
//            printWriter.write(deleteTable.toCharArray());
//            printWriter.write(startSb.toString().toCharArray());
//            insertSQL.forEach(t-> printWriter.write(t.toString().toCharArray()));
//            printWriter.flush();
//            printWriter.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Data
//    public static class Statistics{
//        private String extension;
//        private Long count;
//        private String tenant;
//    }
//
//    public static String readJsonFile(String fileName) {
//        String jsonStr = "";
//        try {
//            File jsonFile = new File(fileName);
//            FileReader fileReader = new FileReader(jsonFile);
//            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
//            int ch = 0;
//            StringBuffer sb = new StringBuffer();
//            while ((ch = reader.read()) != -1) {
//                sb.append((char) ch);
//            }
//            fileReader.close();
//            reader.close();
//            jsonStr = sb.toString();
//            return jsonStr;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

}
