package com.bookee.bookee.entity;

public class VehicleInfoVo {

    private String assessmentNo;//估损单号 2019年11月29日 14:00:08

    private String vehicleVariety;//	车辆种类
    private String vehicleuseCharacter;//	车辆使用性质
    private String engineNo;//	发动机号
    private String thirdpartyFlag;//	三者标记 1：标的车，0：三者车
    private String carBrandName;//	车品牌名称
    private String carSeriesName;//	车系名称
    private String carName;//	车型名称

    private String modelCode;//车型编码
    private String brandCode;//品牌编码
    private String seriesCode;//车系编码
    private String carModelInfoType;//定型方式 1 VIN码；2 手动定型；3 自定义
    private String carYear;//年款

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getSeriesCode() {
        return seriesCode;
    }

    public void setSeriesCode(String seriesCode) {
        this.seriesCode = seriesCode;
    }

    public String getCarModelInfoType() {
        return carModelInfoType;
    }

    public void setCarModelInfoType(String carModelInfoType) {
        this.carModelInfoType = carModelInfoType;
    }

    public String getCarYear() {
        return carYear;
    }

    public void setCarYear(String carYear) {
        this.carYear = carYear;
    }

    public String getAssessmentNo() {
        return assessmentNo;
    }

    public void setAssessmentNo(String assessmentNo) {
        this.assessmentNo = assessmentNo;
    }

    public String getVehicleVariety() {
        return vehicleVariety;
    }

    public void setVehicleVariety(String vehicleVariety) {
        this.vehicleVariety = vehicleVariety;
    }

    public String getVehicleuseCharacter() {
        return vehicleuseCharacter;
    }

    public void setVehicleuseCharacter(String vehicleuseCharacter) {
        this.vehicleuseCharacter = vehicleuseCharacter;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getThirdpartyFlag() {
        return thirdpartyFlag;
    }

    public void setThirdpartyFlag(String thirdpartyFlag) {
        this.thirdpartyFlag = thirdpartyFlag;
    }

    public String getCarBrandName() {
        return carBrandName;
    }

    public void setCarBrandName(String carBrandName) {
        this.carBrandName = carBrandName;
    }

    public String getCarSeriesName() {
        return carSeriesName;
    }

    public void setCarSeriesName(String carSeriesName) {
        this.carSeriesName = carSeriesName;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }
}
