<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
<jsp:include page="../../common/head.jsp"></jsp:include>
</head>
<body>
	<div class="pd-20">
		<form action="${path}/servers/save" method="post" class="form form-horizontal" id="fm">
			<input type="hidden" id="id" value="${id}" name="id">
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>服务器昵称：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${server.serverName }"
						placeholder="服务器昵称" id="serverName" name="serverName" datatype="*2-16"
						nullmsg="服务器昵称不能为空">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>服务器IP：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${server.serverIp }"
						placeholder="服务器IP" id="serverIp" name="serverIp" datatype="*2-16"
						nullmsg="服务器IP不能为空">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl" id="password">
				<label class="form-label col-3"><span class="c-red">*</span>服务器端口：</label>
				<div class="formControls col-5">
						<input type="text" class="input-text" value="${server.serverPort }"
						placeholder="服务器端口" id="serverPort" name="serverPort" datatype="*2-16"
						nullmsg="服务器端口不能为空">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">服务器备注：</label>
				<div class="formControls col-5">
					<textarea type="text" class="textarea" placeholder="服务器备注" id="serverDescription" name="serverDescription">${server.serverDescription }</textarea>
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">是否为生成环境：</label>
				<div class="formControls col-5">
					<span class="select-box" style="width: 150px;"> 
						<select class="select" name="isProductEnv" size="1">
							<option value="0" ${server.isProductEnv eq 0? 'selected' : '' }>否</option>
							<option value="1" ${server.isProductEnv eq 1? 'selected' : '' }>是</option>
						</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">是否启用：</label>
				<div class="formControls col-5">
					<span class="select-box" style="width: 150px;"> 
						<select class="select" name="disabled" size="1">
							<option value="0" ${server.disabled eq 0? 'selected' : '' }>可用</option>
							<option value="1" ${server.disabled eq 1? 'selected' : '' }>不可用</option>
						</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<div class="col-9 col-offset-3">
					<input class="btn btn-success radius" type="submit"
						value="&nbsp;&nbsp;提交&nbsp;&nbsp;"> <input
						class="btn btn-default radius" type="button"
						value="&nbsp;&nbsp;返回&nbsp;&nbsp;">
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="../../common/foot.jsp"></jsp:include>

	<script type="text/javascript">
		$(function() {
			var callback = function(data) {
				layer.msg(data.msg);
				if (data.status == "0") {
					var index = parent.layer.getFrameIndex(window.name);
					parent.initPage("listForm", "demo", "view", "page");
					parent.layer.close(index);
				}
			};
			$("#fm").validFrom(callback);
		});
	</script>
</body>
</html>