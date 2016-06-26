package com.yinrong.tools.util;

import java.util.List;
import java.util.Map;

import com.yinrong.tools.OpenConstants;

import com.fasterxml.jackson.core.type.TypeReference;

public class HttpInvokeUtil {
	
	private static final String CHARSET = OpenConstants.CHARSET_UTF8;
	//private static final String RETURNBODY = "returnBody";
	public static final String ERRORCODE = "errorCode";
	public static final String MSG = "msg";
	public static final String SUBCODE = "subCode";
	public static final String SUBMSG = "subMsg";
	public static final String ERROR_RESPONSE = OpenConstants.OP_ERROR_BODY;
	private static final int CONNECTTIMEOUT = 300000;
	private static final int READTIMEOUT = 300000;
	
	/**
	 * 请求服务
	 * 
	 * @return
	 * @throws RuntimeException
	 *             注意异常的处理 <br>
	 *             输出为JSP时，使用try catch自行控制业务异常的展示; <br>
	 *             输出为JSON时,则将RuntimeException抛出，由struts 拦截器负责返回信息的封装
	 */
	@SuppressWarnings("unchecked")
	public static <T> T callAsObject(String url, Map<String, String> params, Class<T> cls, String charset, int connectTimeout, int readTimeout) throws RuntimeException {
		return (T) call(url, params, "object", charset, connectTimeout, readTimeout, new Object[] { cls });

	}
	@SuppressWarnings("unchecked")
	public static <T> T callAsObject(String url, Map<String, String> params, Class<T> cls) throws RuntimeException {
		return (T) call(url, params, "object", CHARSET, CONNECTTIMEOUT, READTIMEOUT, new Object[] { cls });
	}
	/**
	 * 请求服务，无返回值
	 * 
	 * @param url
	 * @param params
	 * @throws RuntimeException
	 */
	public static void call(String url, Map<String, String> params, String charset, int connectTimeout, int readTimeout) throws RuntimeException {
		call(url, params, "", charset, connectTimeout, readTimeout, new Object[] {});
	}
	public static void call(String url, Map<String, String> params) throws RuntimeException {
		call(url, params, "", CHARSET, CONNECTTIMEOUT, READTIMEOUT, new Object[] {});
	}
	//上传文件时请求
	public static void call(String url, Map<String, String> params,Map<String, FileItem> fileParams) throws RuntimeException {
		callForUpload(url, params, fileParams, CHARSET, CONNECTTIMEOUT, READTIMEOUT);
	}
	//上传文件时请求,返回文件对象
	@SuppressWarnings("unchecked")
	public static Object callUploadAsObject(String url, Map<String, String> params,Map<String, FileItem> fileParams, Class<?> cls) throws RuntimeException {
		String result = callForUpload(url, params, fileParams, CHARSET, CONNECTTIMEOUT, READTIMEOUT);
		Object[] resultMap = new Object[] { cls };
		Map<String, Object> respMap = (Map<String, Object>) JsonUtil.buildNormalBinder().getJsonToMap(result, String.class, Object.class);
		if (respMap == null || respMap.isEmpty()) {
			throw new RuntimeException("请求异常");
		}
		if (respMap.get(ERROR_RESPONSE) == null) {
			String object = respMap.get(params.get(OpenConstants.METHOD).replaceAll("\\.", "_")+"_response")==null?"":JsonUtil.buildNormalBinder().toJson(respMap.get(params.get(OpenConstants.METHOD).replaceAll("\\.", "_")+"_response"));
			Object obj = JsonUtil.buildNormalBinder().getJsonToObject(object, (Class<?>) resultMap[0]);
			return obj;
		} else {
			Map<String, String> errorMap = (Map<String, String>) respMap.get(ERROR_RESPONSE);
			if (errorMap.get(SUBCODE) != null && StringUtils.isNotBlank(errorMap.get(SUBCODE).toString())) {
				throw new RuntimeException(errorMap.get(SUBCODE)+":"+errorMap.get(SUBMSG));// 业务异常，向用户展示错误原因信息
			}else {
				throw new RuntimeException(errorMap.get(ERRORCODE)+":"+errorMap.get(MSG));// 业务异常，向用户展示错误原因信息
			}
		}
	}
	/**
	 * 请求服务
	 * 
	 * @return
	 * @throws RuntimeException
	 *             注意异常的处理 <br>
	 *             输出为JSP时，使用try catch自行控制业务异常的展示; <br>
	 *             输出为JSON时,则将RuntimeException抛出，由struts 拦截器负责返回信息的封装
	 */

	@SuppressWarnings("rawtypes")
	public static Map callAsMap(String url, Map<String, String> params, Class keyclazz, Class valueclazz, String charset, int connectTimeout, int readTimeout) throws RuntimeException {
		return (Map) call(url, params, "map",charset, connectTimeout, readTimeout, new Object[] { keyclazz, valueclazz });
	}
	@SuppressWarnings("rawtypes")
	public static Map callAsMap(String url, Map<String, String> params, Class keyclazz, Class valueclazz) throws RuntimeException {
		return (Map) call(url, params, "map", CHARSET, CONNECTTIMEOUT, READTIMEOUT, new Object[] { keyclazz, valueclazz });
	}
	
