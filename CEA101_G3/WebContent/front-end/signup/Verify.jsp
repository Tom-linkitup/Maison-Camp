<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-end/verify.css">
<title>驗證會員</title>
</head>
<body>
        <div class="container">
		  <!-- Instructions -->
		  <div class="row">
		    <div class="alert alert-success col-md-12" role="alert" id="notes">
		      <h4>通知:</h4>
		      <ul>
		        <li>請至您註冊的信箱收取驗證碼 並輸入驗證碼完成會員驗證</li>
		        <li><a href="<%=request.getContextPath()%>/front-end/front-index.jsp">回首頁</a></li>
		        <li><a href="<%=request.getContextPath()%>/front-end/member/Member.jsp">回會員中心</a></li>
		      </ul>
		    </div>
		  </div>
		  <!-- Verification Entry Jumbotron -->
		  <div class="row">
		    <div class="col-md-12">
		      <div class="jumbotron text-center">
		        <h2>輸入驗證碼</h2>
		        <form method="post" action="<%=request.getContextPath()%>/Verify.do" role="form">
		          <div class="col-md-9 col-sm-12">
		            <div class="form-group form-group-lg">
		              <input type="text" class="form-control col-md-6 col-sm-6 col-sm-offset-2" name="authcode" required>
		              <input class="btn btn-primary btn-lg col-md-2 col-sm-2" type="submit" value="驗證">
		            </div>
		          </div>
		        </form>
		      </div>
		    </div>
		  </div>
		</div>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<c:if test="${authSuccess == 'yes'}">
	<script>
		swal("驗證成功","回首頁或會員中心","success");
	</script>
	
</c:if>
</body>
</html>