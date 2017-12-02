package com.shyk.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.shyk.pojo.PicUrl;
import com.shyk.service.IndexService;
import com.shyk.util.JSONUtils;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
@Controller
public class ServiceController {
	@Autowired
	private IndexService  indexService;
	
	private String prefix = "D:/";
	
	@RequestMapping("/")
	public ModelAndView seachList(Model model,HttpServletRequest request) throws Exception{
	/*	String path = ServiceController.class.getClassLoader().getResource("/").getPath().replace("classes", "Excel");
		List<PicUrl> dat = indexService.getData(path);
		
		//封装成json 
		String listToJson = JSONUtils.ListToJson(dat);
		String data = listToJson.replaceAll("MENU_NAME", "text").replaceAll("childen", "nodes").replaceAll("URL", "href");
//		"nodes":[],
		data = data.replaceAll("\"nodes\":\\[\\],", "");
		System.out.println(data);
		
		map.put("data", data);*/
		ModelAndView  modelAndView = new ModelAndView( );
		modelAndView.setViewName("/item-add");
		modelAndView.addObject("data", "<img src=\"/Big_Screen_Web/img/1sda.jpg\" alt=\"\" />让他引入让他引入同意");
//		map.put("data", "<img src=\"/Big_Screen_Web/img/1sda.jpg\" alt=\"\" />让他引入让他引入同意");
		return modelAndView;
	}
	
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String upload(HttpServletRequest request,MultipartFile uploadFile){
		//map 不能固定泛型，error 的类型如为字符串 js 会接不到返回的数据
		Map map = new HashMap();
		try {
			String schema = request.getScheme();
	        String serverName = request.getServerName();
	        // 端口号返回的是int类型
	        int serverPort = request.getServerPort();
	        String contentPath = request.getContextPath();
//	        String servletPath = request.getServletPath();
	        String realPath = schema+"://"+serverName+":"+serverPort;
//	        String realPath2 =  request.getSession().getServletContext().getRealPath("/");
	        String realPath2 ="D:/file/";
			String name = uploadFile.getOriginalFilename();
		//	String name = uploadFile.getName(); 获得了uploadFile
			
			String suffix = name.substring(name.lastIndexOf(".")+1);
			
			File  file= new File(realPath2/*+"/img/"*/+"1sda."+suffix);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(uploadFile.getBytes());
//			String path = client.uploadFile(uploadFile.getBytes(),suffix);
			String absolutePath = file.getAbsolutePath();
//			String url=prefix+path;
			map.put("error", 0);
			String n = realPath+"/file"+"/1sda."+suffix;
			map.put("url",n);
			return JsonUtils.objectToJson(map);
		} catch (Exception e) {
			e.printStackTrace();
			
			map.put("error", 1);
			map.put("message", "上传失败");
			return JsonUtils.objectToJson(map);
		}
		
		
		
		
	}
	
	@RequestMapping("/item/save")
	@ResponseBody
	public TaotaoResult submit(String desc){
		 TaotaoResult result =new TaotaoResult();
		 //这里写的不好,catch 不到
		try{
		
		 result.setStatus(200);
		 return result;
		}catch( Exception e)
		{
			e.printStackTrace();
			result.setStatus(500);
			return result;
		}
		//map 不能固定泛型，error 的类型如为字符串 js 会接不到返回的数据
	
	}
}
