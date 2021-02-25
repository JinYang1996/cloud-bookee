package com.bookee.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bookee.aop.OperLogAspect;
import com.bookee.bookee.entity.GoodsInfoVo;
import com.bookee.bookee.entity.InquiryAggVo;
import com.bookee.bookee.entity.VehicleAssessVo;
import com.bookee.bookee.entity.VehicleInfoVo;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpUtil {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OperLogAspect.class);

    private final static String OPERATER_NAME = "【HTTP操作】";

    private final static int SUCCESS = 200;

    private final static String UTF8 = "UTF-8";

    private HttpClient client;

    private static HttpUtil instance = new HttpUtil();

    /**
     * 私有化构造器
     */
    private HttpUtil() {

        HttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = httpConnectionManager.getParams();
        params.setConnectionTimeout(5000);
        params.setSoTimeout(20000);
        params.setDefaultMaxConnectionsPerHost(1000);
        params.setMaxTotalConnections(1000);
        client = new HttpClient(httpConnectionManager);
        client.getParams()
              .setContentCharset(UTF8);
        client.getParams()
              .setHttpElementCharset(UTF8);
    }

    /**
     * get请求
     */
    public static String get(URL url) {
        return instance.doGet(url);
    }

    private String doGet(URL url) {
        long beginTime = System.currentTimeMillis();
        String respStr = StringUtils.EMPTY;
        try {
            logger.info(OPERATER_NAME + "开始get通信，目标host:" + url);
            HttpMethod method = new GetMethod(url.toString());
            // 中文转码
            method.getParams()
                  .setContentCharset(UTF8);
            try {
                client.executeMethod(method);
            } catch (HttpException e) {

                logger.error(e.getMessage());
            } catch (IOException e) {

                logger.error(e.getMessage());
            }
            if (method.getStatusCode() == SUCCESS) {
                respStr = method.getResponseBodyAsString();
            }
            // 释放连接
            method.releaseConnection();

            logger.info(OPERATER_NAME + "通讯完成，返回码：" + method.getStatusCode());
            logger.info(OPERATER_NAME + "返回内容：" + method.getResponseBodyAsString());
            logger.info(OPERATER_NAME + "结束..返回结果:" + respStr);
        } catch (Exception e) {
            logger.info(OPERATER_NAME, e);
        }
        long endTime = System.currentTimeMillis();
        logger.info(OPERATER_NAME + "共计耗时:" + (endTime - beginTime) + "ms");

        return respStr;
    }


    /**
     * POST请求
     */
    public static String post(URL url, String content) {
        return instance.doPost(url, content);
    }

    private String doPost(URL url, String content) {
        long beginTime = System.currentTimeMillis();
        String respStr = StringUtils.EMPTY;
        try {
            logger.info(OPERATER_NAME + "开始post通信，目标host:" + url.toString());
            logger.info("通信内容:" + content);
            PostMethod post = new PostMethod(url.toString());
            RequestEntity requestEntity = new StringRequestEntity(content, "application/json;charse=UTF-8", UTF8);
            post.setRequestHeader("userCode", "3421");
            post.setRequestHeader("token", "222");
            post.setRequestHeader("userBranchCode", "3010100");
            post.setRequestEntity(requestEntity);
            // 设置格式
            post.getParams()
                .setContentCharset(UTF8);

            client.executeMethod(post);
            if (post.getStatusCode() == SUCCESS) {
                respStr = post.getResponseBodyAsString();
            }

            logger.info(OPERATER_NAME + "通讯完成，返回码：" + post.getStatusCode());
            logger.info(OPERATER_NAME + "返回内容：" + post.getResponseBodyAsString());
            logger.info(OPERATER_NAME + "结束..返回结果:" + respStr);
            post.releaseConnection();

        } catch (Exception e) {
            logger.error(OPERATER_NAME, e);
        }
        long endTime = System.currentTimeMillis();
        logger.info(OPERATER_NAME + "共计耗时:" + (endTime - beginTime) + "ms");
        return respStr;
    }

    public static void main(String[] args) throws Exception {
        InquiryAggVo inquiryAggVo = new InquiryAggVo();
        VehicleAssessVo vehicleAssessVo = new VehicleAssessVo();
        vehicleAssessVo.setAddress("");
        vehicleAssessVo.setAreaCode("");
        vehicleAssessVo.setAreaName("");
        vehicleAssessVo.setAssessmentActualId("1000044252409");
        vehicleAssessVo.setAssessmentMobile("11111111111");
        vehicleAssessVo.setAssessmentNo("1000044013848001");
        vehicleAssessVo.setAssessmentStaffBranchCode("3010100");
        vehicleAssessVo.setAssessmentStaffCode("3181");
        vehicleAssessVo.setAssessmentStaffName("杨贤平");
        vehicleAssessVo.setCarId("1000044013848");
        vehicleAssessVo.setCityCode("");
        vehicleAssessVo.setCityName("");
        vehicleAssessVo.setCode("CLWX301010031010020000000000057");
        vehicleAssessVo.setName("宝山西上海");
        vehicleAssessVo.setNotificationNo("C310100VEH20000327");
        vehicleAssessVo.setPlateNo("苏A8485H");
        vehicleAssessVo.setProvinceCode("");
        vehicleAssessVo.setRepairContact("玻璃测试");
        vehicleAssessVo.setProvinceName("");
        vehicleAssessVo.setVin("LBEMDAEC4CZ083072");
        vehicleAssessVo.setTel("0");
        vehicleAssessVo.setSource("2");
        vehicleAssessVo.setRepairType("2");
        vehicleAssessVo.setRepairTel("15055445113");
        vehicleAssessVo.setDispatchMiddleBranchCode("310100");
        vehicleAssessVo.setDispatchBranchCode("3010100");

        VehicleInfoVo vehicleInfoVo = new VehicleInfoVo();
        vehicleInfoVo.setCarBrandName("长安");
        vehicleInfoVo.setCarName("朗动 2012款 1.6L GLX MT 领先版");
        vehicleInfoVo.setCarSeriesName("朗动");
        vehicleInfoVo.setCarYear("");
        vehicleInfoVo.setEngineNo("23452345");
        vehicleInfoVo.setThirdpartyFlag("N");
        vehicleInfoVo.setVehicleVariety("01");
        vehicleInfoVo.setVehicleuseCharacter("402");

        List<GoodsInfoVo> goodsInfoVoList = new ArrayList<>();
        String inquiryNo = "XJ201022263033663";
        for (int i = 1; i < 3; i++) {
            GoodsInfoVo goodsInfoVo = new GoodsInfoVo();
            goodsInfoVo.setInquiryNo(inquiryNo + i);
            goodsInfoVo.setPartName("前挡风玻璃" + i);
            goodsInfoVo.setPartCode("baacbacad10c4123" + i);
            goodsInfoVo.setPartType("2");
            goodsInfoVo.setPosDirection(null);
            goodsInfoVo.setPosition1("");
            goodsInfoVo.setPosition2("");
            goodsInfoVo.setPosition3("");
            goodsInfoVo.setPosition4("");
            goodsInfoVo.setPosition5("");
            goodsInfoVo.setPartCount("1");
            goodsInfoVo.setOriOem("861104V0T" + i);
            goodsInfoVo.setUableMemo("");
            goodsInfoVo.setPartDescribe("");
            goodsInfoVo.setRemark("PT测试" + i);
            goodsInfoVo.setOriginPrice("973.46");
            goodsInfoVo.setMarketPrice("");
            goodsInfoVo.setImitationPrice("");
            goodsInfoVo.setApplierPrice("");
            goodsInfoVo.setOriginBasicPrice("");
            goodsInfoVo.setMarketBasicPrice("888");
            goodsInfoVo.setImitationBasicPrice("555");
            goodsInfoVo.setApplierBasicPrice("");
            goodsInfoVo.setOriginLocalPrice("");
            goodsInfoVo.setMarketLocalPrice("");
            goodsInfoVo.setImitationLocalPrice("");
            goodsInfoVo.setApplierLocalPrice("");
            goodsInfoVo.setFileFlag("1");
            goodsInfoVo.setPartUnitPrice("555");
            goodsInfoVo.setPricePlan("001");
            goodsInfoVoList.add(goodsInfoVo);
        }
        inquiryAggVo.setVehicleAssess(vehicleAssessVo);
        inquiryAggVo.setVehicleInfo(vehicleInfoVo);
        inquiryAggVo.setGoodsInfo(goodsInfoVoList);
        inquiryAggVo.setCodeType("1");
        inquiryAggVo.setCode("3421");
        inquiryAggVo.setRefreshUrl("https://cxopcd-ft.cpic.com.cn/survey/claim/html/vehicle-damage.html?v=1595482746823&SSUrl=SS-h5&isSS=1");
        inquiryAggVo.setOrgCode("3010100");
        inquiryAggVo.setNetType("2");
        inquiryAggVo.setTaskId("100003765791");
        inquiryAggVo.setOperateTime("2020-10-22 16:30:54");
        inquiryAggVo.setTimeliness("6301");
        String requestJson = JSON.toJSONString(inquiryAggVo);

//        JSONObject json = new JSONObject();
//        json.put("boActualNo", "XJ2010211620454455");
//        json.put("taskId", "4965239");
//        URL url = new URL("http://partshare-sit.cpic.com.cn/partshare-api/api/partshare/business/inquiry/inquiryAuditTaskDetail");
        URL url = new URL("http://partshare-sit.cpic.com.cn/partshare-api/api/partshare/inquiryStart/generateAndInquiry");
        String resp = HttpUtil.post(url, requestJson);
        System.out.println(resp);
    }

}
