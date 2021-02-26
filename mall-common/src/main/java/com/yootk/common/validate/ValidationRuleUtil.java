package com.yootk.common.validate;

import com.yootk.common.mvc.bean.ControllerRequestMapping;
import com.yootk.common.mvc.util.ResourceUtil;
import com.yootk.common.servlet.ServletObject;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ValidationRuleUtil { // 验证规则类
    /**
     * 将读取到的验证规则进行拆分操作，同时调用具体的验证处理方法进行验证
     * @param rule 全部的验证规则
     * @return 所有的错误信息，KEY为参数名称、VALUE为错误信息（Message.properties定义的）
     */
    public static Map<String, String> validate(String rule) {
        // rule：title:string|content:string|url:string[]
        Map<String, String> errors = new HashMap<>();
        String ruleResults [] = rule.split("\\|"); // 数据拆分
        for (int x = 0; x < ruleResults.length; x ++) { // 规则循环
            String temp [] = ruleResults[x].split(":"); // 第一个内容为请求参数，第二个为规则
            if (!check(temp[1], temp[0])) { // 验证失败
                // 此时Map集合之中的KEY的内容为参数名称，而VALUE的内容为错误信息，而错误信息在Message.properties定义
                // 此时在进行资源获取的时候ResourceUtil之中的资源项是通过外部设置的
                errors.put(temp[0], ResourceUtil.getString("validation.error." + temp[1]));
            }
        }
        return errors;
    }
    public static String getValidateRule(String baseName, ControllerRequestMapping crm) {
        if (baseName == null) { // 没有读取资源的名称
            return null;
        }
        String rule = null; // 保存读取后的规则信息
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(baseName);
            String validationKey = crm.getActionClazz().getName() + "." + crm.getActionMethod().getName(); // 验证规则KEY
            rule = bundle.getString(validationKey); // 获取规则
        } catch (Exception e) {}
        return rule;
    }
    public static String getErrorPage(String baseName, ControllerRequestMapping mapping) {
        return getValidateRule(baseName, mapping);
    }
    /**
     * 根据当前传入的验证的规则进行请求参数的结构验证
     * @param regular 验证规则
     * @param paramName 请求参数的名称
     * @return 验证通过返回true，否则返回false
     */
    private static boolean check(String regular, String paramName) {
        // 本次在进行数据验证的时候，全部的类型使用的都是数组
        String value [] = ServletObject.getParameterUtil().getParameterValues(paramName);
        switch (regular) {  // 规则配置
            case "string": // 字符串的规则
                return ValidateRegular.STRING.check(value);
            case "int":
                return ValidateRegular.INT.check(value);
            case "long":
                return ValidateRegular.LONG.check(value);
            case "double":
                return ValidateRegular.DOUBLE.check(value);
            case "boolean":
                return ValidateRegular.BOOLEAN.check(value);
            case "date":
                return ValidateRegular.DATE.check(value);
            case "datetime":
                return ValidateRegular.DATETIME.check(value);
            case "rand":
                return ValidateRegular.RAND.check(value);
            case "int[]":
                return ValidateRegular.INT_ARRAY.check(value);
            case "long[]":
                return ValidateRegular.LONG_ARRAY.check(value);
            case "string[]":
                return ValidateRegular.STRING_ARRAY.check(value);
            case "image":
                return ValidateRegular.IMAGE.check(paramName);
        }
        return false;
    }
}
