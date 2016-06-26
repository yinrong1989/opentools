package com.yinrong.tools.util;

import java.util.HashMap;
import java.util.Map;

import com.yinrong.tools.OpenConstants;

/**
 *  @author SongZhiQiang
 */
public class ApiUtil {
	public final static String SPLITSTR = "-";
	public final static String APICODE = "appapiCode";
	public final static String VERSION = "appapiVersion";
	public final static String ROUTER = "routerDire";

	public static Map<String, String> getApiAndVersion(String url) {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isNotBlank(url)) {
			String[] s = url.split(SPLITSTR);
			if (null != s) {
				map.put(APICODE, s[0]);
				if (s.length == 3) {
					map.put(VERSION, s[1]);
					map.put(ROUTER, s[2]);
				} else {
					map.put(VERSION, OpenConstants.DEFAULT_VERSION);
					map.put(ROUTER, OpenConstants.ROUTER_DIRE_0);
				}
			}
		}
		return map;
	}
	public static Map<String, String> getApiMap(String url) {
		return getApiAndVersion(url);
	}
	public static String getApi(String url) {
		Map<String, String> map = getApiAndVersion(url);
		return map.get(APICODE);
	}
	public static String getVersion(String url) {
		Map<String, String> map = getApiAndVersion(url);
		return map.get(VERSION);
	}
	public static String getRouterDire(String url) {
		Map<String, String> map = getApiAndVersion(url);
		return map.get(ROUTER);
	}
} 
