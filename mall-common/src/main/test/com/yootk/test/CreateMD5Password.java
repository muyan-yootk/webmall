package com.yootk.test;


import com.yootk.common.util.encrypt.EncryptUtil;

public class CreateMD5Password {
    public static void main(String[] args) {
        System.out.println(EncryptUtil.getMD5Encode("hello")); // 密码加密
        System.out.println(EncryptUtil.getMD5Encode("hello").length()); // 密码长度
    }
}
