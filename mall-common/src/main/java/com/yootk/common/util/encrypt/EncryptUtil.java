package com.yootk.common.util.encrypt;

import java.util.Base64;

public class EncryptUtil { // 加密工具类
    // 如果从开发的角度来讲，此时应该将这两个的配置内容定义在一个额外的资源文件里面
    // 考虑到Base64的加密操作谁都可以使用，所以要想保证安全的加密和解密处理可以加入一些盐值
    private static final String SALT = "Muyan-Yootk_yootk.com"; // 盐值的内容可以随意（KEY）
    // 一次的加密肯定是不安全的，那么肯定要考虑多次加密，那么可以将加密的次数进行配置
    private static final int REPEAT = 5; // 重复加密5次
    private EncryptUtil() {} // 构造方法私有化

    /**
     * 基于MD5的方式实现数据的加密处理，但是需要注意的是，此时数据不可逆
     * @param data 要加密的数据内容
     * @return 加密后的文本信息
     */
    public static String getMD5Encode(String data) {
        String passData = EncryptUtil.encrypt(data); // 先使用Base64加密
        MD5Code md5Code = new MD5Code(); // 获取MD5对象实例
        for (int x = 0; x < REPEAT; x ++) { // 重复加密处理
            passData = md5Code.getMD5ofStr(passData);
        }
        return passData;
    }

    /**
     * 创建一个加密的信息，该信息可逆
     * @param origin 要加密的原始信息
     * @return 可逆的加密数据
     */
    public static String encrypt(String origin) {   // 配置一个加密的处理操作
        Base64.Encoder encoder = Base64.getEncoder(); // 获取加密器
        byte data[] = ("{" + SALT + "}" + origin).getBytes(); // 是依据字节进行加密操作的
        for (int x = 0; x < REPEAT ; x ++) {
            data = encoder.encode(data); // 重复加密操作
        }
        return new String(data); // 返回加密字符串
    }

    /**
     * 进行可逆密码的解密操作
     * @param mist 加密后的数据内容
     * @return 解密之后的数据内容
     */
    public static String decrypt(String mist) {
        Base64.Decoder decoder = Base64.getDecoder(); // 解密器
        byte [] data = mist.getBytes();
        for (int x = 0 ; x < REPEAT ; x ++) {
            data = decoder.decode(data); // 解密处理
        }
        String str = new String(data); // 原始密码是带有盐值
        return str.substring(("{" + SALT + "}").length()); // 字符串截取
    }
}
