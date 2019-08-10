package org.jkl.hrm.dao.provider;


import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.jkl.hrm.entity.Dept;

import static org.jkl.hrm.util.common.HrmConstants.DEPTTABLE;


public class DeptDynaSqlProvider {
	//分页动态查询
		public String selectWithParam(Map<String, Object> params) {
			String sql =new SQL() {
				{
					SELECT ("*");
					FROM("(SELECT *,ROW_NUMBER() OVER(ORDER BY ID) AS RowId FROM "+DEPTTABLE+")AS T");
					if(params.get("dept")!=null) {
						Dept dept = (Dept) params.get("dept");
						if(dept.getName()!=null && !dept.getName().equals("")) {
							WHERE(" name LIKE '%'+#{dept.name}+'%'");//mysql的concat中+连接,SqlServer“,”连接
						}
						//分页
						if(params.get("pageModel")!=null) {
							WHERE(" RowId between #{pageModel.firstLimitParam} and #{pageModel.firstLimitParam}+#{pageModel.pageSize}-1");
						}
					}
				}
			}.toString();
			return sql;
		}
		
		//动态查询相似名总数
		public String count(Map<String, Object> params) {
			return new SQL() {
				{
					SELECT("count(*)");
					FROM(DEPTTABLE);
					if(params.get("dept")!=null) {
						Dept dept = (Dept) params.get("dept");
						if(dept.getName()!=null && !dept.getName().equals("")) {
							WHERE(" name LIKE '%'+#{dept.name}+'%'");
						}
					}
				}
			}.toString();
		}
		
		
		//动态插入
		public String insertDept(Dept dept) {
			return new SQL() {
				{
					INSERT_INTO(DEPTTABLE);
					if(dept.getName()!=null && !dept.getName().equals("")) {
						VALUES("name","#{name}");
					}
					if(dept.getRemark()!=null && !dept.getRemark().equals("")) {
						VALUES("remark", "#{remark}");
					}
					WHERE(" id = #{id}");
				}
			}.toString();
		}
		
		//动态更新
		public String updateDept(Dept dept) {
			return new SQL() {
				{
					UPDATE(DEPTTABLE);
					if(dept.getName()!=null) {
						SET(" name = #{name} ");
					}
					if(dept.getRemark()!=null) {
						SET(" remark = #{remark} ");
					}
					WHERE("id = #{id}");
				}
			}.toString();
		}
}
