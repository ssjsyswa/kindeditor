package com.shyk.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shyk.pojo.PicUrl;
import com.shyk.util.MapCacheManager;

@Service
public class IndexService {
	@Value("${FILEPATH}")
	private String FILEPATH;

	public List<PicUrl> getData(String filepath) throws Exception {

		MapCacheManager cache = MapCacheManager.getInstance();
		Map<String, Object> mapCache = cache.getMapCache();
		Object object = mapCache.get("url");
		if(cache.getMapCache().get("url") == null){
		
		File file = new File(filepath + "\\" + FILEPATH);
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] name = { "MENU_ID", "MENU_NAME", "FATHER_MENU_ID", "URL",
				"ORDER_ID", "LEVEL_ID", "QY", "BZ" };
		Integer[] type = { 1, 1, 1, 1, 1, 1, 1, 1 };

		ExcelData excel = new ExcelData();
		// 存入文件列名,必填项
		excel.setColnumName(name);
		// 使用getExcelObj方法必须设置
		excel.setDTO(new PicUrl());

		excel.setColnumType(type);
		// 使用第二种方法直接获取List<Object>
		List<Object> list = excel.getExcelObj(file, 0, 1);
		List<PicUrl> nodes = getNodes(list);
		cache.putCache("url", nodes);
		return nodes;
		}
		else{
			return (List<PicUrl>) cache.getMapCache().get("url");
		}

	}

	private List<PicUrl> getNodes(List<Object> list) {
		List<PicUrl> picList = new LinkedList<>();
		for (Object o : list) {
			PicUrl t = (PicUrl) o;
		/*	System.out.println("1:" + t.getMENU_NAME() + ",2:" + t.getMENU_ID()
					+ ",3:" + t.getURL() + "4" + t.getORDER_ID());*/
			if (t.getFATHER_MENU_ID().equals("0"))
				picList.add(findChildren(t, list));
		}
		return picList;
	}

	private PicUrl findChildren(PicUrl t, List<Object> list) {
		for (Object o : list) {
			PicUrl p = (PicUrl) o;
			if (t.getMENU_ID().equals(p.getFATHER_MENU_ID())) {
				if (t.getChilden() == null) {
					t.setChilden(new ArrayList<PicUrl>());
				}
				t.getChilden().add(findChildren(p, list));
			}
		}
		return t;
	}

}
