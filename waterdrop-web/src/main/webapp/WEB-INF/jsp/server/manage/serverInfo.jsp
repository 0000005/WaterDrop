<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML>
<html>
<jsp:include page="../../common/head.jsp"></jsp:include>
<script type="text/javascript" src="${path}/static/js/echarts/echarts.min.js"></script>
<script type="text/javascript" src="${path}/static/js/echarts/macarons.js"></script>
<script type="text/javascript" src="${path}/static/js/laydate/laydate.js"></script>
<title>服务器信息</title>
</head>
<body>
	<div class="pd-10">
		<div class="row cl">
			<div class="col-xs-12 col-sm-4">
				<form action="" method="post" class="form form-horizontal pd-20" id="demoform-1">
					<legend>基本信息</legend>
					<div class="row cl">
						<label class="form-label col-xs-4 col-sm-4">服务器昵称：</label>
						<div class="formControls col-xs-8 col-sm-8">
							<input type="text" class="input-text" autocomplete="off" placeholder="服务器昵称">
						</div>
					</div>
					<div class="row cl">
						<label class="form-label col-xs-4 col-sm-4">IP地址：</label>
						<div class="formControls col-xs-8 col-sm-8">
							<input type="text" class="input-text" autocomplete="off" placeholder="IP地址">
						</div>
					</div>
					<div class="row cl">
						<label class="form-label col-xs-4 col-sm-4">服务器端口：</label>
						<div class="formControls col-xs-8 col-sm-8">
							<input type="text" class="input-text" autocomplete="off" placeholder="端口">
						</div>
					</div>
					<div class="row cl">
						<label class="form-label col-xs-4 col-sm-4">是否启用：</label>
						<div class="formControls skin-minimal col-xs-8 col-sm-8">
							  <span class="select-box">
								  <select class="select" size="1" name="demo1">
								    <option value="" selected>请选择</option>
								    <option value="1">否</option>
								    <option value="2">是</option>
								  </select>
							  </span>
						</div>
					</div>
					<div class="row cl">
						<label class="form-label col-xs-4 col-sm-4">是否为生成环境：</label>
						<div class="formControls skin-minimal col-xs-8 col-sm-8">
							  <span class="select-box">
								  <select class="select" size="1" name="demo1">
								    <option value="" selected>请选择</option>
								    <option value="1">否</option>
								    <option value="2">是</option>
								  </select>
							  </span>

						</div>
					</div>
					<div class="row cl">
						<label class="form-label col-xs-4 col-sm-4">服务器备注：</label>
						<div class="formControls col-xs-8 col-sm-8">
							<textarea class="textarea" placeholder="说点什么..." rows="" cols="" name=""></textarea>
						</div>
					</div>
					<div class="row cl">
						<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
							<input class="btn btn-primary radius" type="submit" value="修改">
						</div>
					</div>
				</form>
				<legend>配置信息</legend>
				<table class="table table-hover">
					<tr>
						<td>内存：2048M</td>
						<td>CPU:5核心</td>
						<td>磁盘：150G</td>
					</tr>
					<tr>
						<td>操作系统： CentOS 6.5 64位</td>
						<td>JDK:1.7</td>
						<td>内网ip:127.0.0.1</td>
					</tr>
				</table>
			</div>
			<div class="col-xs-12 col-sm-8">
				<div class="row cl">
					<div class="col-xs-12 col-sm-12 text-r pd-10">
					开始时间：<input id="startTime" class="laydate-icon" style="width:200px; margin-right:10px;">
					结束时间：<input id="endTime" class="laydate-icon" style="width:200px;">&nbsp;&nbsp;&nbsp;
					<input class="btn btn-primary radius" type="button" value="查询">
					</div>
				</div>
				<div class="row cl">
					<div class="col-xs-12 col-sm-6">
						<div id="cpuChart"  style="width:100%;height:250px;">
						</div>
					</div>
					<div class="col-xs-12 col-sm-6">
						<div id="memoryChart"  style="width:100%;height:250px;">
						</div>
					</div>
				</div>
				<div class="row cl">
					<div class="col-xs-12 col-sm-12">
						<div id="networkChart"  style="width:100%;height:250px;">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
	/**********日期拾取开始*************/
	var start = 
	{
	    elem: '#startTime',
	    format: 'YYYY/MM/DD hh:mm:ss',
	    min: laydate.now(), //设定最小日期为当前日期
	    max: '2099-06-16 23:59:59', //最大日期
	    istime: true,
	    istoday: false,
	    choose: function(datas)
	    {
	         end.min = datas; //开始日选好后，重置结束日的最小日期
	         end.start = datas //将结束日的初始值设定为开始日
	    }
	};
	var end = 
	{
	    elem: '#endTime',
	    format: 'YYYY/MM/DD hh:mm:ss',
	    min: laydate.now(),
	    max: '2099-06-16 23:59:59',
	    istime: true,
	    istoday: false,
	    choose: function(datas)
	    {
	        start.max = datas; //结束日选好后，重置开始日的最大日期
	    }
	};
	laydate(start);
	laydate(end);
	/**********日期拾取结束*************/
	
	/**********图表开始*************/
	var cpuChart = echarts.init(document.getElementById('cpuChart'), 'macarons');
    var cpuOption = 
    {
        title: {
            text: 'CPU占用率'
        },
        tooltip : {
            trigger: 'axis',
            formatter : function (params) {
                var date = new Date(params[0].data[0]);
                data = date.getFullYear() + '-'
                       + (date.getMonth() + 1) + '-'
                       + date.getDate() + ' '
                       + date.getHours() + ':'
                       + date.getMinutes();
                return data + '<br/>CPU占用率'
                       + params[0].data[1]
            }
        },
        xAxis: {
        	type:'time',
            axisLine:{
            	show:false
            },
            axisTick:{
            	show:false
            },
            axisLabel:{
            	formatter:function (value, index) {
            		 var date = new Date(value);
                     return date.pattern("MM-dd hh:mm");
            	}
            }
        },
        yAxis: {
        	axisLine:{
            	show:false
            },
            axisTick:{
            	show:false
            }
        },
        series: 
        [{
            name: 'CPU占用率',
            type: 'line',
            symbolSize: function (value){
                return Math.round(value[2]/10) + 2;
            },
            data: (function () {
                var d = [];
                var len = 0;
                var now = new Date();
                var value;
                while (len++ <20) {
                    d.push([
                        new Date(2014, 9, 1, 0, len * 10000),
                        (Math.random()*100).toFixed(2) - 0
                    ]);
                }
                return d;
            })()
        }]
    };

    cpuChart.setOption(cpuOption,true);
    
    var memoryChart = echarts.init(document.getElementById('memoryChart'), 'macarons');
    var memoryOption = 
    {
        title: {
            text: '内存占用率'
        },
        tooltip : {
            trigger: 'axis',
            formatter : function (params) {
                var date = new Date(params[0].data[0]);
                data = date.getFullYear() + '-'
                       + (date.getMonth() + 1) + '-'
                       + date.getDate() + ' '
                       + date.getHours() + ':'
                       + date.getMinutes();
                return data + '<br/>内存占用率'
                       + params[0].data[1]
            }
        },
        xAxis: {
        	type:'time',
            axisLine:{
            	show:false
            },
            axisTick:{
            	show:false
            },
            axisLabel:{
            	formatter:function (value, index) {
            		 var date = new Date(value);
                     return date.pattern("MM-dd hh:mm");
            	}
            }
        },
        yAxis: {
        	axisLine:{
            	show:false
            },
            axisTick:{
            	show:false
            }
        },
        series: 
        [{
            name: '内存占用率',
            type: 'line',
            symbolSize: function (value){
                return Math.round(value[2]/10) + 2;
            },
            data: (function () {
                var d = [];
                var len = 0;
                var now = new Date();
                var value;
                while (len++ <20) {
                    d.push([
                        new Date(2014, 9, 1, 0, len * 10000),
                        (Math.random()*100).toFixed(2) - 0
                    ]);
                }
                return d;
            })()
        }]
    };

    memoryChart.setOption(memoryOption,true);
    
    
    var networkChart = echarts.init(document.getElementById('networkChart'), 'macarons');
    var networkOption = 
    {
        title: {
            text: '网络占用率'
        },
        tooltip : {
            trigger: 'axis',
            formatter : function (params) {
                var date = new Date(params[0].data[0]);
                data = date.getFullYear() + '-'
                       + (date.getMonth() + 1) + '-'
                       + date.getDate() + ' '
                       + date.getHours() + ':'
                       + date.getMinutes();
                return data + '<br/>网络占用率'
                       + params[0].data[1]
            }
        },
        xAxis: {
        	type:'time',
            axisLine:{
            	show:false
            },
            axisTick:{
            	show:false
            },
            axisLabel:{
            	formatter:function (value, index) {
            		 var date = new Date(value);
                     return date.pattern("MM-dd hh:mm");
            	}
            }
        },
        yAxis: {
        	axisLine:{
            	show:false
            },
            axisTick:{
            	show:false
            }
        },
        series: 
        [{
            name: '网络占用率',
            type: 'line',
            symbolSize: function (value){
                return Math.round(value[2]/10) + 2;
            },
            data: (function () {
                var d = [];
                var len = 0;
                var now = new Date();
                var value;
                while (len++ <20) {
                    d.push([
                        new Date(2014, 9, 1, 0, len * 10000),
                        (Math.random()*100).toFixed(2) - 0
                    ]);
                }
                return d;
            })()
        }]
    };

    networkChart.setOption(networkOption,true);
    
    
    //四表联动
	echarts.connect([cpuChart, memoryChart,networkChart]);
    
	/********图表结束****************/
	</script>
</body>
</html>