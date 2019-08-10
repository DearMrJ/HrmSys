package org.jkl.hrm.dao.provider;

import static org.jkl.hrm.util.common.HrmConstants.USERTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.jkl.hrm.entity.User;

public class UserDynaSqlProvider {
	//分页动态查询
	public String selectWithParam(Map<String, Object> params) {
		String sql =new SQL() {
			{
				SELECT ("*");
				FROM("(SELECT *,ROW_NUMBER() OVER(ORDER BY ID) AS RowId FROM "+USERTABLE+")AS T");
				if(params.get("user")!=null) {
					User user = (User) params.get("user");
					if(user.getUsername()!=null && !user.getUsername().equals("")) {
						WHERE(" username LIKE '%'+#{user.username}+'%'");//mysql的concat中+连接,SqlServer“,”连接
					}
					if(user.getUserstatus()!=null && !user.getUserstatus().equals("")) {
						WHERE(" userstatus LIKE  #{user.userstatus}");
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
	
	
	//动态查询总数量
	public String count(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(USERTABLE);
				if(params.get("user")!=null) {
					User user = (User) params.get("user");
					if(user.getUsername()!=null && !user.getUsername().equals("")) {
						WHERE(" username LIKE '%'+#{user.username}+'%'");
					}
					if(user.getUserstatus()!=null && !user.getUserstatus().equals("")) {
						WHERE(" userstatus = #{user.userstatus}");
					}
				}
			}
		}.toString();
	}
	
	//动态插入
	public String insertUser(User user) {
		return new SQL() {
			{
				INSERT_INTO(USERTABLE);
				if(user.getUsername()!=null && !user.getUsername().equals("")) {
					VALUES("username","#{username}");
				}
				if(user.getUserstatus()!=null && !user.getUserstatus().equals("")) {
					VALUES("userstatus","#{userstatus}");
				}
				if(user.getLoginname()!=null && !user.getLoginname().equals("")) {
					VALUES("loginname","#{loginname}");
				}
				if(user.getPassword()!=null && !user.getPassword().equals("")) {
					VALUES("password","#{password}");
				}
			}
		}.toString();
	}
	
	
	//动态更新
	public String updateUser(User user) {
		return new SQL() {
			{
				UPDATE(USERTABLE);
				if(user.getUsername()!=null) {
					SET(" username = #{username} ");
				}
				if(user.getUserstatus()!=null) {
					SET(" userstatus = #{userstatus} ");
				}
				if(user.getLoginname()!=null) {
					SET(" loginname = #{loginname} ");
				}
				if(user.getCreateDate()!=null) {
					SET(" createdate = #{createDate} ");
				}
				if(user.getUsername()!=null) {
					SET(" password = #{password} ");
				}
				WHERE("id = #{id}");
			}
		}.toString();
	}
	
}
