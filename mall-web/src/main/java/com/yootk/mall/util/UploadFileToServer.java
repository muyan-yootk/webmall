package com.yootk.mall.util;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.File;

public class UploadFileToServer {
    public static final String UPLOAD_URL = "http://upload-server/upload.action" ;
    public static String upload(File file,String contentType) throws Exception {
        // 1、创建一个可以被关闭的httpClient的对象
        CloseableHttpClient httpClient = HttpClients.createDefault() ; // 创建一个默认的HttpClient
        // 2、创建一个POST请求模式
        HttpPost post = new HttpPost(UPLOAD_URL) ; // 将请求地址传入到post请求之中
        // 3、此时因为要进行文件的上传，所以一定要对表单进行封装
        MultipartEntityBuilder builder = MultipartEntityBuilder.create() ; // 创建上传的封装“multipart/form-data”
        // msg是一个普通的文本行参数，所以设置参数的呢日哦国内，同时实现参数的类型以及编码的定义
        builder.addPart("contentType",new StringBody(contentType, ContentType.create("text/plain", Consts.UTF_8))) ;
        // photo是一个二进制的图片信息，所以必须进行二进制数据的包装，设置MIME类型，设置原始文件名称
        builder.addPart("file",new FileBody(file,ContentType.create(contentType),file.getName())) ;
        // 4、所有的POST请求是被HttpPost类封装的，那么需要将所有的请求实体包装在此类之中
        post.setEntity(builder.build()); //  将封装的表单的实体直接通过post请求发送到服务器端
        // 5、发送GET请求，随后获取相应的响应信息
        CloseableHttpResponse response = httpClient.execute(post) ; // 通过指定的HttpClient发送一个POST请求
        // 6、所有的请求回应包含两个部分，一个是数据部分，另外一个是头信息
        return EntityUtils.toString(response.getEntity());    // 输出转换
    }
}
