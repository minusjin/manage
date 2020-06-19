package com.springboot.manage.util;

import com.springboot.manage.bean.FamRecord;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MdFileCompare {
    //内部类
    class Compare{
        String drug;
        String num;
    }
    public String[] compare(FamRecord famRecord,String drugs){
        String regEx="[@#$%^&*{}＠＃￥％……＆×，。？！＋——＝－；：’”～｀,<>?`~()（）－＋＝×/]"; //过滤特殊字符
        String regEx1 = "[\\u4e00-\\u9fa5]"; //提取汉字
        String regEx2 = "[0-9.]"; //提取数字
        Pattern p = Pattern.compile(regEx);
        //药方切分
        String[] drugssplipt = p.split(drugs);
        List<Compare> comparetemplat=new ArrayList<>();
        for (String s : drugssplipt) {
           Compare compare=new Compare();
           compare.drug=matchResult(Pattern.compile(regEx1),s);
           compare.num=matchResult(Pattern.compile(regEx2),s);
           comparetemplat.add(compare);
        }
        //比对的药方切分
        String[] comparesplit=p.split(famRecord.getDrugs());
        List<Compare> compartexam=new ArrayList<>();
        for (String s : comparesplit) {
            Compare compare=new Compare();
            compare.drug=matchResult(Pattern.compile(regEx1),s);
            compare.num=matchResult(Pattern.compile(regEx2),s);
            compartexam.add(compare);
        }
        //药方比对
        String tdrugs=""; //原药方
        String cdrugs=""; //被比对的药方
        //原药方更新
        for (Compare compare : comparetemplat) {
            int cflag=0;
            for (Compare compare1 : compartexam) {
                if(compare.drug.equals(compare1.drug)){
                    tdrugs=tdrugs+"<font color='green'>"+compare.drug+" "+"</font>";
                    if(compare.num.equals(compare1.num))
                        tdrugs=tdrugs+"<font color='green'>"+compare.num+",</font>";
                    else  tdrugs=tdrugs+"<font color='blue'>"+compare.num+",</font>";
                    cflag=1;
                    break;
                }
            }
            if(cflag==0) tdrugs=tdrugs+"<font color='red'>"+compare.drug+" "+compare.num+",</font>";
            cflag=0;
        }

        //模板药方更新
        for (Compare compare : compartexam) {
            int cflag=0;
            for (Compare compare1 : comparetemplat) {
                if(compare.drug.equals(compare1.drug)){
                    cdrugs=cdrugs+"<font color='green'>"+compare.drug+" "+"</font>";
                    if(compare.num.equals(compare1.num))
                        cdrugs=cdrugs+"<font color='green'>"+compare.num+",</font>";
                    else  cdrugs=cdrugs+"<font color='blue'>"+compare.num+",</font>";
                    cflag=1;
                    break;
                }
            }
            if(cflag==0) cdrugs=cdrugs+"<font color='red'>"+compare.drug+" "+compare.num+",</font>";
            cflag=0;
        }

        String[] strings={cdrugs,tdrugs};
        return strings;

    };

    public String matchResult(Pattern p,String str)
    {
        StringBuilder sb = new StringBuilder();
        Matcher m = p.matcher(str);
        while (m.find())
            for (int i = 0; i <= m.groupCount(); i++)
            {
                sb.append(m.group());
            }
        return sb.toString();
    }


}
