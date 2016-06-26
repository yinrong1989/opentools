package com.yinrong.tools.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class FlowUtil {
	private static Field[] fafiled;
	public static void main(String[] args) {
		objectToJson("com.soft.bc.financing.fingoods.vo.FinResourceGoodsVo","FinImpCode");
	}
	/***
	 * @Description:将对应实体转为相应json字符串
	 * @param classPath
	 * @param paramName
	 * @author  szq  2012-12-19 insert
	 */
	public static void objectToJson(String classPath,String paramName){
	    Class<?> onwClass = null;
		try {
			onwClass = Class.forName(classPath);
		} catch (Exception e) {
			System.out.println("反射失败");
			e.printStackTrace();
		}
		/**递归获得类中所有的属性值 包括父类**/
		getFatherField(onwClass);
		
		Field[] filed = fafiled;
		String str = "{";
		Map<String,String> map = new HashMap<String,String>();
		for (int i = 0; i < filed.length; i++) {
			if(StringUtils.isBlank(map.get(filed[i].getName()))){
				if (StringUtils.isNotBlank(paramName)) {
					if (filed[i].getType().equals(Double.class) || filed[i].getType().equals(Long.class) ) {
						str += "'"+filed[i].getName()+"':{"+paramName+"." + filed[i].getName() + "},";
					}else {
						str += "'"+filed[i].getName()+"':'{"+paramName+"." + filed[i].getName() + "}',";
					}
				}else {
					if (filed[i].getType().equals(Double.class) || filed[i].getType().equals(Long.class) ) {
						str += "'"+filed[i].getName()+"':{" + filed[i].getName() + "},";
					}else {
						str += "'"+filed[i].getName()+"':'{" + filed[i].getName() + "}',";
					}
				}
				map.put(filed[i].getName(),filed[i].getName());
			}
		}
		str = str.substring(0, str.length()-1);
		str+="}";
		System.out.println(str);
		
		map.clear();
		str = "{";
		for (int i = 0; i < filed.length; i++) {
			if (StringUtils.isNotBlank(paramName)) {
				if(StringUtils.isBlank(map.get(filed[i].getName()))){
					str += "\""+paramName+"."+filed[i].getName()+"\":{\"arg\":null,\"value\":\""+paramName+"."+filed[i].getName()+"\",\"shortname\":null,\"type\":\"1\"},";
					map.put(filed[i].getName(),filed[i].getName());
				}
			}else {
				if(StringUtils.isBlank(map.get(filed[i].getName()))){
					str += "\""+filed[i].getName()+"\":{\"arg\":null,\"value\":\""+filed[i].getName()+"\",\"shortname\":null,\"type\":\"1\"},";
					map.put(filed[i].getName(),filed[i].getName());
				}
			}
		}
		str = str.substring(0, str.length()-1);
		str+="}";
		System.out.println(str);
    }
	/***
	 * @Description:递归获得所有属性
	 * @param onwClass
	 * @author  szq  2012-12-19 insert
	 */
	private static void getFatherField(Class<?> onwClass){
		Field[] sunFiled = onwClass.getDeclaredFields();
		if(null == fafiled){
			fafiled =  sunFiled;
		}else{
			fafiled = add(fafiled,sunFiled);
		}
		if(null != onwClass.getSuperclass()){
			getFatherField(onwClass.getSuperclass());
		}
	}
	/**
	 * @Description:数组叠加
	 * @param list1
	 * @param list2
	 * @return
	 * @author  szq  2012-12-19 insert
	 */
	private static Field [] add(Field[] list1,Field[] list2){
		Field [] filedSize = new Field[list1.length+list2.length];
		System.arraycopy(list1,0, filedSize,0,list1.length);
		System.arraycopy(list2,0, filedSize,list1.length,list2.length);
		return filedSize;
	}
}
