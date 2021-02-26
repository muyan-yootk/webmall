package com.yootk.common.util;

import com.yootk.common.servlet.ServletObject;

public class PageUtil { // 分页工具类
    private Integer currentPage = 1; // 默认当前页
    private Integer lineSize = 10; // 默认每页显示的数据行数
    private String column; // 模糊查询列
    private String keyword; // 模糊查询关键字
    private String columnData; // column候选参数配置
    private String url; // 分页路径
    public PageUtil(String url) {   // 下一次的加载路径
        this(url, null);
    }
    public PageUtil(String url, String columnData) {
        this.url = url;
        this.columnData = columnData;
        this.splitHandle(); // 进行分页参数的控制
    }
    // 分页参数有可能没有传递，有可能传递是错误的，那么应该设置有默认值
    private void splitHandle() {    // 定义分页的处理方法
        try { // 如果出现了错误，那么当前页就是第一页
            this.currentPage = Integer.parseInt(ServletObject.getParameterUtil().getParameter("cp"));
        } catch (Exception e) {}
        try { // 如果出现了错误，使用默认的每页行数长度
            this.lineSize = Integer.parseInt(ServletObject.getParameterUtil().getParameter("ls"));
        } catch (Exception e) {}
        // 在业务层之中对这两个参数已经进行了判断，而这个判断在处理的时候就直接以null的形式完成
        this.column = ServletObject.getParameterUtil().getParameter("col");
        this.keyword = ServletObject.getParameterUtil().getParameter("kw");
        // 考虑到后续的应用还有可能要继续使用分页操作，所以要将这部分的信息继续向后续的传递
        ServletObject.getRequest().setAttribute("currentPage", this.currentPage);
        ServletObject.getRequest().setAttribute("lineSize", this.lineSize);
        ServletObject.getRequest().setAttribute("column", this.column);
        ServletObject.getRequest().setAttribute("keyword", this.keyword);
        ServletObject.getRequest().setAttribute("url", this.url);
        ServletObject.getRequest().setAttribute("columnData", this.columnData);
    }
    // 随后定义若干个数据获取的操作，因为分页参数处理完成之后肯定要把信息交给业务层进行数据加载

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getLineSize() {
        return lineSize;
    }

    public void setLineSize(Integer lineSize) {
        this.lineSize = lineSize;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getColumnData() {
        return columnData;
    }

    public void setColumnData(String columnData) {
        this.columnData = columnData;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
