package com.shyk.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.shyk.pojo.DemoBean;
import com.shyk.service.IndexService;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
@Controller
public class ServiceController {
	@Autowired
	private IndexService  indexService;
	
	private String prefix = "D:/";
	//添加到remarkTo.htm里 
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
		DemoBean demoBean = new DemoBean();
		demoBean.setId(33+"");
		demoBean.setDesc("<img src=\"http://localhost:8080/file/1sda.png\" alt=\"\" />让他引入让他引入同意");
		modelAndView.addObject("data", demoBean);
//		map.put("data", "<img src=\"/Big_Screen_Web/img/1sda.jpg\" alt=\"\" />让他引入让他引入同意");
		return modelAndView;
	}
	public  String getRandomFileName() {  
		  
        SimpleDateFormat simpleDateFormat;  
  
        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");  
  
        Date date = new Date();  
  
        String str = simpleDateFormat.format(date);  
  
        Random random = new Random();  
  
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数  
  
        return rannum + str;// 当前时间  
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
	public TaotaoResult submit(DemoBean bean ){
		 TaotaoResult result =new TaotaoResult();
		 //存入数据库
		 //这里写的不好,catch 不到
		 System.out.println(bean.getDesc());
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
