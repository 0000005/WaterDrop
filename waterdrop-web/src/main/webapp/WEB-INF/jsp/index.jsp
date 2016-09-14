<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html>
<jsp:include page="./common/head.jsp"></jsp:include>
<link href="${path}/static/skin/default/skin.css" rel="stylesheet" type="text/css" id="skin"/>
<title>WaterDrop|首页</title>

<body>
<header class="Hui-header cl"><a class="Hui-logo l" title="spinach" href="/">WaterDrop</a> <span class="Hui-subtitle l">V1.0</span>
    
    <ul class="Hui-userbar">
        <li>超级管理员</li>
        <li class="dropDown dropDown_hover"><a href="#" class="dropDown_A">${account.nickName }<i class="Hui-iconfont">
            &#xe6d5;</i></a>
            <ul class="dropDown-menu radius box-shadow">
                <li><a href="${path}/logout">切换账户</a></li>
                <li><a href="${path}/logout">退出</a></li>
            </ul>
        </li>
        <li id="Hui-msg"><a href="#" title="消息"><span class="badge badge-danger"></span><i class="Hui-iconfont"
                                                                                            style="font-size:18px">
            &#xe68a;</i></a></li>
        <li id="Hui-skin" class="dropDown right dropDown_hover"><a href="javascript:;" title="换肤"><i
                class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
            <ul class="dropDown-menu radius box-shadow">
                <li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
                <li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
                <li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
                <li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
                <li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
                <li><a href="javascript:;" data-val="orange" title="绿色">橙色</a></li>
            </ul>
        </li>
    </ul>
    <a aria-hidden="false" class="Hui-nav-toggle" href="#"></a></header>
<aside class="Hui-aside" style="background-color: #fafafa;">
    <input runat="server" id="divScrollValue" type="hidden" value=""/>
    <jsp:include page="./common/menu.jsp"></jsp:include>
</aside>
<div class="dislpayArrow"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
    <div id="Hui-tabNav" class="Hui-tabNav">
        <div class="Hui-tabNav-wp">
            <ul id="min_title_list" class="acrossTab cl">
                <li class="active"><span title="我的桌面" data-href="${path}">我的桌面</span><em></em></li>
            </ul>
        </div>
        <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S"
                                                  href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a
                id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">
            &#xe6d7;</i></a></div>
    </div>
    <div id="iframe_box" class="Hui-article">
        <div class="show_iframe">
            <div style="display:none" class="loading"></div>
            <iframe scrolling="yes" frameborder="0" src="${path }/welcome"></iframe>
        </div>
    </div>
</section>
<jsp:include page="./common/foot.jsp"></jsp:include>
</body>
</html>