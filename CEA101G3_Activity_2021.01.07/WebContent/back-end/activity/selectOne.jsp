<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
	<div id="content-3">
<ul>
 <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/act.do"  >
        <b>輸入活動編號 (如A10001):</b>
        <input type="text" name="actId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="hidden" name="from" value="back-end">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="actSvc" scope="page" class="com.activity.model.ActivityService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/act.do"  >
       <b>選擇活動編號:</b>
       <select size="1" name="actId">
         <c:forEach var="activityVO" items="${actSvc.all}" > 
          <option value="${activityVO.actId}">${activityVO.actId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="hidden" name="from" value="back-end">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/act.do" >
       <b>選擇活動名稱:</b>
       <select size="1" name="actId">
         <c:forEach var="activityVO" items="${actSvc.all}" > 
          <option value="${activityVO.actId}">${activityVO.actName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="hidden" name="from" value="back-end">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>
</div>
</body>
</html>