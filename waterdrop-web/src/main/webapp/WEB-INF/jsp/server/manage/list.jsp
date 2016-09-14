<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML>
<html>
<jsp:include page="../../common/head.jsp"></jsp:include>
<title>服务器管理</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		服务器资源 <span class="c-gray en">&gt;</span> 服务器管理 <a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-10">
		<div class="cl pd-5 bk-gray mt-10">
			<form action="${path}/servers/list.json" method="get" id="listForm">
			<input type="hidden" name="page" value="1" />
				<shiro:hasPermission name="sys:dict:add">
				<span class="l"><a href="javascript:;"
					onclick="layer_show('添加服务器','${path }/servers/add','','510')"
					class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
						添加服务器</a></span>
				</shiro:hasPermission>
				<span class="r"> <input type="text"
					class="input-text" style="width: 250px" placeholder="输入表字段"
					id="" name="keyWords">
					<button type="button" class="btn btn-success radius" id="search"
						name="">
						<i class="Hui-iconfont">&#xe665;</i> 搜索
					</button>
				</span>
			</form>
		</div>
		<div class="mt-10">
			<table
				class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="130">服务器昵称</th>
						<th width="110">服务器IP</th>
						<th>服务器备注</th>
						<th width="80">生产环境</th>
						<th width="70">是否启用</th>
						<th width="150">创建时间</th>
						<th width="150">更新时间</th>
						<shiro:hasPermission name="sys:dict:update">
						<th width="100">操作</th>
						</shiro:hasPermission>
					</tr>
				</thead>
				<tbody id="view">
				</tbody>
			</table>
		</div>
		<div class="mt-10" id="page"></div>
	</div>
	<script id="demo" type="text/html">
					{{# for(var i = 0, len = d.list.length; i < len; i++){ }}
						{{# var l = d.list[i]}}
						<tr class="text-c">
							<td>{{l.serverName}}</td>
							<td>{{l.serverIp}}:{{l.serverPort}}</td>
							<td>{{l.serverDescription }}</td>
							<td class="td-status">
								<span class="label {{ l.isProductEnv==1 ?'label-success':''}} radius">{{ l.isProductEnv==1 ?'是':'否' }}</span>
							</td>
							<td class="td-status">
								<span class="label {{ l.disabled==0 ?'label-success':''}} radius">{{ l.disabled==0 ?'启用':'禁用' }}</span>
							</td>
							<td>{{new Date(l.createTime).pattern("yyyy-MM-dd hh:mm:ss") }}</td>
							<td>{{new Date(l.updateTime).pattern("yyyy-MM-dd hh:mm:ss") }}</td>
							<shiro:hasPermission name="sys:dict:update">
							<td class="td-manage">
								<a title="管理" href="javascript:;"
								onclick="document.location.href=('${path }/servers/manage/{{l.id}}')"
								class="ml-5" style="text-decoration: none"><i class="Hui-iconfont">&#xe61d;</i></a>
								<a title="编辑" href="javascript:;"
								onclick="layer_show('修改服务器','${path }/servers/add?id={{l.id}}','800','500')"
								class="ml-5" style="text-decoration: none"><i class="Hui-iconfont">&#xe6df;</i></a>
							</td>
							</shiro:hasPermission>
						</tr>
					{{# } }}
	</script>
	<jsp:include page="../../common/foot.jsp"></jsp:include>
	<script type="text/javascript">
		$(function() {
			initPage("listForm", "demo", "view", "page");
		})
		$("#search").click(function() {
			$("[name='page']").val(1);
			initPage("listForm", "demo", "view", "page");
		})
		
	</script>
</body>
</html>