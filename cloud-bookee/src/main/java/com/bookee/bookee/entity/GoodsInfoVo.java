package com.bookee.bookee.entity;

public class GoodsInfoVo {
    private String inquiryNo;//询件单号
    private String partName;//	配件名称
    private String partCode;//	配件编码
    private String partType;//	配件类型

    //侧围件方向 L左R右
    private String posDirection;

    private String position1;//	配件部位1
    private String position2;//	配件部位2
    private String position3;//	配件部位3
    private String position4;//	配件部位4
    private String position5;//	配件部位5
    private String partCount;//	配件数量
    private String oriOem;//	原厂OEM码

    private String uableMemo;//	询件部位备注
    private String partDescribe;//	部位描述
    private String remark;//	备注

    private String originPrice;//	系统原厂件价
    private String marketPrice;//	系统市场价
    private String imitationPrice;//	系统同质价
    private String applierPrice;//	系统适用件格
    private String originBasicPrice;//	原厂件，基础价
    private String marketBasicPrice;//	市场件，基础价
    private String imitationBasicPrice;//	同质件，基础价
    private String applierBasicPrice;//	适用件，基础价
    private String originLocalPrice;//	原厂件,本地价 4S
    private String marketLocalPrice;//	市场件，本地价
    private String imitationLocalPrice;//	同质件，本地价
    private String applierLocalPrice;//	适用件，本地价
    private String fileFlag;//图片上传标志

    private String partUnitPrice;//配件单价

    private String pricePlan;//价格方案 0014S店价格 002市场件价格 003适用件价格 004协议价格 005同质件价格

    public String getPricePlan() {
        return pricePlan;
    }

    public void setPricePlan(String pricePlan) {
        this.pricePlan = pricePlan;
    }

    public String getPartUnitPrice() {
        return partUnitPrice;
    }

    public void setPartUnitPrice(String partUnitPrice) {
        this.partUnitPrice = partUnitPrice;
    }

    public String getPosDirection() {
        return posDirection;
    }

    public void setPosDirection(String posDirection) {
        this.posDirection = posDirection;
    }

    public String getInquiryNo() {
        return inquiryNo;
    }

    public void setInquiryNo(String inquiryNo) {
        this.inquiryNo = inquiryNo;
    }

    public String getFileFlag() {
        return fileFlag;
    }

    public void setFileFlag(String fileFlag) {
        this.fileFlag = fileFlag;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public String getPosition1() {
        return position1;
    }

    public void setPosition1(String position1) {
        this.position1 = position1;
    }

    public String getPosition2() {
        return position2;
    }

    public void setPosition2(String position2) {
        this.position2 = position2;
    }

    public String getPosition3() {
        return position3;
    }

    public void setPosition3(String position3) {
        this.position3 = position3;
    }

    public String getPosition4() {
        return position4;
    }

    public void setPosition4(String position4) {
        this.position4 = position4;
    }

    public String getPosition5() {
        return position5;
    }

    public void setPosition5(String position5) {
        this.position5 = position5;
    }

    public String getPartCount() {
        return partCount;
    }

    public void setPartCount(String partCount) {
        this.partCount = partCount;
    }

    public String getOriOem() {
        return oriOem;
    }

    public void setOriOem(String oriOem) {
        this.oriOem = oriOem;
    }

    public String getUableMemo() {
        return uableMemo;
    }

    public void setUableMemo(String uableMemo) {
        this.uableMemo = uableMemo;
    }

    public String getPartDescribe() {
        return partDescribe;
    }

    public void setPartDescribe(String partDescribe) {
        this.partDescribe = partDescribe;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(String originPrice) {
        this.originPrice = originPrice;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getImitationPrice() {
        return imitationPrice;
    }

    public void setImitationPrice(String imitationPrice) {
        this.imitationPrice = imitationPrice;
    }

    public String getApplierPrice() {
        return applierPrice;
    }

    public void setApplierPrice(String applierPrice) {
        this.applierPrice = applierPrice;
    }

    public String getOriginBasicPrice() {
        return originBasicPrice;
    }

    public void setOriginBasicPrice(String originBasicPrice) {
        this.originBasicPrice = originBasicPrice;
    }

    public String getMarketBasicPrice() {
        return marketBasicPrice;
    }

    public void setMarketBasicPrice(String marketBasicPrice) {
        this.marketBasicPrice = marketBasicPrice;
    }

    public String getImitationBasicPrice() {
        return imitationBasicPrice;
    }

    public void setImitationBasicPrice(String imitationBasicPrice) {
        this.imitationBasicPrice = imitationBasicPrice;
    }

    public String getApplierBasicPrice() {
        return applierBasicPrice;
    }

    public void setApplierBasicPrice(String applierBasicPrice) {
        this.applierBasicPrice = applierBasicPrice;
    }

    public String getOriginLocalPrice() {
        return originLocalPrice;
    }

    public void setOriginLocalPrice(String originLocalPrice) {
        this.originLocalPrice = originLocalPrice;
    }

    public String getMarketLocalPrice() {
        return marketLocalPrice;
    }

    public void setMarketLocalPrice(String marketLocalPrice) {
        this.marketLocalPrice = marketLocalPrice;
    }

    public String getImitationLocalPrice() {
        return imitationLocalPrice;
    }

    public void setImitationLocalPrice(String imitationLocalPrice) {
        this.imitationLocalPrice = imitationLocalPrice;
    }

    public String getApplierLocalPrice() {
        return applierLocalPrice;
    }

    public void setApplierLocalPrice(String applierLocalPrice) {
        this.applierLocalPrice = applierLocalPrice;
    }
}
