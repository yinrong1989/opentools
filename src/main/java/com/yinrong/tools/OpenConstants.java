package com.yinrong.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class OpenConstants {

    public static final String SIGN_TYPE        = "sign_type";
    
    public static final String RETURN_BODY        = "returnBody";

    public static final String OP_ERROR_BODY        = "error_response";
    
    public static final String OP_ERROR_TYPE        = "opErrorType";
    
    public static final String OP_ERROR_CODE        = "opErrorCode";
    
    public static final String OP_ERROR_MSG        = "opErrorMsg";
    
    public static final String SIGN_TYPE_RSA    = "RSA";

    public static final String SIGN_ALGORITHMS  = "SHA1WithRSA";

    public static final String APP_ID           = "app_id";

    public static final String FORMAT           = "format";
    public static final String INFORMAT           = "informat";

    public static final String METHOD           = "method";

    public static final String RS_TIMESTAMP     = "rs_timestamp";

    public static final String RS_SIGN_TYPE     = "rs_sign_type";

    public static final String TIMESTAMP        = "timestamp";

    public static final String VERSION          = "version";

    public static final String SIGN             = "sign";
    
	public static final String ENCRYPT 			= "encrypt";

    public static final String OPEN_SDK       	= "vfinopen_sdk";

    public static final String ACCESS_TOKEN     = "auth_token";
    
    public static final String TERMINAL_TYPE    = "terminal_type";

    public static final String TERMINAL_INFO    = "terminal_info";
    
    public static final String REMOTHOST      	= "remothost";
    
    public static final String SERVICE_VALIDATING = "service_validating";
    
    public static final String SYSTEM_API_VERSION = "system_api_version";
    
    public static final String INVOKEID_MSGRID = "invokeId-msgRid";
	
    public static final String INVOKEID_MSGPARENTID = "invokeId-msgParentId";
	
    public static final String INVOKEID_LEVEL = "invokeId-level";
    
    public static final String NO_SEND_MONITOR = "no_send_monitor";
    
    public static final String TEST_ROUTER_FLAG = "test_router_flag";
    
    public static final String SYS_PRODUCT_KEY = "sys_product_code";
    
    public static final String OAUTH2_RESPONSE_TYPE_KEY = "response_type";  // response_type = code
    public static final String OAUTH2_GRANT_TYPE_KEY    = "grant_type";     // grant_type = access_token / grant_type = refresh_token
    public static final String OAUTH2_TYPE_CODE         = "code";           // code key or code value for oauth2 parameter
    public static final String OAUTH2_ACCESS_TOKEN      = "access_token";   // 
    public static final String OAUTH2_EXPIRES_IN        = "expires_in";     // timeout after expires_in
    public static final String OAUTH2_REFRESH_TOKEN     = "refresh_token";  // 
    public static final String OAUTH2_REDIRECT_URI      = "redirect_uri";  // 
    public static final String OAUTH2_RANDOM_CODE       = "random_code";  // 
    
    public static final String DEFAULT_VERSION = "1.0";
    
    /**
     * 解析方式的 把MAP转成BEAN
     */
    public static final String MAPTOBEAN_KEY	="vop_mapTobean";
    /** 默认时间格式 **/
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    public static final String CHARSET      	= "charset";
    /**  文件上传，相关信息存放KEY **/
    public static final String FILE_KEY    		= "fileBeanList";
    
    /**  Date默认时区 **/
    public static final String DATE_TIMEZONE    = "GMT+8";

    /** UTF-8字符集 **/
    public static final String CHARSET_UTF8     = "UTF-8";

    /** GBK字符集 **/
    public static final String CHARSET_GBK      = "GBK";

    /** JSON 应格式 */
    public static final String FORMAT_JSON      = "json";

    /** XML 应格式 */
    public static final String FORMAT_XML       = "xml";

    /** HTML格式 */
    public static final String FORMAT_HTML       = "html";
    
    /** SDK版本号 */
    public static final String SDK_VERSION      = "vfinopen-sdk-java-dynamicVersionNo";
	
	public static final String PROD_CODE        = "prod_code"; 
	public static final String RESPOEN_CODE        = "memo"; 
	public static final String CHANNEL_TYPE_PRODUCER  = "Producer";
	
	public static final String CHANNEL_TYPE_CONSUMER = "Consumer";
	
	//public static final String APP_STATE_PRE = "pre";
	/**
	 * 内部
	 */
	public static final String ROUTER_DIRE_0  = "0";
	/**
	 * 外部
	 */
	public static final String ROUTER_DIRE_1  = "1";
	/**
	 * 直接访问
	 */
	public static final String ROUTER_DIRE_2  = "2";
	/**
	 * 测试
	 */
	public static final String ROUTER_DIRE_3  = "3";
	/**
	 *  转发
	 */
	public static final String ROUTER_DIRE_4  = "4";
	
	/**
	 * 测试环境
	 */
	public static final String ROUTER_TEST = "3";
	/**
	 * 生产环境
	 */
	public static final String ROUTER_PRO = "1";
	
	/**
	 * 校验类型应用
	 */
	public static final String AUTH_TYPE_APP  = "0";
	/**
	 * 校验类型用户
	 */
	public static final String AUTH_TYPE_USER  = "1";
	/**
	 * 校验类型角色
	 */
	public static final String AUTH_TYPE_ROLE  = "2";	
	/**
	 * 校验类型分类
	 */
	public static final String AUTH_TYPE_CLS  = "3";
	/**
	 * 校验类型验证中
	 */
	public static final String AUTH_TYPE_VALIDATE  = "4";
	
	/**
	 * 进
	 */
	public static final String AUTH_IN  = "0";
	/**
	 * 出
	 */
	public static final String AUTH_OUT  = "1";
	
	public static final String APP_TYPE_IN  = "in";
	
	public static final String APP_TYPE_OUT  = "out";
	
	public static final String PARAM_DIRE_IN = "0";
	public static final String PARAM_DIRE_OUT = "1";
	
	public static final String API_NOT_SWITCH = "0";
	
	/**
	 * 目标APP_ID
	 */
	public static final String TO_APP_ID = "to_app_id";
	
	public static final String OPEN_USER_CODE = "openUserCode";
	
	public static final String PLATFORM_ID_TYPE = "platformIdType";
	/**
	 * API调用类型 直接
	 */
	public static final Integer APPAPI_CALLTYPE_DIR = 0;
	/**
	 * API调用类型 回调
	 */
	public static final Integer APPAPI_CALLTYPE_CALL = 1;
	/**
	 * API调用类型 回调
	 */
	public static final Integer APPAPI_CALLTYPE_PRODUCT = 2;
	
	/**
	 * 内部转发
	 */
	public static final String OPEN_REQUEST_FORWARD = "open_request_forward";
	
	/**
	 * 应用类型 内部应用
	 */
	public static final String APPMANAGE_TYPE_IN = "0";
	/**
	 * 应用类型 外部应用
	 */
	public static final String APPMANAGE_TYPE_OUT = "1";
	/**
	 * 应用类型 组件
	 */
	public static final String APPMANAGE_TYPE_COM = "2";
	/**
	 * 应用类型 业务应用
	 */
	public static final String APPMANAGE_TYPE_BIZ = "3";
	/**
	 * 应用类型 产品
	 */
	public static final String APPMANAGE_TYPE_PRO = "4";
	
	/**
	 * flow
	 */
	public static final String TO_JSON = "TO-JSON";
	
	public static final String ACQ_INST_ID_CODE = "00000000";
	
	public static String SERVER_TYPE_HTTP="http";
	public static String SERVER_TYPE_HTTPS="https";
	public static String SERVER_TYPE_RMI="rmi";
	public static String SERVER_TYPE_SOAP="soap";
	public static String SERVER_TYPE_SOCKET="socket";
	public static String SERVER_TYPE_JAVA="java";
	public static String SERVER_TYPE_LOCAL="spring";
	public static String SERVER_TYPE_ALL="ALL";
	public static String FREEMAKER_STR_KEY = "freemaker-str-key";
	
	public static final Map<String,String> headersMap=new HashMap<String,String>(){/**
		 * 
		 */
		private static final long serialVersionUID = 1549926805156219813L;

	{
		put(SIGN_TYPE, SIGN_TYPE);
		put(APP_ID, APP_ID);
		put(METHOD, METHOD);
		put(TIMESTAMP, TIMESTAMP);
		put(VERSION, VERSION);
		put(OPEN_SDK, OPEN_SDK);
		put(TERMINAL_TYPE, TERMINAL_TYPE);
		put(TERMINAL_INFO, TERMINAL_INFO);
		put(CHARSET, CHARSET);
	}
	};
	public static final Map<String,String> outparamMap=new HashMap<String,String>(){/**
		 * 
		 */
		private static final long serialVersionUID = 5652550529434091733L;

	{
		put(ACCESS_TOKEN, ACCESS_TOKEN);
		put(SDK_VERSION, SDK_VERSION);
		put(FORMAT, FORMAT);
		put(PROD_CODE, PROD_CODE);
		put(RESPOEN_CODE, RESPOEN_CODE);
	}
	};
}
