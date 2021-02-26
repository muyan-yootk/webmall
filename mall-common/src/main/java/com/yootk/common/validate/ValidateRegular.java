package com.yootk.common.validate;

import com.yootk.common.servlet.ServletObject;

import java.util.Arrays;
import java.util.List;

public enum ValidateRegular { // 定义验证处理
    STRING {    // 该内容不允许为空（null和“""”都不允许）
        @Override
        public boolean check(String... value) { // 方法覆写
            if (value == null || value.length == 0) {
                return false; // 验证不通过
            }
            if (value.length != 1) {    // 单内容判断
                return false;
            }
            if (value[0] == null || "".equals(value[0])) {
                return false;
            }
            return true; // 验证通过
        }
    },
    INT {   // 该内容必须是一个整数
        @Override
        public boolean check(String... value) {
            if (ValidateRegular.STRING.check(value)) {  // 数据不为空
                return value[0].matches("\\d+"); // 由数字所组成
            }
            return false;
        }
    },
    LONG {   // 该内容必须是一个整数
        @Override
        public boolean check(String... value) {
            if (ValidateRegular.STRING.check(value)) {  // 数据不为空
                return value[0].matches("\\d+"); // 由数字所组成
            }
            return false;
        }
    },
    DOUBLE {   // 该内容必须是一个小数
        @Override
        public boolean check(String... value) {
            if (ValidateRegular.STRING.check(value)) {  // 数据不为空
                return value[0].matches("\\d+(\\.\\d+)?"); // 由数字所组成
            }
            return false;
        }
    },
    BOOLEAN {
        @Override
        public boolean check(String... value) {
            if (ValidateRegular.STRING.check(value)) {  // 数据不为空
                return "true".equals(value[0]) || "false".equals(value[0]);
            }
            return false;
        }
    },
    DATE { // 该内容必须是一个日期（yyyy-MM-dd）
        @Override
        public boolean check(String... value) {
            if (ValidateRegular.STRING.check(value)) {  // 数据不为空
                return value[0].matches("\\d{4}-\\d{2}-\\d{2}"); // 由数字所组成
            }
            return false;
        }
    },
    DATETIME { // 该内容必须是一个日期时间（yyyy-MM-dd HH:mm:ss）
        @Override
        public boolean check(String... value) {
            if (ValidateRegular.STRING.check(value)) {  // 数据不为空
                return value[0].matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"); // 由数字所组成
            }
            return false;
        }
    },
    RAND { // 该内容需要进行验证码的匹配
        @Override
        public boolean check(String... value) {
            if (ValidateRegular.STRING.check(value)) {  // 数据不为空
                String rand = (String) ServletObject.getSession().getAttribute("rand");
                if (ValidateRegular.STRING.check(rand)) {
                    return rand.equalsIgnoreCase(value[0]);
                }
            }
            return false;
        }
    },
    INT_ARRAY { // 传递的是一个整型数组
        @Override
        public boolean check(String... value) {
            if (value == null || value.length == 0) {
                return false;
            }
            for (String str : value) {  // 数据的循环判断
                if (str == null || "".equals(str)) {
                    return false;
                } else {
                    if (!str.matches("\\d+")) { // 不是数字组成
                        return false;
                    }
                }
            }
            return true;
        }
    },
    LONG_ARRAY { // 传递的是一个整型数组
        @Override
        public boolean check(String... value) {
            if (value == null || value.length == 0) {
                return false;
            }
            for (String str : value) {  // 数据的循环判断
                if (str == null || "".equals(str)) {
                    return false;
                } else {
                    if (!str.matches("\\d+")) { // 不是数字组成
                        return false;
                    }
                }
            }
            return true;
        }
    },
    STRING_ARRAY { // 传递的是一个字符串数组（数组中的每一个内容都不允许为空）
        @Override
        public boolean check(String... value) {
            if (value == null || value.length == 0) {
                return false;
            }
            for (String str : value) {  // 数据的循环判断
                if (str == null || "".equals(str)) {
                    return false;
                }
            }
            return true;
        }
    },
    IMAGE {
        @Override
        public boolean check(String... value) {
            // 如果在后续设计之中，你需要进行此类的MIME信息扩充，可以通过配置文件定义
            List<String> mimeTypes = List.of("image/bmp", "image/png", "image/jpg", "image/jpeg");
            if (value == null) {
                return true;
            }
            System.out.println("【Image规则验证】" + Arrays.toString(value));
            return ServletObject.getParameterUtil().uploadMimeCheck(value[0], mimeTypes);
        }
    };
    /**
     * 定义一个公共的抽象方法，实现具体的数据检查，因为所有传入的数据类型都是字符串
     * request.getParameter()方法可以返回的类型就是字符串
     * @param value 要验证的数据内容
     * @return 验证通过返回true，否则返回false
     */
    public abstract boolean check(String ... value);
}
