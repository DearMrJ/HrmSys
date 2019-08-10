package org.jkl.hrm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.jkl.hrm.entity.Job;

import static org.jkl.hrm.util.common.HrmConstants.JOBTABLE;


public class JobDynaSqlProvider {
	//分页动态查询
	public String selectWithParam(Map<String,Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("(SELECT *,ROW_NUMBER() OVER(ORDER BY ID) AS RowId FROM "+JOBTABLE+")AS T");
				if (params.get("job")!=null) {
					Job job = (Job) params.get("job");
					if(job.getName() !=null && !job.getName().equals("")) {
						WHERE(" name LIKE '%'+#{job.name}+'%'");
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
				FROM(JOBTABLE);
				if(params.get("job")!=null) {
					Job job = (Job) params.get("job");
					if(job.getName()!=null && !job.getName().equals("")) {
						WHERE(" name LIKE '%'+#{job.name}+'%'");
					}
				}
			}
		}.toString();
	}
	
	//动态插入
	public String insertJob(Job job) {
		return new SQL() {
			{
				INSERT_INTO(JOBTABLE);
				if(job.getName()!=null && !job.getName().equals("")) {
					VALUES("name","#{name}");
				}
				if(job.getRemark()!=null && !job.getRemark().equals("")) {
					VALUES("remark","#{remark}");
				}
			}
		}.toString();
	}
	
	
	//动态更新
	public String updateJob(Job job) {
		return new SQL() {
			{
				UPDATE(JOBTABLE);
				if(job.getName()!=null) {
					SET(" name = #{name} ");
				}
				if(job.getRemark()!=null) {
					SET(" remark = #{remark} ");
				}
				WHERE(" id = #{id}");
			}
		}.toString();
	}
	
}
