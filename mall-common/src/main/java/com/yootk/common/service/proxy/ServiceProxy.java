package com.yootk.common.service.proxy;



import com.yootk.common.util.DatabaseConnection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.List;

public class ServiceProxy implements InvocationHandler { // 原生代理设计模式
    private static final List<String> TRANSACTION_METHOD_HEAD = List.of(
            "add", "create", "edit", "update", "delete", "remove", "change", "move");
    private Object target; // 代理的真实对象实例
    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null; // 保存了返回结果
        boolean trsancationFlag = this.openTransaction(method.getName()); // 获取事务开启的标记
        if (trsancationFlag) {  // 开启事务
            DatabaseConnection.getConnection().setAutoCommit(false); // 取消自动提交
        }
        try {
            result = method.invoke(this.target, args); // 方法的反射调用
            if (trsancationFlag) {
                DatabaseConnection.getConnection().commit(); // 提交事务
            }
        } catch (Exception e) {
            if (trsancationFlag) {
                DatabaseConnection.getConnection().rollback(); // 事务回滚
            }
            throw e;
        } finally { // 最终一定要执行的部分
            DatabaseConnection.close(); // 关闭数据库连接
        }
        return result;
    }
    /**
     * 判断当前调用的方法名称是否要开启事务控制
     * @param methodName 要执行的方法
     * @return 如果需要开启事务则返回true，否则返回false
     */
    private boolean openTransaction(String methodName) {
        Iterator<String> iter = TRANSACTION_METHOD_HEAD.iterator();
        while (iter.hasNext()) {
            if (methodName.startsWith(iter.next())) {
                return true; // 需要事务支持
            }
        }
        return false; // 不需要开启事务
    }
}
