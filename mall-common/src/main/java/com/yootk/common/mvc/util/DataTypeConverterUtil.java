package com.yootk.common.mvc.util;

import com.yootk.common.servlet.MultipartFile;
import com.yootk.common.servlet.ServletObject;
import com.yootk.common.util.StringUtil;
import jakarta.servlet.Servlet;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DataTypeConverterUtil { // 数据类型转换处理
    // 如果字符串要想转为日期或者是日期时间，就必须考虑到多线程并发访问下的处理情况
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final ZoneId ZONE_ID = ZoneId.systemDefault(); // 获取默认的区域
    private DataTypeConverterUtil() { } // 构造方法私有化

    /**
     * 将接收到的请求数组参数的内容（字符串数组）转为特定的数据类型的数组（int[]、double[]、...）
     * @param paramName
     * @param type
     * @return
     */
    public static Object convertArray(String paramName, Class<?> type) {
        String values[] = ServletObject.getParameterUtil().getParameterValues(paramName); // 根据请求参数的名称获取数组内容
        if (values == null || values.length == 0) { // 没有数组内容
            return null; // 直接返回一个null的数据信息
        } else {    // 当前数组存在有内容，当前只考虑几种特定的数组
            if ("int[]".equals(type.getSimpleName())) { // 当前是否为整形数组
                int array[] = new int[values.length]; // 开辟一个新的数组
                for (int x = 0; x < array.length; x ++) {   // 实现数组的循环
                    if (values[x].matches("\\d+")) {    // 符合正则要求
                        array[x] = Integer.parseInt(values[x]); // 数据转换
                    }
                }
                return array;
            } else if ("Integer[]".equals(type.getSimpleName())) {
                Integer array[] = new Integer[values.length]; // 开辟一个新的数组
                for (int x = 0; x < array.length; x ++) {   // 实现数组的循环
                    if (values[x].matches("\\d+")) {    // 符合正则要求
                        array[x] = Integer.parseInt(values[x]); // 数据转换
                    }
                }
                return array;
            } else if ("long[]".equals(type.getSimpleName())) {
                long array[] = new long[values.length]; // 开辟一个新的数组
                for (int x = 0; x < array.length; x ++) {   // 实现数组的循环
                    if (values[x].matches("\\d+")) {    // 符合正则要求
                        array[x] = Long.parseLong(values[x]); // 数据转换
                    }
                }
                return array;
            } else if ("Long[]".equals(type.getSimpleName())) {
                Long array[] = new Long[values.length]; // 开辟一个新的数组
                for (int x = 0; x < array.length; x ++) {   // 实现数组的循环
                    if (values[x].matches("\\d+")) {    // 符合正则要求
                        array[x] = Long.parseLong(values[x]); // 数据转换
                    }
                }
                return array;
            } else if ("String[]".equals(type.getSimpleName())){
                return values;
            } else if ("MultipartFile[]".equals(type.getSimpleName())) { // 上传文件
                return ServletObject.getParameterUtil().getAllUploadFile().get(paramName).toArray(new MultipartFile[]{});
            }
        }
        return null; // 没有满足的判断返回null
    }

    /**
     * Action中方法接收的参数不再是基本类型（int、long、double、boolean、Date都属于基本类型），而是一个VO对象
     * 如果是VO对象，就必须通过里面的成员属性列表获取全部的成员属性名称
     * 根据这个名称再结合setXxx()方法调用来实现属性内容的设置，而这个内容就是通过用户的请求参数获取
     * @param obj 当前要操作的VO对象
     */
    public static void setObjectFieldValue(Object obj) {    // 设置对象的属性
        Field fields [] = obj.getClass().getDeclaredFields(); // 获取类中的全部成员属性
        for (Field field : fields) {    // 成员属性的循环
            try {// 所有的属性设置的方法名称采用的方式为：setXxx(数据类型 参数名称)
                Method setMethod = obj.getClass().getDeclaredMethod("set" +
                        StringUtil.initcap(field.getName()), field.getType());
                setMethod.invoke(obj, convert(field.getName(), field.getType()));// 方法调用
            } catch (Exception e) {}
        }
    }

    /**
     * 将指定名称的参数内容按照指定的类型进行转换，由于最终的数据转换需要通过反射传递，那么就返回Object即可
     * @param paramName 请求参数名称
     * @param type 目标接收的数据类型
     * @return 与目标数据类型一致的数据，或者是null
     */
    public static Object convert(String paramName, Class<?> type) {
        try {
            if (paramName == null || "".equals(paramName)) {    // 没有参数名称
                return null;
            }
            // 根据请求参数的名称实现请求参数的内容接收
            String value = ServletObject.getParameterUtil().getParameter(paramName);
            if (value == null || "".equals(value)) { // 没有任何的参数内容
                return null;
            }
            if ("int".equals(type.getName()) || "java.lang.Integer".equals(type.getName())) {   // 整型
                if (value.matches("\\d+")) {    // 匹配正则
                    return Integer.parseInt(value); // 字符串转为数字
                }
            } else if ("long".equals(type.getName()) || "java.lang.Long".equals(type.getName())) {
                if (value.matches("\\d+")) {    // 匹配正则
                    return Long.parseLong(value); // 字符串转为数字
                }
            } else if ("double".equals(type.getName()) || "java.lang.Double".equals(type.getName())) {
                if (value.matches("\\d+(\\d.\\d+)?")) {    // 匹配正则
                    return Double.parseDouble(value); // 字符串转为数字
                }
            } else if ("boolean".equals(type.getName()) || "java.lang.Boolean".equals(type.getName())) {
                return Boolean.parseBoolean(value);
            } else if ("java.util.Date".equals(type.getName())) {
                if (value.matches("\\d{4}-\\d{2}-\\d{2}")) {    // 匹配正则
                    LocalDate localDate = LocalDate.parse(value, DATE_FORMATTER);
                    Instant instant = localDate.atStartOfDay().atZone(ZONE_ID).toInstant();
                    return Date.from(instant);
                } else if (value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
                    LocalDate localDate = LocalDate.parse(value, DATE_TIME_FORMATTER);
                    Instant instant = localDate.atStartOfDay().atZone(ZONE_ID).toInstant();
                    return Date.from(instant);
                } else {
                    return null; // 是为了后续服务的
                }
            } else if ("java.lang.String".equals(type.getName())) {
                return value; // 字符串直接返回
            } else if ("com.yootk.common.servlet.MultipartFile".equals(type.getName())) {
                return ServletObject.getParameterUtil().getAllUploadFile().get(paramName).get(0);
            }
        } catch (Exception e) {} // 不需要考虑任何的异常处理
        return null;
    }
}
