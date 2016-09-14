<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../../common/head.jsp"></jsp:include>
    <link rel="stylesheet" href="${path}/static/js/lib/zTree/zTreeStyle.css" type="text/css">
    <link href="${path}/static/skin/default/skin.css" rel="stylesheet" type="text/css" id="skin"/>
    <!--[if IE 6]>
    <script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>服务器-${server.serverName}</title>
    <script>
    //隐藏主窗口的侧边栏
    parent.shownavbar("hide");
    </script>
</head>
<body class="pos-r">
<div>
    <div>
        <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 服务器资源 <span class="c-gray en">&gt;</span> 服务器管理 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:history.go(-1);" title="刷新" ><i class="Hui-iconfont">&#xe67d;</i></a></nav>
    </div>
    <div class="pos-a" style="width:200px;left:0; height:100%; border-right:1px solid #e5e5e5; background-color:#f5f5f5">
    	<aside class="Hui-aside" style="top:0px;">
		    <div class="menu_dropdown bk_2">
		    	<dl>
					<dt>
						<a href="javascript:jumpSubFrame('./${server.id}/serverInfo')">服务器信息</a>
					</dt>
				</dl>
				<dl>
					<dt>
						<a href="">Tomcat管理</a>
					</dt>
				</dl>
		    </div>
		</aside>
    </div>
    
    <div class="Hui-article-box">
		<iframe id="subFrame" scrolling="yes" frameborder="0" src="./${server.id}/serverInfo" height="100%" width="100%"></iframe>
    </div>
</div>
<jsp:include page="../../common/foot.jsp"></jsp:include>
<script type="text/javascript">
	function jumpSubFrame(url)
	{
		$("#subFrame").attr("src",url);
	}
</script>
</body>
</html>