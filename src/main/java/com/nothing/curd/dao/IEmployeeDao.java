package com.nothing.curd.dao;

import java.util.List;

import com.nothing.curd.beans.Employee;

public interface IEmployeeDao {
    int deleteByPrimaryKey(Integer cmpId);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer cmpId);
    
    List<Employee> selectAllEmployee();
    
    Employee selectByPrimaryKeyWithDept(Integer cmpId);
    
    List<Employee> selectAllEmployeeWithDept();
    
    List<String> countTheSpecifiedElement();
    
    String selectNameById(int id);
    
    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}