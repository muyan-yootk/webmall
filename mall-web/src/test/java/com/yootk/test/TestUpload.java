package com.yootk.test;

import com.yootk.mall.util.UploadFileToServer;

import java.io.File;

public class TestUpload {
    public static void main(String[] args) throws Exception {
        File file = new File("D:" + File.separator + "test.jpg") ;
        System.out.println(UploadFileToServer.upload(file,"image/jpg"));
    }
}
