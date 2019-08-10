package org.jkl.hrm.dao;

import static org.jkl.hrm.util.common.HrmConstants.JOBTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.jkl.hrm.dao.provider.JobDynaSqlProvider;
import org.jkl.hrm.entity.Job;

 

public interface JobDao {
	//根据ID查询
	@Select("select * from "+JOBTABLE+" where ID = #{id}")
	Job selectById(int id);
	//查询所有
	@Select("select * from "+JOBTABLE+" ")
	List<Job> selectAllJob();
	//动态查询
	@SelectProvider(type=JobDynaSqlProvider.class,method="selectWithParam")
	List<Job> selectByPage(Map<String, Object> params);
	//查询相似 总数
	@SelectProvider(type=JobDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	//根据id删除部门
	@Delete(" delete "+JOBTABLE+" where ID = #{id}")
	void deleteById(int id);
	//动态插入部门
	@SelectProvider(type=JobDynaSqlProvider.class,method="insertJob")
	void save(Job job);
	//动态修改
	@SelectProvider(type=JobDynaSqlProvider.class,method="updateJob")
	void update(Job job);
}
