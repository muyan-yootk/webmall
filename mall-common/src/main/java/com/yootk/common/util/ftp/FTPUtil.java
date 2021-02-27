package com.yootk.common.util.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;

public class FTPUtil {
    public static final String FTP_PARENT_PATH = "/var/ftp";
    /**
     * FTP文件上传
     * @param uploadPath 上传路径（"/upload/goods"），如果路径不存在会自动创建
     * @param file 上传文件
     * @return 上传成功返回true，否则返回false
     * @throws Exception
     */
    public static boolean upload(String uploadPath, File file, String fileName) throws Exception {
        String ftpPath = FTP_PARENT_PATH + uploadPath; // 最终上传的路径
        boolean flag = false;
        FTPClient client = FTPConnection.getFTPClient(); // 获取FTP连接
        client.enterLocalPassiveMode(); // 采用被动模式
        client.setFileType(FTPClient.BINARY_FILE_TYPE); // 采用二进制传输
        client.setBufferSize(2048); // 设置上传的缓存大小
        if (!client.changeWorkingDirectory(ftpPath)) {  // 切换目录
            client.makeDirectory(ftpPath); // 目录的创建
        }
        // 所有的文件是以二进制数据流的形式进行传输的，所以一定要通过InputStream获取一个输入流
        InputStream input = new FileInputStream(file); // 文件输入流
        String tempName = ftpPath + fileName; // FTP文件保存名称
        String ftpFileName = new String(tempName.getBytes("UTF-8"), "ISO-8859-1");
        // 在进行文件保存的需要明确配置存储文件的名称，同时利用输入流进行数据的传输
        if (client.storeFile(ftpFileName, input)) {
            flag = true;
        }
        input.close(); // 传输完成后关闭输入流
        client.logout(); // 注销
        return flag;
    }

    /**
     * FTP文件删除
     * @param ftpPath 要删除的路径（/var/ftp/yootk/）
     * @param fileName 要删除的文件
     * @throws Exception
     */
    public static void delete(String ftpPath, String fileName) throws Exception {
        FTPClient client = FTPConnection.getFTPClient(); // 获取FTP连接
        client.enterLocalPassiveMode(); // 采用被动模式
        client.setFileType(FTPClient.BINARY_FILE_TYPE); // 采用二进制传输
        client.setBufferSize(2048); // 设置上传的缓存大小
        if (client.changeWorkingDirectory(ftpPath)) { // 切换到要下载的FTP目录
            FTPFile[] files = client.listFiles(); // 获取全部的文件列表
            for (FTPFile file : files) {    // 迭代当前目录中的所有文件
                if (file.getName().trim().equalsIgnoreCase(fileName)) { // 找到了匹配的文件名称
                    client.deleteFile(new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
                }
            }
        }
        client.logout(); // 注销

    }
}
