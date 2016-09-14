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
<title>服务器信息</title>
</head>
<body>
	<div class="pd-10">
		<div class="row cl">
			<div class="col-xs-12 col-sm-8">
				<div class="row cl">
					<div class="col-xs-12 col-sm-6">
						<div id="cpuChart"  style="width:100%;height:250px;">
						
						</div>
					</div>
					<div class="col-xs-12 col-sm-6">
					b
					</div>
				</div>
				<div class="row cl">
					<div class="col-xs-12 col-sm-6">
					c
					</div>
					<div class="col-xs-12 col-sm-6">
					d
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4">
			1
			</div>
		</div>
	</div>
	<script type="text/javascript">
	/**********图表开始*************/
	var cpuChart = echarts.init(document.getElementById('cpuChart'), 'macarons');
    var option = 
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

    cpuChart.setOption(option);
	/********图表结束****************/
	</script>
</body>
</html>