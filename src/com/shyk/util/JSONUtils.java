package com.shyk.util;

import java.util.List;

import com.google.gson.Gson;

public class JSONUtils {
	private static Gson gson = new Gson();
	public static String ListToJson(List<?> list){
		String json = gson.toJson(list);
		return json;
	}
}
