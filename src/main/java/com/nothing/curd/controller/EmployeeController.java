package com.nothing.curd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.jboss.logging.Property;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nothing.curd.beans.Employee;
import com.nothing.curd.beans.Msg;
import com.nothing.curd.service.IEmployeeService;

import net.sf.jsqlparser.statement.select.Select;

@Controller
public class EmployeeController {
	@Resource(name="employeeService")
	private IEmployeeService servlice;
	/**
	 * 员工跟新方法
	 * @param emp
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/*/uodateEmp.do",method=RequestMethod.POST)
//	@RequestMapping(value="/*/modiftEmp.do",method=RequestMethod.PUT)
	public Msg modifuEmp(Employee emp,HttpServletRequest request){
		String requestURI = request.getRequestURI();
		String[] split = requestURI.split("/");
		Integer integer = new Integer(split[2]);
		emp.setCmpId(integer);
		servlice.modifyByPrimaryKeySelective(emp);
		return Msg.success();
	}
	/**
	 * 单双删除二合一方法
	 * @param arr
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/*/delectEmp.do",method=RequestMethod.POST)
	public Msg delectEmp(@RequestParam("ids") String ids,HttpServletRequest request){
		if(ids.equals("a")){
			String requestURI = request.getRequestURI();
			String[] split = requestURI.split("/");
			Integer integer = new Integer(split[2]);
			//System.out.println(requestURI);
			servlice.removeByPrimaryKey(integer);
			return Msg.success();
		}else{
			String[] split = ids.split("-");
			servlice.removeByAllBatch(split);
			return Msg.success();
		}
		
	}
	/*@ResponseBody
	@RequestMapping(value="/delectEmps.do",method=RequestMethod.POST)
	public Msg delectEmp(String ids){
		
		String[] split = ids.split("-");
		servlice.removeByAllBatch(split);
		return Msg.success();
	}*/
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/empUpdate.do",method=RequestMethod.GET)
	public Msg getEmp(@RequestParam("id") Integer id){
		
		Employee queryByPrimaryKeyWithDept = servlice.queryByPrimaryKeyWithDept(id);
		return Msg.success().add("emp", queryByPrimaryKeyWithDept);
	}
	
	
	
	/**
	 * 保存员工信息
	 * 1.JSR303校验
	 * 2.导入hibernate-Validator
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/emp.do"},method=RequestMethod.POST)
	public Msg saveEmp(@Valid Employee employee,BindingResult result){
		
		if(result.hasErrors()){
			Map<String, Object> map=new HashMap<>();
			//校验失败，应该返回失败，在模态中显示校验失败的信息
			System.out.println(employee);
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else{
			servlice.addEmployeeSelective(employee);
			return Msg.success();
		}
	}
	/**
	 * 检查用户名是否可用
	 * @param empName
	 * @return
	 */
	@RequestMapping(value="/checKuser.do",method=RequestMethod.POST)
	@ResponseBody
	public Msg checKusser(@RequestParam("cmpName")String cmpName){
		
		boolean b=servlice.countTheSpecifiedNamet(cmpName);
		String regx="(^[a-z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!cmpName.matches(regx)){
			return Msg.fail().add("va_msg", "用户名可以是2-5w位汉字，或者6-16位英语数字结合");
		}
		if(b){
			return Msg.success();
		}else{
			return Msg.fail().add("va_msg","用户名可以是2-5w位汉字，或者6-16位英语数字结合");
		}
	} 
	
	
	
	@RequestMapping("/emps.do")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue="1")Integer pn,Model model){
//		引入pageHeier插件
		//在查询之前只需需要调用，传入页码，以及内页的大小
		PageHelper.startPage(pn, 5);
		//gtartPage后面紧跟着这个查询就是一个分页
		List<Employee> emps = servlice.queryAllEmployeeWithDept();
		//对数据进行封装，只需要将pageinfo交给页面就行
		//封装了详细的分页信息，包括有我们查询出来的数据,连续显示的页数
		PageInfo pageInfo = new PageInfo(emps,5);
		return Msg.success().add("pageInfo",pageInfo);
	}
	
	/**
	 * 数据分页
	 * @param empl
	 * @return
	 */
	@RequestMapping("/emps2.do")
	public String getEmps(@RequestParam(value="pn",defaultValue="1")Integer pn,Model model)
	{
//		引入pageHeier插件
		//在查询之前只需需要调用，传入页码，以及内页的大小
		PageHelper.startPage(pn, 5);
		//gtartPage后面紧跟着这个查询就是一个分页
		List<Employee> emps = servlice.queryAllEmployeeWithDept();
		//对数据进行封装，只需要将pageinfo交给页面就行
		//封装了详细的分页信息，包括有我们查询出来的数据,连续显示的页数
		PageInfo pageInfo = new PageInfo(emps,5);
		model.addAttribute("pageInfo",pageInfo);
		return "list";
	}

}
