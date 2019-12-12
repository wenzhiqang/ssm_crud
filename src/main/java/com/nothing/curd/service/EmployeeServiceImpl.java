package com.nothing.curd.service;

import java.util.List;

import com.nothing.curd.beans.Employee;
import com.nothing.curd.dao.IEmployeeDao;

public class EmployeeServiceImpl implements IEmployeeService {
	private IEmployeeDao dao;
	
	public IEmployeeDao getDao() {
		return dao;
	}

	public void setDao(IEmployeeDao dao) {
		this.dao = dao;
	}
	/**
	 * 根据cmp_id删除tbl_emp表中的数据
	 */
	@Override
	public int removeByPrimaryKey(Integer cmpId) {
		// TODO Auto-generated method stub
		return dao.deleteByPrimaryKey(cmpId);
	}
	/**
	 * 根据Employee类的对象插入tbl_emp表中的数据
	 */
	@Override
	public int addEmployee(Employee record) {
		// TODO Auto-generated method stub
		return dao.insert(record);
	}
	/**
	 * 根据Employee类的对象的字段的值有选择插入tbl_emp表中的数据
	 */
	@Override
	public int addEmployeeSelective(Employee record) {
		// TODO Auto-generated method stub
		return dao.insertSelective(record);
	}
	/**
	 * 根据cmp_id查询tbl_emp表中的数据
	 */
	@Override
	public Employee queryByPrimaryKey(Integer cmpId) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(cmpId);
	}
	/**
	 * 查询所有tbl_emp表中的数据
	 */
	@Override
	public List<Employee> queryAllEmployee() {
		// TODO Auto-generated method stub
		return dao.selectAllEmployee();
	}
	/**
	 * 根据cmp_id查询tbl_emp表中的数据带部门信息
	 */
	@Override
	public Employee queryByPrimaryKeyWithDept(Integer cmpId) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKeyWithDept(cmpId);
	}
	/**
	 * 查询所有tbl_emp表中的数据带部门信息
	 */
	@Override
	public List<Employee> queryAllEmployeeWithDept() {
		// TODO Auto-generated method stub
		return dao.selectAllEmployeeWithDept();
	}
	/**
	 * 根据主键有选择的修改tbl_emp表中的数据
	 */
	@Override
	public int modifyByPrimaryKeySelective(Employee record) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKeySelective(record);
	}
	/**
	 * 根据主键修改tbl_emp表中的数据
	 */
	@Override
	public int modifyByPrimaryKey(Employee record) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKey(record);
	}
	/**
	 * 传入name返回name在这个表中是否出现过
	 */
	@Override
	public boolean countTheSpecifiedNamet(String elemts) {
		 List<String> countTheSpecifiedElement = dao.countTheSpecifiedElement();
		int i=0;
		for (String string : countTheSpecifiedElement) {
			if(elemts.equals(string)){
				
//				System.out.println(elemts);
				i++;
			}
		}
		if(i==0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 根据id获得name
	 */
	@Override
	public String queryNameById(int id) {
		return dao.selectNameById(id);
	}
	/**
	 * 删除集合中所有的id
	 */
	@Override
	public void removeByAllBatch(String[] split) {
		for (int i = 0; i < split.length; i++) {
			Integer integer = new Integer(split[i]);
			dao.deleteByPrimaryKey(integer);
		}
	}
	

}
