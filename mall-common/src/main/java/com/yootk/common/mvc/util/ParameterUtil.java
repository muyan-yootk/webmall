package com.yootk.common.mvc.util;

import com.yootk.common.servlet.MultipartFile;
import com.yootk.common.servlet.ServletObject;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload2.FileItem;
import org.apache.commons.fileupload2.disk.DiskFileItemFactory;
import org.apache.commons.fileupload2.servlet.ServletFileUpload;

import java.io.*;
import java.util.*;

public class ParameterUtil {
    // 将所有默认的常量的信息全部放在这个工具类之中，目的是为了进行对象构造时使用的默认参数
    public static final Long MAX_SIZE = 3145728L; // 上传的最大长度为3M数据
    public static final Long FILE_MAX_SIZE = 1048576L; // 上传单个文件的最大值
    // 需要注意的是，此时在进行存储目录设置的时候，后面必须要提供有一个“/”作为完结，如果没有路径出错
    public static final String TEMP_DIR = "/"; // 临时保存目录
    public static final String UPLOAD_DIR = "/upload/"; // 目标存储路径
    public static final String DEFAULT_ENCODING = "UTF-8"; // 文本编码
    // 这个程序类是一个与JavaWEB开发紧密耦合的工具类，必须运行在WEB容器之中
    private HttpServletRequest request; // 实现请求的接收
    private String uploadFile; // 【属性】保存上传路径
    private String tempFile; // 【属性】保存临时存储路径
    private String encoding; // 【属性】保存编码的设置
    private long maxSize; // 【属性】上传文件的最大长度
    private long fileMaxSize; // 【属性】保存每个上传文件最大的长度
    // 在进行请求参数接收处理的时候需要考虑表单是否封装情况下的操作
    // 如果不使用这样的标记，在每次进行参数处理的时候都要求自己手工判断是否以“multipart/form-data”开头
    private boolean uploadFlag = false; // 封装处理标记，保存表单是否封装的状态
    private ServletFileUpload fileUpload; // 实现上传的处理
    // 所有上传的文件应该首先保存在临时目录下，所以需要定义一个可以获取所有临时文件名称的集合，文件名称是随机生成的
    // 该集合还有一个最重要的作用：可以实现上传之后的临时目录清空处理
    private List<String> tempFileNames = new ArrayList<>(); // 临时文件名称存储集合
    // 所有的请求处理的时候，在使用FileUpload组件操作中是需要进行循环的，而设置这个类的目的就是为了减少这样的循环
    // 在一次循环的时候就将所有的临时文件保存了，同时把所有的参数也保存在Map集合里面
    private Map<String, String[]> paramMap = new HashMap<>();
    private Map<String, List<FileItem>> map;
    private Map<String, List<MultipartFile>> allUploadFile = new HashMap<>(); // 所有的上传文件
    public ParameterUtil(HttpServletRequest request) {    // 构造方法
        this(request, UPLOAD_DIR, TEMP_DIR, DEFAULT_ENCODING, MAX_SIZE, FILE_MAX_SIZE);
    }
    public ParameterUtil(HttpServletRequest request, String uploadFile) {
        this(request, uploadFile, TEMP_DIR, DEFAULT_ENCODING, MAX_SIZE, FILE_MAX_SIZE);
    }
    public ParameterUtil(HttpServletRequest request, String uploadFile, String tempFile, String encoding, long maxSize, long fileMaxSize) {
        this.request = request;
        if (uploadFile.endsWith("/")) { // 传递进来的路径存在有“/”
            this.uploadFile = uploadFile;
        } else {
            this.uploadFile = uploadFile + "/"; // 设置一个处理后的路径
        }
        if (tempFile.endsWith("/")) {
            this.tempFile = tempFile;
        } else {
            this.tempFile = tempFile + "/";
        }
        this.encoding = encoding;
        this.maxSize = maxSize;
        this.fileMaxSize = fileMaxSize;
        this.handleParameter(); // 在构造的时候同时实现请求参数的处理操作
    }
    private void handleParameter() {    // 对请求的参数进行处理
        // 针对于表单是否封装，请求参数的获取机制是完全不一样的，所以应该考虑对当前的请求状态做出判断
        if ((this.uploadFlag = this.request.getContentType() != null &&
                this.request.getContentType().startsWith("multipart/form-data"))) { // 进行表单封装处理
            // 一旦表单进行了封装处理操作，那么在整个的代码之中就必须考虑到通过FileUpload组件进行处理
            DiskFileItemFactory factory = new DiskFileItemFactory(); // 磁盘管理类
            factory.setRepository(new File(this.tempFile)); // 临时存储目录
            this.fileUpload = new ServletFileUpload(factory); // 上传处理的核心类
            fileUpload.setSizeMax(this.maxSize); // 上传的最大容量
            fileUpload.setFileSizeMax(this.fileMaxSize); // 单个文件的最大容量
            if (fileUpload.isMultipartContent(request)) {   // 当前存在有文件上传
                try {
                    // 此时所得到的全部的数据内容实际上都保存在了FileItem集合里面
                    this.map = this.fileUpload.parseParameterMap(request); // 对当前请求进行参数的分析
                    for (Map.Entry<String, List<FileItem>> entry : map.entrySet()) {    // Map集合迭代
                        List<MultipartFile> files = new ArrayList<>(); // 上传文件的配置
                        String paramName = entry.getKey(); // 获取提交的参数名称
                        List<FileItem> allItems = entry.getValue(); // 获取请求的参数内容
                        // 如果现在发现是普通的文本数据，则直接保存在paramMap集合里面，如果是上传文件，则生成一个新的文件名称，保存的是文件名称
                        String[] values = new String[allItems.size()]; // 根据提交参数的个数来确定字符串数组的大小
                        int foot = 0; // 实现字符串数组的索引控制
                        for (FileItem item : allItems) {    // 循环参数内容
                            if (item.isFormField()) {   // 是一个普通的文本参数
                                String value = item.getString(this.encoding); // 直接获取参数内容
                                values [foot ++] = value; // 把数据保存在数组之中
                            } else {    // 是二进制文件
                                //  需要创建新的文件名称，同时还要将文件保存在临时目录之中
                                String fileName = this.saveTempFile(item); // 保存临时文件
                                File parentFile = new File(ServletObject.getApplication().getRealPath(this.tempFile));
                                MultipartFile multipartFile = new MultipartFile(parentFile, this.tempFile + fileName);
                                multipartFile.setContentType(item.getContentType()); // 文件MIME类型
                                multipartFile.setOriginFileName(item.getName()); // 原始名称
                                files.add(multipartFile); // 文件上传的存储
                                this.tempFileNames.add(fileName); // 保存临时文件名称
                                values [foot ++] = fileName; // 二进制文件保存的是临时文件名称
                                this.allUploadFile.put(paramName, files);
                            }
                        }
                        // 最终能够实现数据信息查询的是ParamMap集合，将所有的数据保存在ParamMap集合里
                        this.paramMap.put(paramName, values);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // 通过FileItem可以获得原始文件的名称以及MIME类型，因为最终生成的随机文件名称是需要原始文件后缀的
    private String saveTempFile(FileItem item) throws Exception {    // 实现临时文件的存储，并且可以返回临时文件的名称
        if (item.getSize() > 0) {   // 有上传文件存在
            String fileName = "temp." + this.getUUIDName(item); // 获取了一个临时文件的存储名称
            String filePath = this.request.getServletContext().getRealPath(this.tempFile) + fileName; // 临时存储路径
            item.write(new File(filePath)); // 实现文件保存
            item.delete(); // 删除已有的数据
            return fileName;
        }
        return null;
    }
    public String getUUIDName(FileItem item) {  // 实现文件名称的随机创建
        return UUID.randomUUID() + "." + item.getContentType().substring(item.getContentType().lastIndexOf("/") + 1);
    }
    public String getParameter(String paramName) {  // 根据参数名称获取参数内容
        if (this.uploadFlag) {  // 判断当前是否有文件上传
            if (this.paramMap.containsKey(paramName)) { // 当前的参数内容存在
                return this.paramMap.get(paramName) [0]; // 返回第一个内容
            }
            return null;
        }
        return this.request.getParameter(paramName); // 表单未封装，直接使用HttpServletRequest方法处理
    }
    public String[] getParameterValues(String paramName) {  // 根据参数名称获取一组参数内容
        if (this.uploadFlag) {  // 有文件上传
            if (this.paramMap.containsKey(paramName)) { // 存在有指定的key信息
                return this.paramMap.get(paramName); // 最终参数解析获取到的就是一个字符串数组
            }
            return null;
        }
        return this.request.getParameterValues(paramName); // 通过原始方式获取请求参数
    }
    public Set<String> getParameterNames() {    // 获取全部的参数名称
        if (this.uploadFlag) {
            return this.paramMap.keySet(); // 获得包装后的所有参数的名称集合
        }
        return this.request.getParameterMap().keySet(); // 原始方式获取参数的名称集合
    }
    public Map<String, String[]> getParameterMap() {
        if (this.uploadFlag) {
            return this.paramMap;
        }
        return this.request.getParameterMap();
    }
    // 获取指定参数的临时文件的保存名称，同时生成新的目标文件的保存名称
    public List<String> getUUIDName(String paramName) throws Exception {
        List<String> uuidNames = new ArrayList<>(); // 保存最终生成的目标文件的名称
        String fileNames [] = this.paramMap.get(paramName); // 获取临时文件的名称集合
        for (String fileName : fileNames) { // 数组迭代
            uuidNames.add("yootk." + UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1));
        }
        return uuidNames; // 返回目标存储的文件名称
    }
    // 文件的保存，而uploadFileNames就是getUUIDName()方法所生成的文件名称
    public void saveUploadFile(String paramName, List<String> uploadFileNames) throws Exception {
        String fileNames [] = this.paramMap.get(paramName); // 获取临时文件名称
        for (int x = 0; x < fileNames.length; x++) {
            File srcFile = new File(this.request.getServletContext().getRealPath(this.tempFile) + fileNames[x]);
            File destFile = new File(this.request.getServletContext().getRealPath(this.uploadFile) + uploadFileNames.get(x));
            InputStream input = new FileInputStream(srcFile); // 临时文件输入流
            OutputStream output = new FileOutputStream(destFile);  // 临时文件输出流
            input.transferTo(output); // 文件的转存
            input.close();
            output.close();
        }
    }
    // 现在目标文件都已经保存在了upload目录下了，那么就需要清除temp目录下的内容
    public void clean() {
        if (this.tempFileNames != null && this.tempFileNames.size() > 0) {
            for (String fileName : this.tempFileNames) {
                String filePath = this.request.getServletContext().getRealPath(this.tempFile) + fileName;
                File file = new File(filePath);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }
    public List<String> getTempFileNames() {    // 获取所有临时目录下的文件名称
        return tempFileNames;
    }

    /**
     * 对已经上传的文件（保存在临时目录之中的文件）进行MIME类型检测
     * @param paramName 上传文件的参数名称
     * @param mimeTypes 所有允许你使用的MIME类型
     * @return 如果类型相符合返回true，否则返回false
     */
    public boolean uploadMimeCheck(String paramName, List<String> mimeTypes) {
        // 所有的表单的提交信息都保存在了ParamMap集合里面，所以要进行文件的验证之前需要进行参数是否存在的判断
        if (this.map.containsKey(paramName)) { // 当前的参数存在
            List<FileItem> items = this.map.get(paramName); // 获取上传文件
            for (FileItem item : items) {
                if (item.getSize() > 0) {   // 当前存在有上传文件
                    if (!mimeTypes.contains(item.getContentType())) { // 当前的文件类型不匹配
                        return false;// 文件属于非法状态
                    }
                }
            }
        }
        return true; // 不需要进行文件的验证，直接返回true
    }

    /**
     * 获取全部的上传文件的集合信息
     * @return 上传文件集合
     */
    public Map<String, List<MultipartFile>> getAllUploadFile() {
        return allUploadFile;
    }
}
