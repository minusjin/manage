package com.springboot.manage.util;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PageUtil {

    //角标算法
    public Integer[] pageindex(String page,String limit){
        Integer pagenum=0;
        Integer limitnum=20;
        if(StringUtils.isEmpty(page)||page==null){
            pagenum=0;
        }else {
            pagenum=Integer.valueOf(page)-1;
        }
        if(StringUtils.isEmpty(limit)||limitnum==null){
            limitnum=20;
            pagenum=pagenum*limitnum;
        }else {
            pagenum=pagenum*Integer.valueOf(limit);
            limitnum=Integer.valueOf(limit);
        }
        Integer[] pageindex={pagenum,limitnum};
        return pageindex;
    }
}
