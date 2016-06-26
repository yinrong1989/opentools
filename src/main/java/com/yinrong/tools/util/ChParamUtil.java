package com.yinrong.tools.util;

public class ChParamUtil {
	private String tablename;//type=1 2对应指定list对应的对应关键名
	private String lisType;//type=1 2 对应指定list 对应循环次数，0 按tablename处理  1固定次数  2 按指定对象（LIST 或OBJECT）固定次数 
	private String lisNum;//type=1 2对应指定list 对应循环次数，固定次数
	private String lisStart;//list 循环开始时，外面是否需要+字符串，默认是[
	private String lisEnd;//list 循环结束时，外面是否需要+字符串，默认是]
	private String lisCend;//list 循环中间时，外面是否需要+字符串，默认是,
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getLisType() {
		return lisType;
	}
	public void setLisType(String lisType) {
		this.lisType = lisType;
	}
	public String getLisNum() {
		return lisNum;
	}
	public void setLisNum(String lisNum) {
		this.lisNum = lisNum;
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
	public String getLisCend() {
		return lisCend;
	}
	public void setLisCend(String lisCend) {
		this.lisCend = lisCend;
	}
}
