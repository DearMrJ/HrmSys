package org.jkl.hrm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.jkl.hrm.dao.provider.DeptDynaSqlProvider;
import org.jkl.hrm.entity.Dept;

import static org.jkl.hrm.util.common.HrmConstants.DEPTTABLE;

public interface DeptDao {
	//动态查询
	@SelectProvider(type=DeptDynaSqlProvider.class,method="selectWithParam")
	List<Dept> selectByPage(Map<String, Object> params);
	//查询相似姓名 总数
	@SelectProvider(type=DeptDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	//查询全部
	@Select("select * from "+DEPTTABLE+"")
	List<Dept> selectAllDept();
	//根据ID查询
	@Select(" select * from "+DEPTTABLE+" where ID = #{id}")
	Dept selectById(int id);
	//根据id删除部门
	@Delete(" delete from "+DEPTTABLE+" where ID = #{id}")
	void deleteById(int id);
	//动态插入部门
	@SelectProvider(type=DeptDynaSqlProvider.class,method="insertDept")
	void save(Dept dept);
	//动态修改用户
	@SelectProvider(type=DeptDynaSqlProvider.class,method="updateDept")
	void update(Dept dept);
}
