/**
 * 
 */
package com.yinrong.tools.util;

/**
 * @Project vfinance-open-tool
 * @Description
 * @Company fujie
 * @Create 2015-1-22
 * @author linqingyou
 */
public class TokenUtil {

	public final static String APPEND_CHAR = "-";

	public static String genToken(Object... values) {
		if (values == null) {
			return null;
		}
		StringBuilder buff = new StringBuilder();
		for (Object value : values) {
			buff.append(value).append(APPEND_CHAR);
		}
		buff.deleteCharAt(buff.length() - 1);
		return buff.toString();
	}

	private TokenUtil() {
	}
}
