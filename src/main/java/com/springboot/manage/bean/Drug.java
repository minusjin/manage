package com.springboot.manage.bean;

import java.io.Serializable;

/*
药品实体类
 */
public class Drug implements Serializable {
    //编号
    private int id;
    //药品名称
    private String drugname;
    //功效
    private String efficacy;
    //药味
    private String medprope;
    //药性
    private String medtaste;
    //归经
    private String categorize;
    //库存量
    private double amount;
    //价格
    private double price;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrugname() {
        return drugname;
    }

    public void setDrugname(String drugname) {
        this.drugname = drugname;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }

    public String getMedprope() {
        return medprope;
    }

    public void setMedprope(String medprope) {
        this.medprope = medprope;
    }

    public String getMedtaste() {
        return medtaste;
    }

    public void setMedtaste(String medtaste) {
        this.medtaste = medtaste;
    }

    public String getCategorize() {
        return categorize;
    }

    public void setCategorize(String categorize) {
        this.categorize = categorize;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "id=" + id +
                ", drugname='" + drugname + '\'' +
                ", efficacy='" + efficacy + '\'' +
                ", medprope='" + medprope + '\'' +
                ", medtaste='" + medtaste + '\'' +
                ", categorize='" + categorize + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
