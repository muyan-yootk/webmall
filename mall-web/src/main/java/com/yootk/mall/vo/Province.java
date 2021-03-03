package com.yootk.mall.vo;

import java.io.Serializable;

public class Province implements Serializable {
    private Long pid;
    private String title;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
