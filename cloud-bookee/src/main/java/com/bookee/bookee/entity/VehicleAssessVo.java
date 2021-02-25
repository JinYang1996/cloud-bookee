package com.bookee.bookee.entity;

public class VehicleAssessVo {
    private String notificationNo;//	报案号
    private String assessmentActualId;//	估损单唯一标识
    private String carId;//车辆出险信息Id 不能为空
    private String assessmentNo;//	估损单号
    private String source;//来源渠道	1.精友 2.自建库 3.ccc
    private String status;//	状态 2019年11月29日 13:54:03
    private String plateNo;//	车牌号
    private String vin;//	车架号
    private String assessmentStaffBranchCode;//	定损员所属分公司代码
    private String assessmentStaffCode;//	定损员账号
    private String assessmentStaffName;//	定损员姓名
    private String dispatchBranchCode;//	估损地分公司
    private String dispatchMiddleBranchCode;//	估损地中支
    private String code;//	维修厂代码
    private String name;//	维修厂名称
    private String contact;//	维修厂联系人 负责人
    private String tel;//	维修厂联系电话 负责人
    private String repairType;//	维修厂类别

    private String assessmentMobile;// 查勘员电话 定损员联系方式

    private String address;//营业地址

    private String provinceCode;//所在省代码
    private String provinceName;//所在省名称
    private String cityCode;//所在市代码
    private String cityName;//所在市名称
    private String areaCode;//所在区/县代码
    private String areaName;//所在区/县名称

    private String repairContact;//维修厂联络人 收货人
    private String repairTel;//维修厂联络电话 收货人

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getRepairContact() {
        return repairContact;
    }

    public void setRepairContact(String repairContact) {
        this.repairContact = repairContact;
    }

    public String getRepairTel() {
        return repairTel;
    }

    public void setRepairTel(String repairTel) {
        this.repairTel = repairTel;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAssessmentMobile() {
        return assessmentMobile;
    }

    public void setAssessmentMobile(String assessmentMobile) {
        this.assessmentMobile = assessmentMobile;
    }

    public String getNotificationNo() {
        return notificationNo;
    }

    public void setNotificationNo(String notificationNo) {
        this.notificationNo = notificationNo;
    }

    public String getAssessmentActualId() {
        return assessmentActualId;
    }

    public void setAssessmentActualId(String assessmentActualId) {
        this.assessmentActualId = assessmentActualId;
    }

    public String getAssessmentNo() {
        return assessmentNo;
    }

    public void setAssessmentNo(String assessmentNo) {
        this.assessmentNo = assessmentNo;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getAssessmentStaffBranchCode() {
        return assessmentStaffBranchCode;
    }

    public void setAssessmentStaffBranchCode(String assessmentStaffBranchCode) {
        this.assessmentStaffBranchCode = assessmentStaffBranchCode;
    }

    public String getAssessmentStaffCode() {
        return assessmentStaffCode;
    }

    public void setAssessmentStaffCode(String assessmentStaffCode) {
        this.assessmentStaffCode = assessmentStaffCode;
    }

    public String getAssessmentStaffName() {
        return assessmentStaffName;
    }

    public void setAssessmentStaffName(String assessmentStaffName) {
        this.assessmentStaffName = assessmentStaffName;
    }

    public String getDispatchBranchCode() {
        return dispatchBranchCode;
    }

    public void setDispatchBranchCode(String dispatchBranchCode) {
        this.dispatchBranchCode = dispatchBranchCode;
    }

    public String getDispatchMiddleBranchCode() {
        return dispatchMiddleBranchCode;
    }

    public void setDispatchMiddleBranchCode(String dispatchMiddleBranchCode) {
        this.dispatchMiddleBranchCode = dispatchMiddleBranchCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRepairType() {
        return repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
