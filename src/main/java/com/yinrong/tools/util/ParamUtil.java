package com.yinrong.tools.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParamUtil {
	private String value;//值
	private String type;//类型 0 OBJECT 1 LIST
	private String key; //当前定义对象，用于嵌套
	private String nextkey;//下一级对象，用于嵌套
	private String tablename;//type=1 2对应指定list对应的对应关键名
	private String lisType;//type=1 2 对应指定list 对应循环次数，0 按tablename处理  1固定次数  2 按指定对象（LIST 或OBJECT）固定次数 
	private String lisNum;//type=1 2对应指定list 对应循环次数，固定次数
	private String lisStart;//list 循环开始时，外面是否需要+字符串，默认是[
	private String lisEnd;//list 循环结束时，外面是否需要+字符串，默认是]
	private String lisCend;//list 循环中间时，外面是否需要+字符串，默认是,
	private List<ChParamUtil> chParamUtilList;
	private List<ConUtil> conUtilList;
	private Map<String,ComUtil> comUtilMap;//获取额外数据源
	
	public Map<String, ComUtil> getComUtilMap() {
		return comUtilMap;
	}
	public void setComUtilMap(Map<String, ComUtil> comUtilMap) {
		this.comUtilMap = comUtilMap;
	}

	private String emFlag;//解析对象中无数据时候处理方式   默认 0 不影响继续处理   1 直接返回空
	
	public String getEmFlag() {
		return emFlag;
	}
	public void setEmFlag(String emFlag) {
		this.emFlag = emFlag;
	}
	public List<ChParamUtil> getChParamUtilList() {
		return chParamUtilList;
	}
	public void setChParamUtilList(List<ChParamUtil> chParamUtilList) {
		this.chParamUtilList = chParamUtilList;
	}
	public String getLisCend() {
		return lisCend;
	}
	public void setLisCend(String lisCend) {
		this.lisCend = lisCend;
	}
	public String getLisStart() {
		return lisStart;
	}
	public void setLisStart(String lisStart) {
		this.lisStart = lisStart;
	}
	public String getLisEnd() {
		return lisEnd;
	}
	public void setLisEnd(String lisEnd) {
		this.lisEnd = lisEnd;
	}

	public List<ConUtil> getConUtilList() {
		return conUtilList;
	}
	public void setConUtilList(List<ConUtil> conUtilList) {
		this.conUtilList = conUtilList;
	}
	public String getLisNum() {
		return lisNum;
	}
	public void setLisNum(String lisNum) {
		this.lisNum = lisNum;
	}
	public String getLisType() {
		return lisType;
	}
	public void setLisType(String lisType) {
		this.lisType = lisType;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getNextkey() {
		return nextkey;
	}
	public void setNextkey(String nextkey) {
		this.nextkey = nextkey;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public static void main(String[] args) {
	    ParamUtil paramUtil = new ParamUtil();
	    
	    paramUtil.setType("1");
	    List<ConUtil> conUtilList = new ArrayList<ConUtil>();
	    ConUtil conUtil = new ConUtil();
	    conUtil.setArg("aaaa");
	    conUtil.setMsg("bbbb");
	    conUtil.setShortname("cccc");
	    conUtil.setValue("dddd");
	    
	    conUtilList.add(conUtil);
	    
	    conUtil = new ConUtil();
        conUtil.setArg("eeee");
        conUtil.setMsg("rrrr");
        conUtil.setShortname("tttt");
        conUtil.setValue("yyyy");
        conUtilList.add(conUtil);
        
        paramUtil.setConUtilList(conUtilList);
        
        paramUtil.setTablename("444444444444");
        
        System.out.println(JsonUtil.buildNormalBinder().toJson(paramUtil));
    }
}
