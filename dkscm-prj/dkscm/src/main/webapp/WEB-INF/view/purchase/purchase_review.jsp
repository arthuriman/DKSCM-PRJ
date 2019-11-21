<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="../../static/css/bootstrap.min.css">
  		<link rel="stylesheet" href="../../static/css/font-awesome.min.css">
  		<script src="../../static/js/jquery-3.4.1.min.js"></script>
		<script src="../../static/js/bootstrap.min.js"></script>
		<title></title>
		<script type="text/javascript">
			function review(statusCode) {
				// 获得审批意见
				var reviewRmark = $("#reviewRemark").val();
				if (reviewRmark != null && reviewRmark != "") {
					// 审批有效，进行异步数据修改
					$.ajax({
						url: "",
						type: "post",
						data: {
							"reviewRmark": reviewRmark,
							"statusCode": statusCode
						},
						dataType: "json",
						success: function(data) {
							if (data) {
								alert("审批成功！");
								// 关闭模态框
								parent.closeModal();
								// 页面重定向
								location.href = "../index";
							} else {
								alert("");
							}
						}
					});
				} else {
					// 审批无效
					alert("请填写审批意见！");
				}
			}
		</script>
	</head>
	<body>
		<div class="container" style="margin: 5px;">
			<div class="row">
				<div class="col-md-12">
						<table>
							<tr>
								<td align="right">
									<span style="color: red;">*</span>
									<i class="fa fa-gift"></i>
									<label>审批意见：</label>
								</td>
								<td>
									<input type="text" id="reviewRemark" name="reviewRemark" class="form-control"/>
								</td>
							</tr>
							<tr>
								<td><p></p></td>
								<td>
									<label class="error" for="goodsName" style="color: red;"></label>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="right">
									<a href="javascript:review('REVIEW');" class="btn btn-success btn-sm">
										<i class=""></i>
										<label>申请审批通过</label>
									</a>
									<a href="javascript:review('REVIEW_BACK');" class="btn btn-danger btn-sm">
										<i class=""></i>
										<label>申请审批驳回</label>
									</a>
								</td>
							</tr>
						</table>
				</div>
			</div>		
		</div>
	</body>
</html>