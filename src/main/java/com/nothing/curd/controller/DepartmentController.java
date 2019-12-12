package com.nothing.curd.controller;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nothing.curd.beans.Department;
import com.nothing.curd.beans.Msg;
import com.nothing.curd.service.IDepartmentSerice;
@Controller
/**
 * 处理和部门信息相关的请求
 * @author wenzhiqinag
 *
 */
public class DepartmentController {
	
	@Resource(name="departmentSerice")
	private IDepartmentSerice service;
	
	/**
	 * 所有的部门信息
	 * @return
	 */
	@RequestMapping("getDepts.do")
	@ResponseBody
	public Msg getDepts(){
		List<Department> list = service.queryAllDepatyment();
		return Msg.success().add("dept", list);
	}
	
	
}
