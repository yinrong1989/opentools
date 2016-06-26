package com.yinrong.tools.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * <p>
 * Double类型数据格式化及乘、除计算
 * </p>
 */
public class DoubleUtil {
	/**
	 *重量显示格式
	 */
	private static final String WEIGHTFORMAT = "##,###,###,##0.000";

	/**
	 * 重量输入格式
	 */
	private static final String WEIGHTFORMATINPUT = "##########0.000";
	/**
	 * 理算系数输入格式
	 */
	private static final String QUOTIETYINPUT = "##########0.000000";
	/**
	 * 数量显示格式
	 */
	private static final String NUMBER = "##,###,###,##0";

	/**
	 * 数量输入格式
	 */
	private static final String NUMBERINPUT = "##########0";

	/**
	 * 价格显示格式
	 */
	private static final String PRICE = "##,###,###,##0.00";

	/**价格输入格式
	 * 
	 */
	private static final String PRICEINPUT = "##########0.00";

	/**
	 * 格式化重量,只是显示,不能输入
	 * 
	 * @param d
	 *            重量
	 * @param partsname
	 *            品名
	 * @return
	 */
	public static String convertToWeight(Double d,String partsname) {
		if (null == d) {
			d = 0D;
		}
		String s = convertDoubleFormat(d, WEIGHTFORMAT);
		return s;
	}

	/**
	 * 格式化重量,可以做为输入
	 * 
	 * @param d
	 * @param partsname
	 * @return
	 */
	public static String convertToWeightInput(Double d,String partsname) {
		if (null == d) {
			d = 0D;
		}
		String s = convertDoubleFormat(d, WEIGHTFORMATINPUT);
		return s;
	}

	/**
	 * 格式化数量,只是显示,不能输入
	 * 
	 * @param d
	 *            数量
	 * @param partsname
	 *            品名
	 * @return
	 */
	public static String convertToNumber(Double d,String partsname) {
		if (null == d) {
			d = 0D;
		}
		return convertDoubleFormat(d, NUMBER);
	}

	/**
	 * 格式化数量,可以输入
	 * 
	 * @param d
	 *            数量
	 * @param partsname
	 *            品名
	 * @return
	 */
	public static String convertToNumberInput(Double d,String partsname) {
		if (null == d) {
			d = 0D;
		}
		return convertDoubleFormat(d, NUMBERINPUT);
	}
	/**
	 * 处理理算系数
	 * @param d
	 * @return
	 */
	public static String convertToQuotiety(Double d) {
		if (null == d) {
			d = 0D;
		}
		return convertDoubleFormat(d, QUOTIETYINPUT);
	}
	/**
	 * 处理价格位数
	 * 
	 * @param d
	 * @return
	 */
	public static String convertToPrice(Double d) {
		if (null == d) {
			d = 0D;
		}
		return convertDoubleFormat(d, PRICE);
	}

	/**
	 * 处理价格位数
	 * 
	 * @param d
	 * @return
	 */
	public static String convertToPriceInput(Double d) {
		if (null == d) {
			d = 0D;
		}
		return convertDoubleFormat(d, PRICEINPUT);
	}

	/**
	 * 转换成字符串
	 * 
	 * @param d
	 * @return
	 */
	public static String convertToString(Double d) {
		if (null == d) {
			d = 0D;
		}
		String f = "";
		for (int i = 0; i < String.valueOf(d).length(); i++) {
			f = f + "#";
		}
		DecimalFormat df = new DecimalFormat(f);
		String s = df.format(d);
		return s;
	}

	/**
	 * 按指定格式转换成字符串
	 * 
	 * @param d
	 * @param format
	 *            格式
	 * @return
	 */
	public static String convertToString(Double d, String format) {
		if (null == d) {
			d = 0D;
		}
		DecimalFormat df = new DecimalFormat(format);
		String s = df.format(d);
		return s;
	}

