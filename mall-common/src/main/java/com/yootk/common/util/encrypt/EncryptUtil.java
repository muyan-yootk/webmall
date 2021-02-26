package com.yootk.common.util.encrypt;
public class EncryptUtil {
    private static final int REPEAT = 6 ; // 加密次数
    public static String encode(String password) {
        String newPass = EncryptSalt.encrypt(password) ; // Base64加密
        com.yootk.common.util.encrypt.MD5Code md5 = new com.yootk.common.util.encrypt.MD5Code() ; // 获取MD5加密对象实例
        for (int x = 0 ; x < REPEAT ; x ++) {
            newPass = md5.getMD5ofStr(newPass) ;
        }
        return newPass ;
    }
}
