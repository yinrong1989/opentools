package com.yinrong.tools.util;

import java.util.Random;

public class RandomUtils {

    // 使用到Algerian字体，系统里没有的话需要安装字体，字体只显示大写，去掉了1,0,i,o几个容易混淆的字符
    public static final String RANDOM_NUM = "0123456789";
    public static final String RANDOM_LETTER = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjklmnpqrstuvwxyz";
    public static final String RANDOM_MIX = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjklmnpqrstuvwxyz";

    /**
     * 使用系统默认字符源生成验证码
     * 
     * @param verifySize
     *            验证码长度
     * @param verifyType
     *            验证码类型
     * @return
     */
    public static String generateRandom(int verifySize, Integer verifyType) {
        String VERIFY_CODES = "";
        if (verifyType == 0) {
            VERIFY_CODES = RANDOM_NUM;
        } else if (verifyType == 1) {
            VERIFY_CODES = RANDOM_LETTER;
        } else if (verifyType == 2) {
            VERIFY_CODES = RANDOM_MIX;
        }
        return generateVerifyCode(verifySize, VERIFY_CODES);
    }

    /**
     * 使用指定源生成验证码
     * 
     * @param verifySize
     *            验证码长度
     * @param sources
     *            验证码字符源
     * @return
     */
    public static String generateVerifyCode(int verifySize, String sources) {
        if (sources == null || sources.length() == 0) {
            sources = RANDOM_NUM;
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for (int i = 0; i < verifySize; i++) {
            verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }
}
