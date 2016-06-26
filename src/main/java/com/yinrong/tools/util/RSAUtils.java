package com.yinrong.tools.util;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import com.yinrong.tools.codec.Base64;
public class RSAUtils {



	/**
	 * <dl>
	 * <dt><b>类功能概要：</b></dt>
	 * <dd>RSA签名,加解密处理核心文件，注意：密钥长度1024</dd>
	 * </dl>
	 * 
	 * @copyright Copyright 2014,(C)The V-Finance Co.Ltd. All right reserved.
	 * @since 1.6
	 * @version <pre>
	 * Version  Date        Company    Author         Case-Name
	 * -------  ----------  ---------  -------------  --------------------------------------------------
	 * 1.00     2014-06-01  V-Finance  Zhang.Huihua   Initial Creation
	 * 
	 * </pre>
	 */
	    private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";                     //签名算法
	    private static final String KEY_ALGORITHM       = "RSA";        //加密算法RSA
	    private static final int    MAX_ENCRYPT_BLOCK   = 117;          //RSA最大加密明文大小
	    private static final int    MAX_DECRYPT_BLOCK   = 128;          //RSA最大解密密文大小

	    /**
	     * 私钥签名
	     * 
	     * @param text 原字符串
	     * @param privateKey 私钥
	     * @param charset 编码格式
	     * @return 签名
	     */
	    public static byte[] sign(byte[] text, PrivateKey privateKey) throws Exception {
	        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
	        signature.initSign(privateKey);
	        signature.update(text);
	        return signature.sign();
	    }

	    /**
	     * 公钥验签
	     * 
	     * @param text 原字符串
	     * @param sign 签名结果
	     * @param publicKey 公钥
	     * @param charset 编码格式
	     * @return 验签结果
	     */
	    public static boolean verify(byte[] text, byte[] sign, PublicKey publicKey) throws Exception {
	        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
	        signature.initVerify(publicKey);
	        signature.update(text);
	        return signature.verify(sign);
	    }

	    /**
	     * 私钥加密/公钥加密
	     * 
	     * @param data 明文
	     * @param privateKey/publicKey 私钥
	     * @return 加密后密文
	     */
	    public static byte[] encrypt(byte[] data, Key key) throws Exception {
	        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
	        cipher.init(Cipher.ENCRYPT_MODE, key);
	        int inputLen = data.length;
	        ByteArrayOutputStream out = new ByteArrayOutputStream(data.length << 1);
	        int offSet = 0;
	        byte[] cache;
	        // 对数据分段加密
	        while (inputLen - offSet > 0) {
	            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
	                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
	            } else {
	                cache = cipher.doFinal(data, offSet, inputLen - offSet);
	            }
	            out.write(cache);
	            offSet += MAX_ENCRYPT_BLOCK;
	        }
	        byte[] encryptedData = out.toByteArray();
	        out.close();

	        return encryptedData;
	    }

	    /**
	     * 私钥解密/公钥解密
	     * 
	     * @param encryptedData 密文
	     * @param privateKey/publicKey 公钥
	     * @return 解密后明文
	     */
	    public static byte[] decrypt(byte[] encryptedData, Key key) throws Exception {
	        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
	        cipher.init(Cipher.DECRYPT_MODE, key);
	        int inputLen = encryptedData.length;
	        ByteArrayOutputStream out = new ByteArrayOutputStream(encryptedData.length);
	        int offSet = 0;
	        byte[] cache;
	        // 对数据分段解密
	        while (inputLen - offSet > 0) {
	            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
	                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
	            } else {
	                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
	            }
	            out.write(cache);
	            offSet += MAX_DECRYPT_BLOCK;
	        }
	        byte[] decryptedData = out.toByteArray();
	        out.close();

	        return decryptedData;
	    }

	    /** 生成密钥对(公钥和私钥) */
	    public static KeyPair genKeyPair() throws Exception {
	        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
	        keyPairGen.initialize(1024);
	        return keyPairGen.generateKeyPair();
	    }

	    /** 获取私钥 */
	    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
	        return KeyFactory.getInstance(KEY_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey)));
	    }

	    /** 获取公钥 */
	    public static PublicKey getPublicKey(String publicKey) throws Exception {
	        return KeyFactory.getInstance(KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(publicKey)));
	    }

	    /** 获取私钥 */
	    public static String getPrivateKey(KeyPair keyPair) throws Exception {
	        return Base64.encodeBase64String(keyPair.getPrivate().getEncoded());
	    }

	    /** 获取公钥 */
	    public static String getPublicKey(KeyPair keyPair) throws Exception {
	        return Base64.encodeBase64String(keyPair.getPublic().getEncoded());
	    }

	    public static void main(String[] args) throws Exception {

	    }
}
