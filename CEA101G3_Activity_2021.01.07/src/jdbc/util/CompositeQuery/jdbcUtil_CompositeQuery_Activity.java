/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */


package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_Activity {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("act_Category_Id".equals(columnName)) 
			aCondition = columnName + "='" + value + "'";
		else if ("act_Name".equals(columnName)) 
			aCondition = columnName + " like '%" + value + "%'";
		else if ("act_Start_Date".equals(columnName))
			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')>='" + value + "'";
		else if ("act_End_Date".equals(columnName))
			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')<='" + value + "'";
		
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (!"".equals(value) && value.trim().length() != 0 && !"action".equals(key) && !"from".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}


}
