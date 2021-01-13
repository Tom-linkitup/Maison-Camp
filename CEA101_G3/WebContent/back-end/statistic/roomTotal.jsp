<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.hibernate.Session" %>
<%@ page import="org.hibernate.Transaction" %>
<%@ page import="org.hibernate.query.NativeQuery" %>
<%@ page import="hibernate.util.HibernateUtil" %>
<%@ page import="java.util.*" %>
<%@ page import="java.math.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="<%=request.getContextPath()%>/js/back-end/echarts.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<%
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = null;
	List<String> roomCategoryList = new ArrayList(); 
	List<Integer> roomSum = new ArrayList();
	StringBuffer roomCategorySB = new  StringBuffer();
	StringBuffer roomSumSB = new  StringBuffer();
	int  roomCategoryCount= 0 , roomSumCount = 0 ;
	try {
		tx = session1.beginTransaction();
		@SuppressWarnings("unchecked")
		NativeQuery<Object[]> query2 = session1.createNativeQuery("select rc.ROOM_NAME , sum(rod.quantity) as 數量 from ROOM_CATEGORY rc inner join ROOM_ORDER_DETAIL rod ON rc.ROOM_CATEGORY_ID=rod.ROOM_CATEGORY_ID group by rc.ROOM_NAME");
		List<Object[]> list2 = query2.getResultList();
		for (Object[] aArray : list2) {
			for(int i=0 ; i<aArray.length ; i++){
				Object Obj = aArray[i];
				if(i==0){
					String roomCategory = (String)Obj;
					roomCategoryList.add(roomCategory);
				}else{
					BigDecimal bd = (BigDecimal)Obj;
					roomSum.add(bd.intValue()) ;
				}
			}
		}

		for(String s : roomCategoryList){
			roomCategoryCount++;
			if(!(roomCategoryCount==roomCategoryList.size())){
				roomCategorySB.append("\"");
				roomCategorySB.append(s);
				roomCategorySB.append("\",");
			}else{
				roomCategorySB.append("\"");
				roomCategorySB.append(s);
				roomCategorySB.append("\"");
			}
		}
		for(int i : roomSum){
			roomSumCount++;
			if(!(roomSumCount==roomCategoryList.size())){
				roomSumSB.append("\"");
				roomSumSB.append(i);
				roomSumSB.append("\",");
			}else{
				roomSumSB.append("\"");
				roomSumSB.append(i);
				roomSumSB.append("\"");
			}
		}
%>
<div id="main" style="width: 1800px; height: 800px;margin-left:2em;margin-top:5em;">
      <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById("main"));

        // 指定图表的配置项和数据
        var option = {
          xAxis: {
            type: "category",
            data: [<%=roomCategorySB%>],
          },
          yAxis: {
            type: "value",
          },
          series: [
            {
              data: [<%=roomSumSB%>],
              type: "bar",
              showBackground: true,
              backgroundStyle: {
                color: "rgba(220, 220, 220, 0.8)",
              },
            },
          ],
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
      </script>
    </div>
<%		tx.commit();
	} catch (RuntimeException ex) {
		if (tx != null)
			tx.rollback();
		throw ex; //System.out.println(ex.getMessage());
	} finally {
		session1.close();
	} 
%>
</body>
</html>