	/**
	 * 请求服务
	 * 
	 * @return
	 * @throws RuntimeException
	 *             注意异常的处理 <br>
	 *             输出为JSP时，使用try catch自行控制业务异常的展示; <br>
	 *             输出为JSON时,则将RuntimeException抛出，由struts 拦截器负责返回信息的封装
	 */
	@SuppressWarnings("rawtypes")
	public static List<?> callAsList(String url, Map<String, String> params, Class<?> valueclazz, String charset, int connectTimeout, int readTimeout) throws RuntimeException {
		return (List) call(url, params, "list",charset, connectTimeout, readTimeout, new Object[] { valueclazz });

	}
	@SuppressWarnings("rawtypes")
	public static List<?> callAsList(String url, Map<String, String> params, Class<?> valueclazz) throws RuntimeException {
		return (List) call(url, params, "list", CHARSET, CONNECTTIMEOUT, READTIMEOUT, new Object[] { valueclazz });

	}
	private static Object call(String url, Map<String, String> params, String type, String charset, int connectTimeout, int readTimeout, Object... resultMap) throws RuntimeException {
		return readObj(url, params, type, charset, connectTimeout, readTimeout, resultMap);

	}
	@SuppressWarnings({ "unchecked"})
	public static <T> T callAsTypeRefrence(String url,Map<String,String> params, TypeReference<T> typeReference) throws RuntimeException{
		return (T)call(url, params, "typeRefrence", CHARSET, CONNECTTIMEOUT, READTIMEOUT, new Object[]{typeReference});
	}
	@SuppressWarnings({ "unchecked"})
	public static <T> T callAsTypeRefrence(String url,Map<String,String> params, TypeReference<T> typeReference, String charset, int connectTimeout, int readTimeout) throws RuntimeException{
		return (T)call(url, params, "typeRefrence", charset, connectTimeout, readTimeout, new Object[]{typeReference});
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Object readObj(String url, Map<String, String> params, String type, String charset, int connectTimeout, int readTimeout, Object... resultMap) throws RuntimeException {
		String result = callAsJson(url, params, charset, connectTimeout, readTimeout);
		Map<String, Object> respMap = (Map<String, Object>) JsonUtil.buildNormalBinder().getJsonToMap(result, String.class, Object.class);
		if (respMap == null || respMap.isEmpty()) {
			throw new RuntimeException("请求异常");
		}
		if (respMap.get(ERROR_RESPONSE) == null) {
			String object = respMap.get(params.get(OpenConstants.METHOD).replaceAll("\\.", "_")+"_response")==null?"":JsonUtil.buildNormalBinder().toJson(respMap.get(params.get(OpenConstants.METHOD).replaceAll("\\.", "_")+"_response"));
			if (type.equals("")) { // 无返回值
				return "";
			} else if (object == null) {
				return null;
			} else if (type.equals("object")) {
				//Class<?> clazz=(Class) resultMap[0].getClass();
				String cname=((Class)resultMap[0]).getName();
				Object obj =null;
				if(null!=cname&&cname.indexOf("java.lang.")==0){
					Map<String,Object> map = (Map<String,Object>)JsonUtil.buildNormalBinder().getJsonToMap(object,String.class, (Class) resultMap[0]);
					if(null!=map){
						obj=map.get("result");
					}
				}else{
					obj = JsonUtil.buildNormalBinder().getJsonToObject(object, (Class) resultMap[0]);
				}
				return obj;
			} else if (type.equals("map")) {
				Object obj = JsonUtil.buildNormalBinder().getJsonToMap(object, (Class) resultMap[0], (Class) resultMap[1]);
				return obj;
			} else if (type.equals("list")) {
				Object obj = JsonUtil.buildNormalBinder().getJsonToList(object, (Class) resultMap[0]);
				return obj;
			} else if (type.equals("typeRefrence")){
					Object obj = JsonUtil.buildNormalBinder().getJsonToObject(object, (TypeReference)resultMap[0]);
					return obj;
			} else {
				throw new RuntimeException("Unsupport " + type);
			}
		} else {
			Map<String, String> errorMap = (Map<String, String>) respMap.get(ERROR_RESPONSE);
			if (errorMap.get(SUBCODE) != null && StringUtils.isNotBlank(errorMap.get(SUBCODE).toString())) {
				throw new RuntimeException(errorMap.get(SUBCODE)+":"+errorMap.get(SUBMSG));// 业务异常，向用户展示错误原因信息
			}else {
				throw new RuntimeException(errorMap.get(ERRORCODE)+":"+errorMap.get(MSG));// 业务异常，向用户展示错误原因信息
			}
		}
	}

	private static String callAsJson(String url, Map<String, String> params, String charset, int connectTimeout, int readTimeout) {
		if (StringUtils.isBlank(charset)) charset = CHARSET;
		String result = null;
		try {
			result = WebUtils.doPost(url, params, charset, connectTimeout, readTimeout);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("请求错误：" + e.getMessage());
		}
		return result;
	}
	
	/*
	 * 文件上传时使用
	 */
	private static String callForUpload(String url, Map<String, String> params,Map<String, FileItem> fileParams,String charset, int connectTimeout, int readTimeout) {
		if (StringUtils.isBlank(charset)) charset = CHARSET;
		String result = null;
		try {
			result=WebUtils.doPost(url, params,fileParams, connectTimeout, readTimeout);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("请求错误：" + e.getMessage());
		}
		return result;
	}
	
}
