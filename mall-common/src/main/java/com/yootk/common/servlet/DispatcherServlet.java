package com.yootk.common.servlet;

import com.yootk.common.mvc.bean.ActionUtil;
import com.yootk.common.mvc.bean.ControllerRequestMapping;
import com.yootk.common.mvc.bean.DependObject;
import com.yootk.common.mvc.util.ParameterUtil;
import com.yootk.common.mvc.util.ResourceUtil;
import com.yootk.common.mvc.util.ScannerPackageUtil;
import com.yootk.common.validate.ValidationRuleUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;

public class DispatcherServlet extends HttpServlet {
    public static final String DEFAULT_ERROR_PAGE = "/errors.jsp"; // 公共错误页
    private String errorPage = null; // 保存错误页的资源
    private String validationBaseName = null; // 验证规则资源
    private String errorPageBaseName = null; // 错误页处理
    private String messageBaseName = null; // 提示信息的资源
    // 【验证标记】true：没有配置Validation.properties、false：配置Validation.properties
    private boolean validateFlag;
    // 【错误页】true：没有配置ErrorPage.propertries、false：配置ErrorPage.properties
    private boolean errorFlag;
    @Override
    public void init() throws ServletException {    // 初始化的时候进行扫描配置
        // 获取ServletContext所配置的初始化上下文参数
        String basePackage = super.getServletContext().getInitParameter("base-package"); // 获取初始化参数
        ScannerPackageUtil.scannerHandle(super.getClass(), basePackage); // 扫描处理
        this.validationBaseName = super.getServletConfig().getInitParameter("validationBaseName");
        this.errorPageBaseName = super.getServletConfig().getInitParameter("errorPageBaseName");
        this.messageBaseName = super.getServletConfig().getInitParameter("messageBaseName");
        if (this.messageBaseName != null) { // 当前配置了消息资源
            ResourceUtil.setMessageBaseName(this.messageBaseName); // 可以读取Message.properties文件
        }
        this.validateFlag = this.validationBaseName == null || "".equals(this.validationBaseName); // 不需要验证
        this.errorFlag = this.errorPageBaseName == null || "".equals(this.errorPageBaseName); // 没有错误页
        if (this.errorFlag) {   // 没有具体的错误页
            this.errorPage = DEFAULT_ERROR_PAGE; // 默认错误页
        } else {    // 配置了错误页
            try {
                this.errorPage = ResourceBundle.getBundle(this.errorPageBaseName).getString("global.error.page");
            } catch (Exception e) {
                this.errorPage = DEFAULT_ERROR_PAGE;
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dispatcherPath = null; // 跳转路径
        // 获取了当前请求路径，而这个路径恰好就是Action配置的映射路径
        String path = req.getServletPath().substring(0, req.getServletPath().lastIndexOf("."));
        // 包扫描完成之后，所有的映射路径实际上都保存在了“Map”集合之中
        ControllerRequestMapping mapping = ScannerPackageUtil.getActionMap().get(path);
        // 此时可以获取到映射的Action类（Class实例）以及对应的Method实例
//        ScannerPackageUtil.getActionMap().entrySet().forEach(
//                System.out::println
//        );
        try {
            ServletObject.setRequest(req); // 在当前的线程内保存有request
            ServletObject.setResponse(resp); // 在当前的线程内保存有response
            Object actionObject = mapping.getActionClazz().getDeclaredConstructor().newInstance(); // 反射获取Action类对象
            String uploadPath = ActionUtil.getUpload(actionObject); // 解析上传目录
            ParameterUtil parameterUtil = new ParameterUtil(req, uploadPath);
            ServletObject.setParameterUtil(parameterUtil);
            Map<String, String> errors = null; // 保存所有的错误信息
            if (!this.validateFlag) {   // 现在存在有验证需要
                // 根据当前请求的Action的名称以及方法名称获取方法的验证规则项
                String rule = ValidationRuleUtil.getValidateRule(this.validationBaseName, mapping);
                if (rule != null) { // 当前需要进行验证
                    errors = ValidationRuleUtil.validate(rule); // 规则验证
                }
            }
            if (errors == null || errors.size() == 0) { // 没有错误
                // 以下为具体的Action调用处理操作，那么验证应该放在Action调用之前完成
                // 现在传入的是Action层，则第一次会进行Action结构处理（可以获取到业务层）
                // 而后第二次自己调用的的时候就可以通过业务层获取数据层
                new DependObject(actionObject).injectObject(); // 实现对象注入操作
                // 获取当前Action方法要调用的参数具体内容
                Object params[] = ActionUtil.getMethodParameterValue(actionObject, mapping.getActionMethod());
                // 在进行方法反射调用的时候会有返回内容，而返回的内容统一类型为Object
                Object returnValue = mapping.getActionMethod().invoke(actionObject, params); // 方法的反射调用
                if (returnValue != null) {  // 有返回值，方法的返回值不是void
                    if (returnValue instanceof String) {    // 当前方法返回了字符串
                        dispatcherPath = returnValue.toString(); // 直接获取跳转路径
                    }
                    if (returnValue instanceof ModelAndView) {  // 返回ModelAndView
                        ModelAndView modelAndView = (ModelAndView) returnValue;
                        dispatcherPath = modelAndView.getView(); // 获取跳转的视图路径
                    }
                }
            } else {    // 此时存在有错误
                if (!this.errorFlag) {  // 配置了错误页
                    dispatcherPath = ValidationRuleUtil.getErrorPage(this.errorPageBaseName, mapping); // 获取错误页
                    if (dispatcherPath == null || "".equals(dispatcherPath)) {
                        dispatcherPath = this.errorPage; // 使用默认的错误页
                    }
                }
                req.setAttribute("errors", errors); // 将所有的错误信息通过request属性进行传递
            }
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
             ServletObject.clean(); // 删除当前线程request/response
        }
        if (dispatcherPath != null) {   // 有跳转路径配置
            req.getRequestDispatcher(dispatcherPath).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
