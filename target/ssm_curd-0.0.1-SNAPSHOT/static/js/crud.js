function getMessget(){
	var totalRecord;
	var currentPage;
	$(function(){
		//去首页
		to_page(1);
		
	});
	function to_page(pn){
		$.ajax({
			url:"${App_Path}/emps.do",
			data:"pn="+pn,
			type:"GET",
			success:function(result){
				 /* console.log(result); */
				 build_emps_table(result);
				 //2.显示分页数据
				 build_page_info(result);
				 //3.显示分页信息数据
				 build_page_nav(result);
			}
		});
	}
	function build_emps_table(result){
		$("#emps_table tbody").empty();
		var emps=result.extend.pageInfo.list;
		$.each(emps,function(index,itme){
			var checkBoxTx=$("<td><input type='checkbox' class='check_itme'/ ></td>");
			var deptTa=$("<td></td>").append(itme.cmpId);
			var cmpNameTa=$("<td></td>").append(itme.cmpName);
			var gttderTa=$("<td></td>").append(itme.gttder=="1"?'男':'女');
			var emailTa=$("<td></td>").append(itme.email);
			var deptNameTa=$("<td></td>").append(itme.dept.deptName);
			/* <button class="btn btn-info btn-sm">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
						编辑</button> */
			var editBtu=$("<button></button>").addClass("btn btn-info btn-sm").append("<span></span>").addClass("glyphicon glyphicon-pencil edit_but").append("编辑");
				/* 为编辑按钮添加一个自定属性，来表示员工的id */
				editBtu.attr("edit_id",(itme.cmpId));
			var delBtu=$("<button></button>").addClass("btn btn-danger btn-sm").append("<span></span>").addClass("glyphicon glyphicon-trash delete_but").append("删除");
				delBtu.attr("delect_id",itme.cmpId);					var btnId=$("<td></td>").append(editBtu).append(" ").append(delBtu);
			$("<tr></tr>").append(checkBoxTx).append(deptTa).append(cmpNameTa).append(gttderTa).append(emailTa).append(deptNameTa).append(btnId) .appendTo("#emps_table tbody");
		});
	}
	//解析显示分页数据
	function build_page_info(result){
		$("#page_info_area").empty();
		$("#page_info_area").append("当前"+result.extend.pageInfo.pageNum+"页, 总"+result.extend.pageInfo.pages+"页, 总"+result.extend.pageInfo.total+"条记录");
		totalRecord=result.extend.pageInfo.total;
		currentPage=result.extend.pageInfo.pageNum;
	}
	
	function build_page_nav(result){
		$("#build_nav_area").empty();
		var uls=$("<ul></ul>").addClass("pagination")
		//构建元素
		var firstPageLi=$("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
		var perPageLi=$("<li></li>").append($("<a></a>").append("&laquo;"));
		/*
			判断是否有上一页，给首页还有上一页和首页加不可点击事件
		*/
		if(result.extend.pageInfo.navigatepageNums){
			
			//为元素添加单击事件
			firstPageLi.click(function(){
				to_page(1);
			});
			perPageLi.click(function(){
				to_page(result.extend.pageInfo.pageNum-1);
			});                 
		}else{
			firstPageLi.addClass("disabled");
			perPageLi.addClass("disabled");  
		}
		
		var nextPageLi=$("<li></li>").append($("<a></a>").append("&raquo;"));
		var lastPageLi=$("<li></li>").append($("<a></a>").append("尾页").attr("href","#"));
		//添加首页前一页
		/**判断是否有下一页，给首页还有下一页还有尾页加不可点击事件*/
		if(result.extend.pageInfo.hasNextPage==false){
			nextPageLi.addClass("disabled");
			lastPageLi.addClass("disabled");                   
		}else{
			nextPageLi.click(function(){
			to_page(result.extend.pageInfo.pageNum+1);
			});
			lastPageLi.click(function(){
				to_page(result.extend.pageInfo.pages);
			});
		}
		
		uls.append(firstPageLi).append(perPageLi);
		$.each(result.extend.pageInfo.navigatepageNums , function(index,itme){
			var numLi=$("<li></li>").append($("<a></a>").append(itme));
			numLi.click(function(){
				to_page(itme);
			});
			if(result.extend.pageInfo.pageNum==itme){
				numLi.addClass("active");
				
			}
			
			uls.append(numLi);
		});
		
	//	添加下一页和尾页的提示
		
		uls.append(nextPageLi).append(lastPageLi);
	 	
	 	var navEle =$("<nav></nav>").append(uls);
	 	
	 	navEle.appendTo("#build_nav_area"); 
	 	
	 	
	}
	
	//清空表单样式及内容
	function reset_form(ele){
		$(ele)[0].reset();
		$(ele).find("*").removeClass("has-succes has-error");
		$(ele).find(".help-block").text("");
	}
	//点击新增按钮弹出模态框，
	$("#emps_add_model_but").click(function(){
		//清空模态框里面的数据
		reset_form("#empAddModal form");
		//发送ajax请求，查出部门信息，显示在下拉列表中
		getDepts("#empAddModal select");
		//弹出模态框
		$("#empAddModal").modal({modal:"static"});
	});
	//查出所有部门信息并显示在下拉列表中
	 function getDepts(ele){
	 	$(ele).empty();
		$.ajax({
			url:"${App_Path }/getDepts.do",
			type:"GET",
			success:function(result){
				/* console.log(result); */
				/* 显示到下拉列表中 */
				//#("#empAddModal select")
				$.each(result.extend.dept,function(index,itme){
					var opteonEle=$("<option></option>").append(this.deptName).attr("value",this.deptId);
					opteonEle.appendTo(ele);
				});
			}
		})
	} 
	
	//传进来要校验的元素，是否成功，显示提示信息
	function show_validata_msg(ele,status,msg)
	{
		//alert("a");
		/* $(ele).parent().removeClass("has-succes has-error");
		$(ele).next("span").text(""); */
		
		if("success"==status){
			$(ele).parent().addClass("has-succes");
			$(ele).next("span").text(msg);
		}
		if("error"==status){
			$(ele).parent().addClass("has-error");
			$(ele).next("span").text(msg);
			
		}
	}
	
	
	//检验用户名是否可用
	$("#cmpName_input").change(function (){
		//alert("a");
		 //发送ajax请求校验用户名是否可用
		//reset_form("#empAddModal form");
		var cmpName=this.value;
		var regName=/(^[a-z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
		
		//先判断用户名是否是合法的表达式
		if(!regName.test(cmpName)){
			//$("#cmpName_input_input").parent().addClass("has-error");
			//$("#cmpName_input_input").next("span").text("用户名可以是2-5w位汉字，或者6-16位英语数字结合");
			show_validata_msg("#cmpName_input","error","用户名可以是2-5w位汉字，或者6-16位英语数字结合");
			return false;
		}
		
		$.ajax({
			url:"${App_Path }/checKuser.do",
			type:"POST",
			data:"cmpName="+cmpName,
			success:function(result){
				//alert(result.msg);
				if(result.code==200){
						show_validata_msg("#cmpName_input","error","用户名已经注册了"); 
						$("#emp_save_btn").attr("ajax_va","error");
				}else{
					show_validata_msg("#cmpName_input","success","用户名没有进行注册,欢迎注册!"); 
					$("#emp_save_btn").attr("ajax_va","success");
				}
				/* alert(result.code); */
			}
		}); 
		
		// 				 /* $.ajax({
	// 					url:"${App_Path}/checKuser.do",
	// 					data:"cmpName="+cmpName,
	// 					type:"POST",
	// 					success:function(result){
	// 						if(result.code==100){
	// 							show_validata_msg("#cmpName_input","success","用户名没有被注册过");
	// 							$("#cmpName_input").attr("ajax_va","success");
	// 						}else{
	// 							show_validata_msg("#cmpName_input","error",result.extend.va_msg);
	// 							$("#cmpName_input").attr("ajax_va","error");
	// 							show_validata_msg("#cmpName_input","error","用户名已经注册过了");
					
	// 						}
			
	// 					}
	// 				}); */
		
	});
	
	//对表单的数据进行校验
	function validate_add_form(){
		
		//1.拿到要校验的数据，使用正则表达式进行校验
		var enpName=$("#cmpName_input_input").val();
		var regName=/(^[a-z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
		if(!regName.test(enpName)){
			//$("#cmpName_input_input").parent().addClass("has-error");
			//$("#cmpName_input_input").next("span").text("用户名可以是2-5w位汉字，或者6-16位英语数字结合");
			show_validata_msg("#cmpName_input_input","error","用户名可以是2-5w位汉字，或者6-16位英语数字结合");
			return false;
		}else{
			//$("#cmpName_input_input").parent().addClass("has-success");
			//$("#cmpName_input_input").next("span").text("");
			show_validata_msg("#cmpName_input_input","success","");
		}
		
		//2.校验邮箱
		var email=$("#email_input_input").val();
		var refEmail=/(^[a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6}$)/;
		if(!refEmail.test(email)){
			//alert("邮箱格式不正常"); 
			//$("#email_input_input").parent().addClass("has-error");
			//$("#email_input_input").next("span").text("邮箱格式不正确");
			show_validata_msg("#email_input_input","error","邮箱格式不正确");
			return false;
		}else{
			//$("#email_input_input").parent().addClass("has-success");
			//$("#email_input_input").next("span").text("");
			show_validata_msg("#email_input_input","success","");
		}
		
		return true;
		
	}
	
	//员工添加
	$("#emp_save_btn").click(function (){
		//1.模态框中填写的表单数据交给服务器
		//先阱行校验,如果用户民，邮箱不合法，不进行ajax请求
		if(validate_add_form()==false){
			return false;
		}
		//进行后天验证，如果名有这不进行ajax提交
		if($("#emp_save_btn").attr("ajax_va")=="error"){
			return false;
		}
		
		$.ajax({
			url:"${App_Path }/emp.do",
			type:"POST",
			data:$("#empAddModal form").serialize(),
			success:function(result){
				//alert(result.msg);
				if(result.code==100){
					//1.关闭模态框
					$('#empAddModal').modal('hide')
					//2.来到最后一页，显示刚才保存的数据
					//发送ajax请求显示最后一页的数据
					to_page(totalRecord);
				}else{
					if(undefinad!=result.extend.fieldError.cmpName){
						show_validata_msg("#cmpName_input_input","success",result.extend.fieldError.cmpName); 
					}else if(undefinad!=result.extend.fieldError.email){
						show_validata_msg("#email_input_input","success",result.extend.fieldError.email); 
					}
				}
			}
		});
		//validate_add_form();
		/* alert($("#empAddModal form").serialize()); */
		//alert($("#empAddModal form").serialize())
		
	});
	//点击删除  
	$(document).on("click",".delete_but",function (){
		//弹出是否确认删除
		var delect_id=$(this).attr("delect_id");
		alert(delect_id);
		var empName=$(this).parents("tr").find("td:eq(1)").text();
		//alert($(this).parents("tr").find("td:eq(1)").text());
		if(confirm("确认删除【"+empName+"】吗？")){
			 $.ajax({
					url:"${App_Path}/"+delect_id+"/delectEmp.do",
					type:"POST",
					success:function(result){
						if(result.code==100){
							alert("删除成功");
							to_page(currentPage);
						}
					}
			}); 
		}
		
	})
	
	//1、我们是按钮船舰之前就绑定了click，所以帮定不上
	//1）、可以在创建按钮的时候绑定。2）。绑定点击.live(),jquery高版本帮这个方法删除了 	
	//3）、使用on进行替代
	// $(".edit_but").live(function (){}); 
	 $(document).on("click",".edit_but",function (){
	 	
	 	//2、查出部门信息，并显示部门列表
		getDepts("#empUpdateModal select");
		//1、查出员工信息，显示员工信息
		getEmp($(this).attr("edit_id"));
		//将员工的id传给模态框
		$("#emp_uodata_btn1").attr("edit_id",$(this).attr("edit_id"));
						
		//弹出模态框
		$("#empUpdateModal").modal({modal:"static"});
		
	 }); 
	function getEmp(id){
		$.ajax({
			url:"${App_Path}/empUpdate.do",
			type:"get",
			data:"id="+id,
			success:function(result){
				/* $("empName_update_static").text(result.extend) */
				var empData=result.extend.emp;
				$("#empName_update_static").text(empData.cmpName);
				$("#email_Update_input").val(empData.email);
				$("#empUpdateModal input[name=gttder]").val([empData.gttder]);
				$("#updata_did select").val([empData.dId]);
			}
		});
	}
	//点击跟新，更新员工信息
	$("#emp_uodata_btn1").click(function (){
		var email=$("#email_Update_input").val();
		var refEmail=/(^[a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6}$)/;
		if(!refEmail.test(email)){
			show_validata_msg("#email_Update_input","error","邮箱格式不正确");
			return false;
		}else{
			show_validata_msg("#email_Update_input","success","");
		}  
		var cmpId=$("#emp_uodata_btn1").attr("edit_id");
		/* alert(edit_ages); */
		 $.ajax({
			url:"${App_Path}/"+cmpId+"/uodateEmp.do",
			type:"POST",
			data:$("#empUpdateModal form").serialize(),
			success:function(result){
				if(result.code==100){
					//alert(result.msg);
					$('#empUpdateModal').modal('hide')
					to_page(currentPage);
				}else{
					show_validata_msg("#email_input_input","success",result.extend.fieldError.email); 
				} 
			}
		});
	});
	//完成全选/全不选功能
	$("#check_all").click(function (){
		//获取原生的属性用prop
		//prop修改和读原生的属性
		$(this).prop("checked")
		$(".check_itme").prop("checked",$(this).prop("checked"));
		
	});
	//判断字符串是否为空
	function eqNull(empty){
		var emp=$.trim(empty);  
		if(emp==null||emp==''){
			return false;
		}
		return true;
	}
	//给分页显示的所有选择框加都选，上方的选择框选中
	 $(document).on("click",".check_itme",function (){
	 	var fale=$(".check_itme:checked").length==$(".check_itme").length;
	 	$("#check_all").prop("checked",fale);	
	 });
	//点击表单的删除按钮，散出勾选的选择框
	 $("#emps_delect_all_but").click(function (){
	 var empName=$(this).parents("tr").find("td:eq(1)").text();
	 	var emps="";
	 	var del_idstr="";
	 	$.each($(".check_itme:checked"),function(index,itme){
	 		emps+=$(this).parents("tr").find("td:eq(2)").text()+",";
	 		del_idstr+=$(this).parents("tr").find("td:eq(1)").text()+"-"
	 	});
	 	var empNames=emps.substring(0,emps.length-1);
	 	var del_idstrs=del_idstr.substring(0,del_idstr.length-1);
	 	//alert(del_idstrs);
	 	if(!eqNull(del_idstrs)){
	 		return false;
	 	}
	 	
	 	if(confirm("确认删除【"+empNames+"】吗？")){
	 		$.ajax({
	 			url:"${App_Path}/delectEmps.do",
				type:"POST",
				data:"ids="+del_idstrs,
				success:function(result){
					if(result.code==100){
						alert("删除成功");
						to_page(1);
					}
				}
	 		})
	 	}
	 });
 }