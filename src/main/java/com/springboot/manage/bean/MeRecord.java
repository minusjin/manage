package com.springboot.manage.bean;

import java.io.Serializable;

/*
诊疗记录
 */
public class MeRecord implements Serializable {
    //编号
    private int id;
    //病人姓名
    private String name;
    //性别
    private String sex;
    //年龄
    private String age;
    //诊次
    private String frequ;
    //主诉
    private String chcom;
    //其它症状
    private String symptom;
    //中医诊断
    private String zdiagnosis;
    //西医诊断
    private String xdiagnosis;
    //舌苔
    private String tonfur;
    //脉象
    private String pulse;
    //辩证分型
    private String dialetype;
    //证素
    private String cercate;
    //治则治法
    private String rule;
    //药物组成
    private String drugs;
    //方剂数
    private String count;
    //方剂
    private String prescription;
    //时间
    private String time;
    //价钱
    private String price;

    //医生姓名
    private String dockername;

    //医生编号
    private Integer userid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFrequ() {
        return frequ;
    }

    public void setFrequ(String frequ) {
        this.frequ = frequ;
    }

    public String getChcom() {
        return chcom;
    }

    public void setChcom(String chcom) {
        this.chcom = chcom;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getZdiagnosis() {
        return zdiagnosis;
    }

    public void setZdiagnosis(String zdiagnosis) {
        this.zdiagnosis = zdiagnosis;
    }

    public String getXdiagnosis() {
        return xdiagnosis;
    }

    public void setXdiagnosis(String xdiagnosis) {
        this.xdiagnosis = xdiagnosis;
    }

    public String getTonfur() {
        return tonfur;
    }

    public void setTonfur(String tonfur) {
        this.tonfur = tonfur;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getDialetype() {
        return dialetype;
    }

    public void setDialetype(String dialetype) {
        this.dialetype = dialetype;
    }

    public String getCercate() {
        return cercate;
    }

    public void setCercate(String cercate) {
        this.cercate = cercate;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getDrugs() {
        return drugs;
    }

    public void setDrugs(String drugs) {
        this.drugs = drugs;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDockername() {
        return dockername;
    }

    public void setDockername(String dockername) {
        this.dockername = dockername;
    }

    @Override
    public String toString() {
        return "MeRecord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", frequ='" + frequ + '\'' +
                ", chcom='" + chcom + '\'' +
                ", symptom='" + symptom + '\'' +
                ", zdiagnosis='" + zdiagnosis + '\'' +
                ", xdiagnosis='" + xdiagnosis + '\'' +
                ", tonfur='" + tonfur + '\'' +
                ", pulse='" + pulse + '\'' +
                ", dialetype='" + dialetype + '\'' +
                ", cercate='" + cercate + '\'' +
                ", rule='" + rule + '\'' +
                ", drugs='" + drugs + '\'' +
                ", count='" + count + '\'' +
                ", prescription='" + prescription + '\'' +
                ", time='" + time + '\'' +
                ", price='" + price + '\'' +
                ", dockername='" + dockername + '\'' +
                ", userid=" + userid +
                '}';
    }
}
