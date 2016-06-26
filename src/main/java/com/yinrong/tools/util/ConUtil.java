package com.yinrong.tools.util;

import java.util.Map;

public class ConUtil {
	private String arg;
	private String value;
	private String shortname;
	private String msg;
	private String dataType;
	private String dataUrl;
	private String dataCharset;
	private String signName;
	private String signParam;
	private String json;
	private String retValue;//获取返回对象里的属性，为空直接返回对象STRING
	public String getRetValue() {
		return retValue;
	}
	public void setRetValue(String retValue) {
		this.retValue = retValue;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDataUrl() {
		return dataUrl;
	}
	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}
	public String getDataCharset() {
		return dataCharset;
	}
	public void setDataCharset(String dataCharset) {
		this.dataCharset = dataCharset;
	}
	public String getSignName() {
		return signName;
	}
	public void setSignName(String signName) {
		this.signName = signName;
	}
	public String getSignParam() {
		return signParam;
	}
	public void setSignParam(String signParam) {
		this.signParam = signParam;
	}
	private Map<String,ComUtil> othercomMap;
	public Map<String, ComUtil> getOthercomMap() {
		return othercomMap;
	}
	public void setOthercomMap(Map<String, ComUtil> othercomMap) {
		this.othercomMap = othercomMap;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getArg() {
		return arg;
	}
	public void setArg(String arg) {
		this.arg = arg;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
}
