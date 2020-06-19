"use strict";
layui.define([], function (exprots) {
    let okMock = {
        api: {
            //药品库
            listDrug: "/druglist",
            //医疗记录库
            listMdfile: "/mdfilelist",
            //医案
            famousRecordList:"/fmRecordList",
            //今日诊疗
    　　　　　tondayPanList:"/tonpatlist"
        }
    };
    exprots("okMock", okMock);
});
