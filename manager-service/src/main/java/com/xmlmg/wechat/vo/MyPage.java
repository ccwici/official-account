package com.xmlmg.wechat.vo;

/**
 * @author: Wang Chen Chen
 * @Date: 2018/10/26 9:54
 * @describe：
 * @version: 1.0
 */

public class MyPage {

    private int pageNum = 1;

    private int pageSize = 10;

    private String search = null;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
