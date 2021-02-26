package com.yootk.common.servlet;

import java.util.Map;

public class ModelAndView {
    private String view; // 视图路径（跳转路径）
    public ModelAndView() {}
    public ModelAndView(String view) {
        this.view = view;
    }
    public ModelAndView(String view, String name, Object value) {
        this.view = view;
        this.add(name, value);
    }
    public ModelAndView(String view, Map<String, Object> map) {
        this.view = view;
        this.add(map);
    }
    public void add(String name, Object value) {    // 属性的存储
        ServletObject.getRequest().setAttribute(name, value);
    }
    public void add(Map<String, Object> map) {
       for (Map.Entry<String, Object> entry : map.entrySet()) {
           ServletObject.getRequest().setAttribute(entry.getKey(), entry.getValue());
       }
    }
    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
