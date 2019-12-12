package com.nothing.curd.service;

import java.util.List;

import com.nothing.curd.beans.Department;

public interface IDepartmentSerice {
	int removeByPrimaryKey(Integer deptId);

    int addDepartment(Department record);

    int addDepartmentSelective(Department record);

    Department queryByPrimaryKey(Integer deptId);
    
    List<Department> queryAllDepatyment();

    int modifyByPrimaryKeySelective(Department record);

    int modifyByPrimaryKey(Department record);
}
