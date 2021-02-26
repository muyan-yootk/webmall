package com.yootk.common.servlet;

import com.yootk.common.mvc.util.ParameterUtil;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ServletObject {
    private static final ThreadLocal<HttpServletRequest> THREAD_REQUEST = new ThreadLocal<>();
    private static final ThreadLocal<HttpServletResponse> THREAD_RESPONSE = new ThreadLocal<>();
    private static final ThreadLocal<ParameterUtil> THREAD_PARAMETER = new ThreadLocal<>();
    private ServletObject() {} // 构造方法私有化
    public static void setParameterUtil(ParameterUtil parameterUtil) {
        THREAD_PARAMETER.set(parameterUtil);
    }
    public static ParameterUtil getParameterUtil() {
        return THREAD_PARAMETER.get();
    }
    public static void setRequest(HttpServletRequest request) {
        THREAD_REQUEST.set(request);
    }
    public static void setResponse(HttpServletResponse response) {
        THREAD_RESPONSE.set(response);
    }
    public static HttpServletRequest getRequest() {
        return THREAD_REQUEST.get();
    }
    public static HttpServletResponse getResponse() {
        return THREAD_RESPONSE.get();
    }
    public static HttpSession getSession() {
        return THREAD_REQUEST.get().getSession();
    }
    public static ServletContext getApplication() {
        return THREAD_REQUEST.get().getServletContext();
    }
    public static void clean() {
        THREAD_PARAMETER.get().clean();// 清除所有的上传临时文件
        THREAD_REQUEST.remove();
        THREAD_RESPONSE.remove();
        THREAD_PARAMETER.remove();
    }
    public static void out() {
        System.out.println("【PARAMETER_UTIL】" + THREAD_PARAMETER.get());
        System.out.println("【REQUEST】" + THREAD_REQUEST.get());
        System.out.println("【RESPONSE】" + THREAD_RESPONSE.get());
    }
}
