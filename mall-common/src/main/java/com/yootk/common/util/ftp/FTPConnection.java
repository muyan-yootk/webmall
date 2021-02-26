package com.yootk.common.util.ftp;

import org.apache.commons.net.ftp.FTPClient;

public class FTPConnection { // 实现FTP连接的获取
    private static final String FTP_SERVER = "192.168.190.128"; // 服务地址
    private static final int FTP_PORT = 21;
    private static final String FTP_USER = "ftp";
    private static final String FTP_PASSWORD = "yootk@muyan";
    private static final int TIMEOUT = 5000; // 设置超时时间
    private static final String ENCODING = "UTF-8";
    public static FTPClient getFTPClient() throws Exception {
        FTPClient client = new FTPClient(); // 实例化FTPClient类实例
        client.connect(FTP_SERVER, FTP_PORT); // 设置连接主机信息
        client.login(FTP_USER, FTP_PASSWORD); // 实现服务登录
        client.setConnectTimeout(TIMEOUT); // 超时时间
        client.setControlEncoding(ENCODING); // 编码
        return client;
    }
}
