package com.yootk.test;


import com.yootk.common.util.encrypt.EncryptUtil;

public class CreateBase64Password {
    public static void main(String[] args) {
        String password = "edu.yootk.com"; // 原始密码
        String en = EncryptUtil.encrypt(password);
        System.out.println("【加密密码】" + en);
        System.out.println("【解密密码】" + EncryptUtil.decrypt(en));
    }
}