	/**
	 * 转换成字符串固定位数,不足的补在右边设置的字符
	 * 
	 * @param d
	 * @param n
	 * @return
	 */
	public static String convertRToString(Double d, String rchar, int n) {
		if (null == d) {
			d = 0D;
		}
		String f = "";
		for (int i = 0; i < n; i++) {
			f = f + rchar;
		}
		DecimalFormat df = new DecimalFormat(f);
		String s = df.format(d);
		return s;
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(Double v, int scale) {
		if (null == v) {
			v = 0D;
		}
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(String v, int scale) {
		if (null == v) {
			v = "0";
		}
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(v);
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 浮点数 加 精确计算 a+b
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double preciseAdd(Double a, Double b) {
		if (null == b ) {
			b= 0D;
		}
		if (null == a ) {
			a= 0D;
		}
		BigDecimal r = new BigDecimal(Double.toString(a));
		r = r.add(new BigDecimal(Double.toString(b)));
		return r.doubleValue();
	}

	/**
	 * 浮点数 加 小数位四舍五入精确计算 a+b
	 * 
	 * @param a
	 * @param b
	 * @param scale
	 *            精确的小数位
	 * @return
	 */
	public static double preciseAdd(Double a, Double b, int scale) {
		if (null == b ) {
			b= 0D;
		}
		if (null == a ) {
			a= 0D;
		}
		BigDecimal r = new BigDecimal(Double.toString(a));
		r = r.add(new BigDecimal(Double.toString(b)));
		return DoubleUtil.round(r.doubleValue(), scale);
	}

	/**
	 * 浮点数 减 精确计算 a-b
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double preciseSub(Double a, Double b) {
		if (null == b ) {
			b= 0D;
		}
		if (null == a ) {
			a= 0D;
		}
		BigDecimal r = new BigDecimal(Double.toString(a));
		r = r.subtract(new BigDecimal(Double.toString(b)));
		return r.doubleValue();
	}

	/**
	 * 浮点数 减 小数位四舍五入精确计算 a-b
	 * 
	 * @param a
	 * @param b
	 * @param scale
	 *            精确的小数位
	 * @return
	 */
	public static double preciseSub(Double a, Double b, int scale) {
		if (null == b ) {
			b= 0D;
		}
		if (null == a ) {
			a= 0D;
		}
		BigDecimal r = new BigDecimal(Double.toString(a));
		r = r.subtract(new BigDecimal(Double.toString(b)));
		return DoubleUtil.round(r.doubleValue(), scale);
	}

	/**
	 * 浮点数 乘 精确计算 a*b
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double preciseMul(Double a, Double b) {
		if (null == b || null == a) {
			return 0D;
		}
		BigDecimal r = new BigDecimal(Double.toString(a));
		r = r.multiply(new BigDecimal(Double.toString(b)));
		return r.doubleValue();
	}

	/**
	 * 浮点数 乘 小数位四舍五入精确计算 a*b
	 * 
	 * @param a
	 * @param b
	 * @param scale
	 *            精确的小数位
	 * @return
	 */
	public static double preciseMul(Double a, Double b, int scale) {
		if (null == b || null == a) {
			return 0D;
		}
		return DoubleUtil.round(new BigDecimal(Double.toString(a)).multiply(
				new BigDecimal(Double.toString(b))).doubleValue(), scale);
	}

	/**
	 * 浮点数 除 精确计算 a/b
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double preciseDev(Double a, Double b) {
		if (null == b || b == 0D || null == a) {
			return 0D;
		}
		BigDecimal r = new BigDecimal(Double.toString(a));
		r = r.divide(new BigDecimal(Double.toString(b)), 6,
				BigDecimal.ROUND_HALF_UP);
		return r.doubleValue();
	}

	/**
	 * 浮点数 除 小数位四舍五入精确计算 a/b
	 * 
	 * @param a
	 * @param b
	 * @param scale
	 *            精确的小数位
	 * @return
	 */
	public static double preciseDev(Double a, Double b, int scale) {
		if (null == b || b == 0D || null == a) {
			return 0D;
		}
		BigDecimal r = new BigDecimal(Double.toString(a));
		r = r.divide(new BigDecimal(Double.toString(b)), scale,
				BigDecimal.ROUND_HALF_UP);
		return r.doubleValue();
	}

	public static String convertDoubleFormat(Double d, String f) {
		DecimalFormat df = new DecimalFormat(f);
		String s = df.format(d);
		return s;
	}

	public static String addDouble(String d1, String d2, boolean t) {
		if (isNumber(d1) && isNumber(d2)) {
			d1 = new BigDecimal(d1).add(new BigDecimal(d2)).toString();
		} else if (isNumber(d2)) {
			d1 = d2;
		}
		if (t && d1.indexOf(".") > 0) {
			d1 = d1.substring(0, d1.indexOf("."));
		}
		return d1;
	}
	public static boolean isNumber(String obj) {
		boolean t = false;
		if (StringUtils.isNotBlank(obj)) {
			try {
				if (Double.valueOf(obj) >= 0D) {
					t = true;
				}
			} catch (Exception e) {

			}
		}
		return t;
	}
	
	/**
	 * 检测obj是否是大于0
	 * @param obj
	 * @return true 是大于 0 false 小于0
	 */
	public static boolean isNotBlank(Double obj){
		if(null==obj||obj.doubleValue()<=0){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 检测obj是否是小于或等于0
	 * @param obj
	 * @return true 小于或等于 0 false 不小于或等于0
	 */
	public static boolean isBlank(Double obj){
		if(null==obj||obj.doubleValue()<=0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 检测obj是否不为空或者不为0
	 * @param obj
	 * @return true 是  false 不是
	 */
	public static boolean isNotEmpty(Double obj){
		if(null==obj||obj.doubleValue()==0){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 检测obj是否为空或者为0
	 * @param obj
	 * @returntrue 是  false 不是
	 */
	public static boolean isEmpty(Double obj){
		if(null==obj||obj.doubleValue()==0){
			return true;
		}else{
			return false;
		}
	}
}
