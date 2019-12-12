package com.nothing.curd.beans;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class Employee {
    private Integer cmpId;
    @Override
	public boolean equals(Object cmpId) {
    	if(cmpId instanceof Employee)
    		return false;
    	Employee emp=(Employee)cmpId;
    	
    	return this.cmpId-emp.cmpId>0;
	}


	@Pattern(regexp="(^[a-z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]{2,5})",message="用户名可以是2-5w位汉字，或者6-16位英语数字结合")
    private String cmpName;

    private Integer gttder;
    @Email
    @Pattern(regexp="(^[a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6}$)",message="邮箱格式不正确")
    private String email;

    private Integer dId;
    
    
    private Department dept;


	public Integer getCmpId() {
		return cmpId;
	}


	public void setCmpId(Integer cmpId) {
		this.cmpId = cmpId;
	}


	public String getCmpName() {
		return cmpName;
	}


	public void setCmpName(String cmpName) {
		this.cmpName = cmpName;
	}


	public Integer getGttder() {
		return gttder;
	}


	public void setGttder(Integer gttder) {
		this.gttder = gttder;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getdId() {
		return dId;
	}


	public void setdId(Integer dId) {
		this.dId = dId;
	}


	public Department getDept() {
		return dept;
	}


	public void setDept(Department dept) {
		this.dept = dept;
	}


	@Override
	public String toString() {
		return "Employee [cmpId=" + cmpId + ", cmpName=" + cmpName + ", gttder=" + gttder + ", email=" + email
				+ ", dId=" + dId + ", dept=" + dept + "]";
	}


	public Employee(String cmpName, Integer gttder, String email, Integer dId) {
		super();
		this.cmpName = cmpName;
		this.gttder = gttder;
		this.email = email;
		this.dId = dId;
	}


	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
    
   
}