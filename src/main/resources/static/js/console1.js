"use strict";
layui.use(["okUtils", "okCountUp"], function () {
    var countUp = layui.okCountUp;
    var okUtils = layui.okUtils;
    var $ = layui.jquery;
    okLoading.close();

    /**
     * 疾病百分比图
     */
    function initUserActiveTodayChart() {
        var userActiveTodayChart = echarts.init($("#userActiveTodayChart")[0], "themez");
        userActiveTodayChart.setOption({
            color: "#03a9f3",
            xAxis: {
                type: 'category', data: ['周一', '周二', '周三', '周四', '周五', '周六', '周天']
            },
            yAxis: {type: 'value'},
            series: [{
                type: 'bar',
                data: []
            }]
        });
        okUtils.echartsResize([userActiveTodayChart]);
        userActiveTodayChart.showLoading();
        $.get('/data/usertonday').done(function (data) {
            userActiveTodayChart.hideLoading();
            //返回数据格式转换
          //data=JSON.parse(data)
           // var str = data.nums;
          // let slice = str.slice(1,14);
          // var par = /,/g;
          // let replace = slice.replace(par,"");

            userActiveTodayChart.setOption({
                color: "#03a9f3",
                tooltip: {},
                legend: {
                    data:['人数']
　　　　　　　　　},　　　　　
                    xAxis: {
                    type: 'category',
                    data:  ['周一', '周二', '周三', '周四', '周五', '周六', '周天']
                },
                series: [{
                    type:'bar',
                    data:data.nums
                }]
            });
        });
        okUtils.echartsResize([userActiveTodayChart]);
    }


    /**
     * 今日用户访问来源图表
     */
    function initUserSourceTodayChart() {
        var userSourceTodayChart = echarts.init($("#userSourceTodayChart")[0], "themez");
        userSourceTodayChart.setOption({
            title: {show: false, text: '病症占比',x: 'center'},
            tooltip: {trigger: 'item', formatter: "{a} <br/>{b} : {c} ({d}%)"},
            legend: {orient: 'vertical', left: 'left', data: []},
            series: [
                {
                    name: '百分比', type: 'pie', radius: '55%', center: ['50%', '60%'],
                    data: [],
                    itemStyle: {emphasis: {shadowBlur: 10, shadowOffsetX: 0, shadowColor: 'rgba(0, 0, 0, 0.5)'}}
                }
            ]
        });
        okUtils.echartsResize([userSourceTodayChart]);
        userSourceTodayChart.showLoading();
        $.get('/data/usersource').done(function (data) {
            userSourceTodayChart.hideLoading();
            //返回数据格式转换
            //data=JSON.parse(data)
            userSourceTodayChart.setOption({
                title: {show: false, text: '病症占比',x: 'center'},
                tooltip: {trigger: 'item', formatter: "{a} <br/>{b} : {c} ({d}%)"},
                series: [
                    {
                        name: '百分比', type: 'pie', radius: '55%', center: ['50%', '50%'],
                        data: data.nums,
                        itemStyle: {emphasis: {shadowBlur: 10, shadowOffsetX: 0, shadowColor: 'rgba(0, 0, 0, 0.5)'}}
                    }
                ]
            });
        });
        okUtils.echartsResize([userSourceTodayChart]);
    }


    /**
     * 本周用户访问来源图表
     */
    function initUserSourceWeekChart() {
        var userSourceWeekChart = echarts.init($("#userSourceWeekChart")[0], "themez");
        userSourceWeekChart.setOption({
            title: {show: true, text: ''},
            tooltip: {trigger: 'axis', axisPointer: {type: 'cross', label: {backgroundColor: '#6a7985'}}},
            toolbox: {show: false, feature: {saveAsImage: {}}},
            grid: {left: '3%', right: '4%', bottom: '3%', containLabel: true},
            xAxis: [{type: 'category', boundaryGap: false, data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']}],
            yAxis: [{type: 'value', splitLine: {show: false},}],
            series: [
            ]
        });
        okUtils.echartsResize([userSourceWeekChart]);
        userSourceWeekChart.showLoading()
        $.get('/data/userweeksource').done(function (data) {
            userSourceWeekChart.hideLoading();
            //返回数据格式转换
          //  data=JSON.parse(data)
            var series=[]
            var nums=data.nums
            $.each(nums, function (index, item) {
                series.push({
                    name:nums[index].name,
                    type:'line',
                    stack: '总量',
                    data:nums[index].value,
                    smooth:true,
                    areaStyle: {},
                })
            })
               userSourceWeekChart.setOption({
                title: {show: true, text: ''},
                tooltip: {trigger: 'axis', axisPointer: {type: 'cross', label: {backgroundColor: '#6a7985'}}},
                toolbox: {show: false, feature: {saveAsImage: {}}},
                legend: {data: data.names},
                grid: {left: '3%', right: '4%', bottom: '3%', containLabel: true},
                xAxis: [{type: 'category', boundaryGap: false, data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']}],
                yAxis: [{type: 'value', splitLine: {show: false},}],
                series:series
            });
        });
        okUtils.echartsResize([userSourceWeekChart]);
    }

    initUserActiveTodayChart();
    initUserSourceTodayChart();
    initUserSourceWeekChart();
});


