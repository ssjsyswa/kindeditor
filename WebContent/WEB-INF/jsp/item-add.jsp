<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link href="<%=request.getContextPath() %>/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/js/common.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="itemAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>商品描述:</td>
	            <td>
	                <textarea style="width:800px;height:300px;visibility:hidden;" name="desc" >${data }</textarea>
	            </td>
	        </tr>
	    </table>
	    <!-- <input type="hidden" name="itemParams"/> -->
	</form>
	<div style="display:none;">${data}</div>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	var itemAddEditor ;
	 
	//页面初始化完毕后执行此方法
	$(function(){
		
		 var desc =  $("#ht").text();
		  alert(desc)
		//创建富文本编辑器
		
		itemAddEditor = TAOTAO.createEditor("#itemAddForm [name=desc]");
		  /* itemAddEditor.html("123123") */
	});
	//提交表单
	function submitForm(){
		alert($("#itemAddForm").serialize());
		//同步文本框中的商品描述
		itemAddEditor.sync();
		$.post(getProject()+"/item/save",$("#itemAddForm").serialize(), function(data){
			
			if(data.status == 200){
				$.messager.alert('提示','新增商品成功!');
			}
		});
	}
	
	function clearForm(){
		$('#itemAddForm').form('reset');
		itemAddEditor.html('');
	}
</script>
