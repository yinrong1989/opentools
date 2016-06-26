package com.yinrong.tools.util;

import java.util.HashMap;
import java.util.Map;

public class HttpUrlUtil {
	public final static String SPLITSTR="!";
	public final static String EXTENSION=".htm";
	public final static String ACTION="action";
	public final static String METHOD="method";
	public static Map<String,String> getHttpUrl(String url){
		Map<String,String> map= new HashMap<String, String>();
		if(StringUtils.isNotBlank(url)){
			url=url.replace(EXTENSION, "");
			String[] s=url.split(SPLITSTR);
			if(null!=s){
				map.put(ACTION, s[0]);
				if(s.length==2){
					map.put(METHOD, s[1]);
				}else{
					map.put(METHOD, "");
				}
			}
		}
		return map;
	}
}
