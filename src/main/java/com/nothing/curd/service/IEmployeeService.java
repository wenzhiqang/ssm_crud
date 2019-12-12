package com.nothing.curd.service;

import java.util.List;

import com.nothing.curd.beans.Employee;

public interface IEmployeeService {
	
	int removeByPrimaryKey(Integer cmpId);

    int addEmployee(Employee record);

    int addEmployeeSelective(Employee record);

    Employee queryByPrimaryKey(Integer cmpId);
    
    List<Employee> queryAllEmployee();
    
    Employee queryByPrimaryKeyWithDept(Integer cmpId);
    
    List<Employee> queryAllEmployeeWithDept();

    boolean countTheSpecifiedNamet(String elemts);
    
    int modifyByPrimaryKeySelective(Employee record);

    int modifyByPrimaryKey(Employee record);
    
    String queryNameById(int id);

	void removeByAllBatch(String[] split);
    
    

}
