package com.springboot.manage.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataWeekModel implements Serializable {
    private String name;
    private List<Integer> value=new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getValue() {
        return value;
    }

    public void setValue(List<Integer> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DataWeekModel{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
