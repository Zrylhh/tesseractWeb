package cn.zrylhh.tesseractWeb.utils;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

/**
 * 项目名：TesseractWeb.
 * 文件名：
 * 模块说明：
 * 修改历史：
 * 2021-08-04 - ZhaoLongTao - 创建.
 */
public class JsonProcess {


    public static void main(String[] args) throws IOException {
        // unfreezePlan
        // 从文件中读取字符串，格式化成json
        // truncate table ads_xskb_unfreezeplan_td;
        // insert into ads_xskb_unfreezeplan_td(processdate,storegid,gdgid,qty,tenant,avglast4weeksale,rec_modified,qpc) values
//        String unfreezePlan = FileUtils.readFileToString(new File("E:\\projects\\TesseractWeb\\src\\main\\resources\\stastic\\mkhtest\\ads_xskb_unfreezeplan_td.json"));
//        JSONArray unfreezePlanArr = new JSONArray(unfreezePlan);
//        StringBuilder unfreezePlanValues = new StringBuilder();
//        for (Object o : unfreezePlanArr) {
//            JSONObject tmp = (JSONObject)o;
//            unfreezePlanValues.append("(");
//            unfreezePlanValues.append("'" + tmp.getString("processdate") + "',");
//            unfreezePlanValues.append("'" + tmp.getString("storegid") + "',");
//            unfreezePlanValues.append("'" + tmp.getString("gdgid") + "',");
//            unfreezePlanValues.append(tmp.getInt("qty") + ",");
//            unfreezePlanValues.append("'" + tmp.getString("tenant") + "',");
//            unfreezePlanValues.append(tmp.getInt("avglast4weeksale") + ",");
//            unfreezePlanValues.append("'" + tmp.getString("rec_modified") + "',");
//            unfreezePlanValues.append("'" + tmp.getString("qpc") + "'");
//            unfreezePlanValues.append("),\n");
//        }
//        System.out.println(unfreezePlanValues.toString());


        //truncate table ads_xskb_storerefinfo;
        //insert into ads_xskb_storerefinfo(rec_modified,tenant,storegid ,date ,weekday ,workday ,phenomenon ,temperature) values
//        String storeInfo = FileUtils.readFileToString(new File("E:\\projects\\TesseractWeb\\src\\main\\resources\\stastic\\mkhtest\\ads_xskb_storerefinfo.json"));
//        JSONArray storeInfoArr = new JSONArray(storeInfo);
//        StringBuilder storeInfoValues = new StringBuilder();
//        for (Object o : storeInfoArr) {
//            JSONObject tmp = (JSONObject)o;
//            storeInfoValues.append("(");
//            storeInfoValues.append("'" + tmp.getString("rec_modified") + "',");
//            storeInfoValues.append("'" + tmp.getString("tenant") + "',");
//            storeInfoValues.append("'" + tmp.getString("storegid") + "',");
//            storeInfoValues.append("'" + tmp.getString("date") + "',");
//            storeInfoValues.append("'" + tmp.getString("weekday") + "',");
//            storeInfoValues.append("'" + tmp.getString("workday") + "',");
//            storeInfoValues.append("'" + tmp.getString("phenomenon") + "',");
//            storeInfoValues.append("'" + tmp.getString("temperature") + "'");
//            storeInfoValues.append("),\n");
//        }
//        System.out.println(storeInfoValues.toString());


        String makePlan = FileUtils.readFileToString(new File("E:\\projects\\TesseractWeb\\src\\main\\resources\\static\\mkhtest\\ads_xskb_makeplan_td.json"));
        JSONArray makePlanArr = new JSONArray(makePlan);
        StringBuilder makePlanValues = new StringBuilder();
        for (Object o : makePlanArr) {
            JSONObject tmp = (JSONObject)o;
            makePlanValues.append("(");
            makePlanValues.append("'" + tmp.getString("processdate") + "',");
            makePlanValues.append("'" + tmp.getString("storegid") + "',");
            makePlanValues.append("'" + tmp.getString("gdgid") + "',");
            makePlanValues.append(tmp.getInt("qty") + ",");
            makePlanValues.append("'" + tmp.getString("tenant") + "',");
            makePlanValues.append("'" + tmp.getString("mealtimeuuid") + "',");
            makePlanValues.append(tmp.getInt("lastweeksale") + ",");
            makePlanValues.append("'" + tmp.getString("qpc") + "',");
            makePlanValues.append("'" + tmp.getString("rec_modified") + "'");
            makePlanValues.append("),\n");
        }
        System.out.println(makePlanValues.toString());




    }
}
