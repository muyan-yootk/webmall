package com.yootk.common.service.abs;

public abstract class AbstractService {
    public boolean checkAge(int age) { // 年龄范围检测
        return age >= 18 && age <= 80;
    }
    public boolean checkSex(String sex) {
        if (sex == null || "".equals(sex)) {
            return false;
        }
        return sex.equals("男") || sex.equals("女");
    }
    /**
     * 判断传入的数据内容是否为空值
     * @param params 要判断的数据项
     * @return 如果数据为空返回false，如果不为空返回true
     */
    public boolean checkEmpty(String ... params) {
        for (String param : params) {
            if (param == null || "".equals(param)) {
                 return false;
            }
        }
        return true;
    }
}
