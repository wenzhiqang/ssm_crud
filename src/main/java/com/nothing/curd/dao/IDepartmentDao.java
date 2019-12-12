package com.nothing.curd.dao;

import java.util.List;

import com.nothing.curd.beans.Department;

public interface IDepartmentDao {
    int deleteByPrimaryKey(Integer deptId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer deptId);
    
    List<Department> selectAllDepatyment();

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}