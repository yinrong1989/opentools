package com.yinrong.tools.util;

import java.util.Map;

public class ComUtil extends ConUtil{
	private String type;
	private String tagname;//嵌套循环对应的nextkey
	private String objName;//嵌套循环数据源属性名称
	private String objKey;//嵌套数据源存放到MAP中的KEY
	private Map<String,ComUtil> commap;//按解析tabname区分对照
	public Map<String, ComUtil> getCommap() {
		return commap;
	}
	public void setCommap(Map<String, ComUtil> commap) {
		this.commap = commap;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public String getObjName() {
		return objName;
	}
	public void setObjName(String objName) {
		this.objName = objName;
	}
	public String getObjKey() {
		return objKey;
	}
	public void setObjKey(String objKey) {
		this.objKey = objKey;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
