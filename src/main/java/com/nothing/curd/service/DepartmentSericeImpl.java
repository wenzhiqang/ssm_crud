package com.nothing.curd.service;

import java.util.List;

import com.nothing.curd.beans.Department;
import com.nothing.curd.dao.IDepartmentDao;

public class DepartmentSericeImpl implements IDepartmentSerice{
	
	private IDepartmentDao dao;
	
	public IDepartmentDao getDao() {
		return dao;
	}

	public void setDao(IDepartmentDao dao) {
		this.dao = dao;
	}
	/**
	 * 根据dept_id删除tbl_dept表中的数据
	 */
	@Override
	public int removeByPrimaryKey(Integer deptId) {
		// TODO Auto-generated method stub
		return dao.deleteByPrimaryKey(deptId);
	}
	/**
	 * 根据Department类的对象插入tbl_dept表中的数据
	 */
	@Override
	public int addDepartment(Department record) {
		// TODO Auto-generated method stub
		return dao.insert(record);
	}
	/**
	 * 根据Department类的对象的属性的指插入tbl_dept表中的数据
	 */
	@Override
	public int addDepartmentSelective(Department record) {
		// TODO Auto-generated method stub
		return dao.insertSelective(record);
	}
	/**
	 * 根据dept_id查询tbl_dept表中的数据
	 */
		@Override
	public Department queryByPrimaryKey(Integer deptId) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(deptId);
	}
	/**
	 * 查询所有的tbl_dept表中的数据
	 */
	@Override
	public List<Department> queryAllDepatyment() {
		// TODO Auto-generated method stub
		return dao.selectAllDepatyment();
	}
	/**
	 * 限制条件为主键，根据Department中的数据选择修改tbl_dept表中的数据
	 */
	@Override
	public int modifyByPrimaryKeySelective(Department record) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKeySelective(record);
	}
	/**
	 * 限制条件为主键，根据Department中的数据修改tbl_dept表中的数据
	 */
	@Override
	public int modifyByPrimaryKey(Department record) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKey(record);
	}
	

}
