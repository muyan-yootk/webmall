package com.yootk.common.mvc.util;

import com.yootk.common.mvc.bean.ControllerRequestMapping;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ScannerPackageUtil { // 扫描工具类
    // 保存整个项目之中全部控制器的访问映射处理，通过ConfigAnnotationParseUtil工具处理
    private static final Map<String, ControllerRequestMapping> ACTION_MAP = new HashMap<>();
    // 保存整个项目之中全部根据名称匹配的Class实例
    private static final Map<String, Class> BY_NAME_MAP = new HashMap<>();
    // 保存整个项目之中全局根据类型匹配的Class实例（根据类型实现自动注入管理）
    private static final Map<Class, Class> BY_TYPE_MAP = new HashMap<>();
    // 这个路径是随着项目的部署而动态获取的，此时的设计不考虑打包为*.jar文件后的处理
    private static String baseDir = null; // 公共的项目路径
    private ScannerPackageUtil() {} // 构造方法私有化

    /**
     * 实现扫描包的配置处理
     * @param clazz 调用此类的程序类，一般就是DispatcherServlet
     * @param packages 配置的扫描包名称，使用“;”进行分割
     */
    public static void scannerHandle(Class<?> clazz, String packages) {    // 进行解析处理
        if (packages == null || "".equals(packages)) {  // 没有配置扫描包
            return; // 结束方法调用
        }
        String resultPackages[] = packages.split(";"); // 按照“;”拆分
        // 传入的Class类型是DispatcherServlet程序类
        baseDir = clazz.getResource("/").getPath();// 获取具体路径
        baseDir = baseDir.substring(1).replace("/", File.separator); // 更换路径符号
        for (int x = 0; x < resultPackages.length; x++) {   // 扫描包的拼凑
            String subDir = resultPackages[x].replace(".", File.separator); // 获取子路径
            listDirClass(new File(baseDir, subDir)); // 列出所有的Class名称
        }
    }
    public static void listDirClass(File file) {    // 目录的列出
        if (file.isDirectory()) {   // 当前给定的是一个目录
            File result [] = file.listFiles(); // 目录列表
            if (result != null) {   // 有可能无法列出信息
                for (int x = 0; x < result.length; x ++) {
                    listDirClass(result[x]); // 递归的目录列表
                }
            }
        } else {    // 不是目录
            if (file.isFile()) {    // 给定的是一个文件
                String className = file.getAbsolutePath().replace(baseDir, "") // 替换掉父路径
                        .replace(File.separator, ".").replace(".class", ""); // 类名称
                try {   // 通过之前的解析工具类根据指定的类名称获取对应的程序结构
                    ConfigAnnotationParseUtil parseUtil = new ConfigAnnotationParseUtil(Class.forName(className));
                    ACTION_MAP.putAll(parseUtil.getControllerMapResult()); // 获取全部控制层
                    BY_NAME_MAP.putAll(parseUtil.getNameAndTypeMap());
                    BY_TYPE_MAP.putAll(parseUtil.getObjectInterfaceAndClassMap());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static Map<String, ControllerRequestMapping> getActionMap() {
        return ACTION_MAP;
    }

    public static Map<String, Class> getByNameMap() {
        return BY_NAME_MAP;
    }

    public static Map<Class, Class> getByTypeMap() {
        return BY_TYPE_MAP;
    }
}
