package com.yootk.common.servlet;

import java.io.*;

public class MultipartFile extends File { // 自定义的文件类
    private String contentType; // 文件的MIME类型
    private String originFileName; // 文件原始名称
    public MultipartFile(String path) {
        super(path);
    }
    public MultipartFile(File parent, String child) {
        super(parent, child);
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    @Override
    public String toString() {
        return "【MultipartFile】" + super.toString() + "，contentType = " + this.contentType + "、originFileName = " + this.originFileName;
    }
    private void transferTo(InputStream inputStream , OutputStream output) throws Exception  {
        byte []  data = new byte [1024] ;
        int temp = 0 ;
        while((temp = inputStream.read(data)) != -1){
            output.write(data,0,temp);
        }
    }
    /**
     * 将当前的文件内容直接转存到指定的目录之中（输出文件的完整路径）
     * @param savePath 完整路径
     * @return 保存成功返回true，否则返回false
     */
    public boolean transfer(String savePath) {
        boolean flag = false ;
        InputStream input = null ;
        OutputStream output = null ;
        try {
            File outFile = new File(savePath) ;
            input = new FileInputStream(this) ;
            output = new FileOutputStream(outFile) ;
            this.transferTo(input,output) ;
            flag = true ; // 修改成功的标志位
        } catch (Exception e) {
        } finally {
            if (input != null) {
                try {
                    input.close() ;
                } catch (IOException e) {
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                }
            }
        }
        return flag ;
    }
}
