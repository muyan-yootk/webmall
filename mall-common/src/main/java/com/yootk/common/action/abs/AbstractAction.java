package com.yootk.common.action.abs;

import com.yootk.common.mvc.util.ResourceUtil;
import com.yootk.common.servlet.MultipartFile;
import com.yootk.common.servlet.ServletObject;

import java.io.IOException;
import java.util.*;

public abstract class AbstractAction { // 所有Action的父类
    public String getUploadPath() { // 是为了上传准备的
        return "/upload/";
    }
    public static final String PATH_ATTRIBUTE_NAME = "path" ;
    public static final String MSG_ATTRIBUTE_NAME = "msg" ;
    private static final String PAGES_BASENAME = "com.yootk.mall.resource.Page" ;
    private static ResourceBundle pageResourceBundle ;
    static {
        try {
            pageResourceBundle = ResourceBundle.getBundle(PAGES_BASENAME) ;
        } catch (Exception e) {}
    }
    private String getPageResource(String key) {
        try {
            return pageResourceBundle.getString(key) ;
        } catch (Exception e) {
            return null ;
        }
    }
    public String getTempPath() {
        return "/tmp" ;
    }

    /**
     * 实现所有上传文件的存储
     * @param files 描述所有的上传文件
     * @return 返回每个文件保存的路径信息
     */
    public List<String> saveUploadFile(MultipartFile... files) {
        List<String> fileNames = new ArrayList<>() ;
        String uploadDir = this.getUploadPath();
        if (!uploadDir.endsWith("/")) {
            uploadDir = uploadDir + "/" ;
        }
        for (MultipartFile file : files) {
            String fileName = UUID.randomUUID() + "." + file.getContentType().substring(file.getContentType().lastIndexOf("/") + 1) ;
            String savePath = ServletObject.getApplication().getRealPath(uploadDir) + fileName ;
            if(file.transfer(savePath)) {   // 文件转存
                fileNames.add(fileName) ;
            }
        }
        return fileNames ;
    }
    /**
     * 实现信息输出
     * @param obj 要输出的信息内容
     */
    public void print(Object obj) {
        try {
            ServletObject.getResponse().getWriter().println(obj);
        } catch (IOException e) {
        }
    }
    /**
     * 得到跳转页面
     * @return 跳转显示页
     */
    public String getForwardPage() {
        return this.getPageResource("forward.page");
    }
    public String getBackForwardPage() {
        return this.getPageResource("back.forward.page");
    }

    /**
     * 获取登陆后显示的首页信息
     * @return 返回首页路径
     */
    public String getIndexPage() {
        return this.getPageResource("index.page");
    }

    public String createUploadFileName(MultipartFile file) {
        if (file == null) { // 现在没有文件名称
            return "nophoto.png"; // 默认的文件名称
        }
        return UUID.randomUUID() + "." + file.getContentType().substring(file.getContentType().lastIndexOf("/") + 1);
    }

    /**
     * 得到跳转路径
     * @param key
     * @return
     */
    public String getPage(String key) {
        return this.getPageResource(this.getClass().getName() + "." + key) ;
    }
    public String getMessge(String key, String ... params) {
        return ResourceUtil.getMessage(key,params) ;
    }

    public Set<Long> splitIds(String data) {
        Set<Long> ids = new HashSet<>(); // 保存全部处理的id信息
        String result[] = data.split(";"); // 根据分号进行数据的拆分处理
        for (String temp : result) {
            ids.add(Long.parseLong(temp)); // 保存删除集合
        }
        return ids;
    }
}
