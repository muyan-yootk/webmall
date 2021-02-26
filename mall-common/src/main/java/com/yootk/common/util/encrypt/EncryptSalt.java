package com.yootk.common.util.encrypt;
import java.util.Base64;

public class EncryptSalt {
    private static final String SALT = "MUYAN-YOOTK.COM" ;
    private static final int REPEAT = 5 ; // 定义加密次数
    private EncryptSalt() {}
    public static String encrypt(String origin) {
        Base64.Encoder encoder = Base64.getEncoder() ; // 获得加密器
        byte data [] = ("{"+SALT+"}" + origin).getBytes() ; // 将字符串转为字节数组
        for (int x = 0 ; x < REPEAT ; x ++) {
            data = encoder.encode(data);
        }
        return new String(data) ; // 返回加密后的数据信息
    }
    public static String decrypt(String mist) {
        Base64.Decoder decoder = Base64.getDecoder() ;
        byte data [] = mist.getBytes() ; // 将字符串转为字节数组
        for (int x = 0 ; x < REPEAT ; x ++) {
            data = decoder.decode(data) ;
        }
        String str = new String(data);
        return str.substring((("{" + SALT + "}")).length()) ;
    }
}
