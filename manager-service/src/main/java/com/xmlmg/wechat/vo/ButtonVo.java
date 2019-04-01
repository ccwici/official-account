package com.xmlmg.wechat.vo;


/**
 * @author: Wang Chen Chen
 * @Date: 2018/10/29 15:33
 * @describe：
 * @version: 1.0
 */

public class ButtonVo {

    private Integer pid;

    private String resources;

    private String title;

    public ButtonVo() {
    }

    public ButtonVo(Integer pid, String resources, String title) {
        this.pid = pid;
        this.resources = resources;
        this.title = title;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
