package com.yinrong.tools.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 系统工具类。
 * 
 */
public abstract class OpenUtils {
	private static String localIp;

	private static List<String> allIps;

	private OpenUtils() {
	}

	/**
	 * 获取文件的真实后缀名。目前只支持JPG, GIF, PNG, BMP四种图片文件。
	 * 
	 * @param bytes
	 *            文件字节流
	 * @return JPG, GIF, PNG or null
	 */
	public static String getFileSuffix(byte[] bytes) {
		if (bytes == null || bytes.length < 10) {
			return null;
		}

		if (bytes[0] == 'G' && bytes[1] == 'I' && bytes[2] == 'F') {
			return "GIF";
		} else if (bytes[1] == 'P' && bytes[2] == 'N' && bytes[3] == 'G') {
			return "PNG";
		} else if (bytes[6] == 'J' && bytes[7] == 'F' && bytes[8] == 'I' && bytes[9] == 'F') {
			return "JPG";
		} else if (bytes[0] == 'B' && bytes[1] == 'M') {
			return "BMP";
		} else {
			return null;
		}
	}

	/**
	 * 获取文件的真实媒体类型。目前只支持JPG, GIF, PNG, BMP四种图片文件。
	 * 
	 * @param bytes
	 *            文件字节流
	 * @return 媒体类型(MEME-TYPE)
	 */
	public static String getMimeType(byte[] bytes) {
		String suffix = getFileSuffix(bytes);
		String mimeType;

		if ("JPG".equals(suffix)) {
			mimeType = "image/jpeg";
		} else if ("GIF".equals(suffix)) {
			mimeType = "image/gif";
		} else if ("PNG".equals(suffix)) {
			mimeType = "image/png";
		} else if ("BMP".equals(suffix)) {
			mimeType = "image/bmp";
		} else {
			mimeType = "application/octet-stream";
		}

		return mimeType;
	}

	@SuppressWarnings("rawtypes")
	public static Class classForName(String classPath) throws ClassNotFoundException {
		if (StringUtils.isBlank(classPath))
			return null;
		if ("boolean".equals(classPath)) {
			return boolean.class;
		} else if ("byte".equals(classPath)) {
			return byte.class;
		} else if ("char".equals(classPath)) {
			return char.class;
		} else if ("short".equals(classPath)) {
			return short.class;
		} else if ("int".equals(classPath)) {
			return int.class;
		} else if ("long".equals(classPath)) {
			return long.class;
		} else if ("float".equals(classPath)) {
			return float.class;
		} else if ("double".equals(classPath)) {
			return double.class;
		} else if ("[Ljava.lang.String".equals(classPath)) {
			return String[].class;
		} else if ("[Ljava.lang.Byte".equals(classPath)) {
			return Byte[].class;
		} else if ("[Ljava.lang.Double".equals(classPath)) {
			return Double[].class;
		} else if ("[Ljava.lang.Long".equals(classPath)) {
			return Long[].class;
		} else if ("[Ljava.lang.Short".equals(classPath)) {
			return Short[].class;
		} else if ("[Ljava.lang.Date".equals(classPath)) {
			return Date[].class;
		} else if ("[Ljava.lang.Integer".equals(classPath)) {
			return Integer[].class;
		} else if ("[Ljava.lang.Boolean".equals(classPath)) {
			return Boolean[].class;
		} else if ("[Ljava.lang.Number".equals(classPath)) {
			return Number[].class;
		} else if ("[Ljava.lang.Float".equals(classPath)) {
			return Float[].class;
		} else if ("[Ljava.lang.Character".equals(classPath)) {
			return Character[].class;
		} else {
			return Class.forName(classPath);
		}
	}

	/**
	 * 清除字典中值为空的项。
	 * 
	 * @param <V>
	 *            泛型
	 * @param map
	 *            待清除的字典
	 * @return 清除后的字典
	 */
	public static <V> Map<String, V> cleanupMap(Map<String, V> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}

		Map<String, V> result = new HashMap<String, V>(map.size());
		Set<Entry<String, V>> entries = map.entrySet();

		for (Entry<String, V> entry : entries) {
			if (entry.getValue() != null) {
				result.put(entry.getKey(), entry.getValue());
			}
		}

		return result;
	}

	public static String getLoaclHostname() {
		String address = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			address = addr.getHostName();// 获得本机名称
		} catch (Exception e) {
		}
		return address;
	}

	/**
	 * 获取本机的网络IP
	 */
	public static String getLocalNetWorkIp() {
		if (localIp != null) {
			return localIp;
		}
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (netInterfaces.hasMoreElements()) {// 遍历所有的网卡
				NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
				if (ni.isLoopback() || ni.isVirtual()) {// 如果是回环和虚拟网络地址的话继续
					continue;
				}
				Enumeration<InetAddress> addresss = ni.getInetAddresses();
				while (addresss.hasMoreElements()) {
					InetAddress address = addresss.nextElement();
					if (address instanceof Inet4Address) {// 这里暂时只获取ipv4地址
						ip = address;
						break;
					}
				}
				if (ip != null) {
					break;
				}
			}
			if (ip != null) {
				localIp = ip.getHostAddress();
			} else {
				localIp = "127.0.0.1";
			}
		} catch (Exception e) {
			localIp = "127.0.0.1";
		}
		return localIp;
	}

	public static List<String> getAllNetWorkIp() {
		if (allIps != null) {
			return allIps;
		}
		List<String> ips = new LinkedList<String>();
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {// 遍历所有的网卡
				NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
				if (ni.isLoopback() || ni.isVirtual()) {// 如果是回环和虚拟网络地址的话继续
					continue;
				}
				Enumeration<InetAddress> addresss = ni.getInetAddresses();
				while (addresss.hasMoreElements()) {
					InetAddress address = addresss.nextElement();
					if (address instanceof Inet4Address) {// 这里暂时只获取ipv4地址
						ips.add(address.getHostAddress());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ips.add("127.0.0.1");
		}
		allIps = ips;
		return ips;
	}
	
	public static void main(String[] args) {
		System.out.println(getAllNetWorkIp());
	}

}
