package com.bookee.bookee.entity;

import java.io.Serializable;
import java.util.List;

public class InquiryAggVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codeType;//发起人角色类型
    private String code;//发起人登录账号
    private String refreshUrl;//返回刷新URL
    private String orgCode;//发起人机构编码
    private String netType;//1.内网 2.外网
    private VehicleAssessVo vehicleAssess;//估损单信息
    private VehicleInfoVo vehicleInfo;//案件车辆信息
    private String timeliness;//报价时效
    private List<GoodsInfoVo> goodsInfo;//配件信息
    private String taskId;//M6传来的任务ID
    private String operateTime;//操作时间

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRefreshUrl() {
        return refreshUrl;
    }

    public void setRefreshUrl(String refreshUrl) {
        this.refreshUrl = refreshUrl;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public VehicleAssessVo getVehicleAssess() {
        return vehicleAssess;
    }

    public void setVehicleAssess(VehicleAssessVo vehicleAssess) {
        this.vehicleAssess = vehicleAssess;
    }

    public VehicleInfoVo getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(VehicleInfoVo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public String getTimeliness() {
        return timeliness;
    }

    public void setTimeliness(String timeliness) {
        this.timeliness = timeliness;
    }

    public List<GoodsInfoVo> getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(List<GoodsInfoVo> goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

}
