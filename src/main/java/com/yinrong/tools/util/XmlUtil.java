package com.yinrong.tools.util;

import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yinrong.tools.OpenConstants;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


/************************************************
 * Copyright (c)  by goldensoft
 * All right reserved.
 * Create Date: 2009-8-15
 * Create Author: liurong
 * File Name:  josn工具
 * Last version:  1.0
 * Function:这里写注释
 * Last Update Date:
 * Change Log:
**************************************************/ 
 
public class XmlUtil {
	private static XmlUtil allXmlUtil;
	private static XmlUtil notNullXmlUtil;
	private static XmlUtil notDefXmlUtil;
	private static XmlUtil notEmpXmlUtil;
    public static XmlUtil getAllXmlUtil() {
		return allXmlUtil;
	}
	public static void setAllXmlUtil(XmlUtil allXmlUtil) {
		XmlUtil.allXmlUtil = allXmlUtil;
	}
	public static XmlUtil getNotNullXmlUtil() {
		return notNullXmlUtil;
	}
	public static void setNotNullXmlUtil(XmlUtil notNullXmlUtil) {
		XmlUtil.notNullXmlUtil = notNullXmlUtil;
	}
	public static XmlUtil getNotDefXmlUtil() {
		return notDefXmlUtil;
	}
	public static void setNotDefXmlUtil(XmlUtil notDefXmlUtil) {
		XmlUtil.notDefXmlUtil = notDefXmlUtil;
	}
	public static XmlUtil getNotEmpXmlUtil() {
		return notEmpXmlUtil;
	}
	public static void setNotEmpXmlUtil(XmlUtil notEmpXmlUtil) {
		XmlUtil.notEmpXmlUtil = notEmpXmlUtil;
	}
	private ObjectMapper mapper;
    public ObjectMapper getMapper() {
        return mapper;
    }
    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }
    public XmlUtil(Include include){
        mapper = new XmlMapper(); 
        mapper.setSerializationInclusion(include); 
       //设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性 
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
        //禁止使用int代表Enum的order()來反序列化Enum,非常危險 
        mapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true); 
        setDateFormat(OpenConstants.DATE_TIME_FORMAT);
         
    }
    /**
     * 创建输出全部属性
     * @return
     */
    public static XmlUtil buildNormalBinder(){
    	synchronized (XmlUtil.class) {
			if(XmlUtil.allXmlUtil ==null){
				XmlUtil.allXmlUtil=new XmlUtil(Include.ALWAYS);
			}
		}
        return XmlUtil.allXmlUtil;
    }
    /**
     * 创建只输出非空属性的
     * @return
     */
    public static XmlUtil buildNonNullBinder(){
        synchronized (XmlUtil.class) {
			if(XmlUtil.notNullXmlUtil ==null){
				XmlUtil.notNullXmlUtil=new XmlUtil(Include.NON_NULL);
			}
		}
        return XmlUtil.notNullXmlUtil;
    }
    /**
     * 创建只输出初始值被改变的属性
     * @return
     */
    public static XmlUtil buildNonDefaultBinder(){
        synchronized (XmlUtil.class) {
			if(XmlUtil.notDefXmlUtil ==null){
				XmlUtil.notDefXmlUtil=new XmlUtil(Include.NON_DEFAULT);
			}
		}
        return XmlUtil.notDefXmlUtil;
    }
    /**
     * 创建只输出初始值被改变的属性
     * @return
     */
    public static XmlUtil buildNonEmptyBinder(){
        synchronized (XmlUtil.class) {
			if(XmlUtil.notEmpXmlUtil ==null){
				XmlUtil.notEmpXmlUtil=new XmlUtil(Include.NON_EMPTY);
			}
		}
        return XmlUtil.notEmpXmlUtil;
    }

    /**
     * 把json字符串转成对象
     * @param json
     * @param clazz
     * @return
     */
    public <T> T getJsonToObject(String json,Class<T> clazz){
        T object=null;
        if(StringUtils.isNotBlank(json)) {
            try {
                object=getMapper().readValue(json, clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return object;
    }
    /**
     * 把JSON转成list
     * @param json
     * @param clazz
     * @return
     */
    @SuppressWarnings({ "rawtypes" })
    public Object getJsonToList(String json,Class clazz){
        Object object=null;
        if(StringUtils.isNotBlank(json)) {
           try {
                object=getMapper().readValue(json,TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, clazz));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return object;
    }
    @SuppressWarnings({ "rawtypes" })
    public Object[] getJsonToArray(String json,Class clazz){
    	Object[] object=null;
        if(StringUtils.isNotBlank(json)) {
           try {
                object=getMapper().readValue(json,TypeFactory.defaultInstance().constructArrayType(clazz));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return object;
    }
    public byte[] getJsonTobyteArray(String json){
    	byte[] object=null;
        if(StringUtils.isNotBlank(json)) {
           try {
                object=getMapper().readValue(json,TypeFactory.defaultInstance().constructArrayType(byte.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return object;
    }
    /**
     * 把JSON转成Map
     * @param json
     * @param keyclazz
     * @param valueclazz
     * @return
     */
    @SuppressWarnings({ "rawtypes"})
    public Object getJsonToMap(String json,Class keyclazz,Class valueclazz){
        Object object=null;
        if(StringUtils.isNotBlank(json)) {
            try {
                object=getMapper().readValue(json,TypeFactory.defaultInstance().constructParametricType(HashMap.class, keyclazz,valueclazz));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return object;
    }

    /**
     * 把json格式数据装成map
     * @param str
     * @return
     */
    public static Map<String,String> getJsonToMap(String str){
        Map<String,String> map = new HashMap<String, String>();
        if(StringUtils.isNotBlank(str)){
            String[] s=str.split(",");
            if(s.length>0){
                for (int i = 0; i < s.length; i++) {
                    String con=s[i];
                    int s1=con.indexOf(":");
                    if(s1>0){
                        map.put(con.substring(0,s1).trim().replace("\"", ""), con.substring(s1+1).replace("\"", ""));
                    }else{
                        map.put(con.trim().replace("\"", ""), "");
                    }
                }
            }
        }
        return map;
    }

    /**
     * 把map转成combo数据格式的json格式
     * @return String (json)
     */
    public String getMapToJson(Map<String,String> map) {
        List<String[]> list =new ArrayList<String[]>();
        if (null != map && !map.isEmpty()) {
            for (String key : map.keySet()) {
                String[] strS = new String[2];
                strS[0] = key;
                strS[1] = map.get(key);
                list.add(strS);
            }
        }
        return jsonObject(list);
    }

    /**
     * 把对象转成json格式
     * @param obj 需要转的对象
     * @return String
     */
    @SuppressWarnings("rawtypes")
	public String jsonObject(List list) {
        StringWriter sw = new StringWriter();
        JsonGenerator gen;
        try {
            gen = new JsonFactory().createGenerator(sw);
            getMapper().writeValue(gen, list);
            gen.close();
        } catch (Exception e) {
            
        }
        return sw.toString();
    }
    
    /**
     * 把JSON转成Object
     * @param json
     * @param keyclazz
     * @param valueclazz
     * @return
     */
    @SuppressWarnings({ "rawtypes" })
    public Object getJsonToObject(String json,Class objclazz,Class ...pclazz){
        Object object=null;
        if(StringUtils.isNotBlank(json)) {
            try {
                object=getMapper().readValue(json,TypeFactory.defaultInstance().constructParametricType(objclazz, pclazz));
            } catch (Exception e) {
            }
        }
        return object;
    }
    /**
     * 把对象转成字符串
     * @param object
     * @return
     */
    public String toJson(Object object){
        String json=null;
        try {
            json=getMapper().writeValueAsString(object);
        }  catch (Exception e) {
        	e.printStackTrace();
        }
        return json;
    }
    /**
     * 设置日期格式
     * @param pattern
     */
    public void setDateFormat(String pattern){
        if(StringUtils.isNotBlank(pattern)){
            DateFormat df=new SimpleDateFormat(pattern);
            getMapper().setDateFormat(df);
        }
    }
    public static void main(String[] args){
    }
}