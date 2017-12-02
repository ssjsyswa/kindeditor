package com.shyk.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.shyk.service.ExcelObj;

public class PicUrl extends ExcelObj implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String getFATHER_MENU_ID() {
		return FATHER_MENU_ID;
	}
	public void setFATHER_MENU_ID(String fATHER_MENU_ID) {
		FATHER_MENU_ID = fATHER_MENU_ID;
	}
	private String MENU_ID ;
	private String MENU_NAME;
	private String FATHER_MENU_ID;
	private String URL;
	private String ORDER_ID;
	private String LEVEL_ID;
	private String QY;
	private String	BZ;
	private List<PicUrl> childen = new ArrayList<>();
	public  String getMENU_ID() {
		return MENU_ID;
	}
	public void setMENU_ID(String mENU_ID) {
		MENU_ID = mENU_ID;
	}
	public String getMENU_NAME() {
		return MENU_NAME;
	}
	public void setMENU_NAME(String mENU_NAME) {
		MENU_NAME = mENU_NAME;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getORDER_ID() {
		return ORDER_ID;
	}
	public void setORDER_ID(String oRDER_ID) {
		ORDER_ID = oRDER_ID;
	}
	public String getLEVEL_ID() {
		return LEVEL_ID;
	}
	public void setLEVEL_ID(String lEVEL_ID) {
		LEVEL_ID = lEVEL_ID;
	}
	public String getQY() {
		return QY;
	}
	public void setQY(String qY) {
		QY = qY;
	}
	public String getBZ() {
		return BZ;
	}
	public void setBZ(String bZ) {
		BZ = bZ;
	}
	public List<PicUrl> getChilden() {
		return childen;
	}
	public void setChilden(List<PicUrl> childen) {
		this.childen = childen;
	}

}
