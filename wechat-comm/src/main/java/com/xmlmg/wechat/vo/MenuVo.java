package com.xmlmg.wechat.vo;

import java.util.List;

public class MenuVo {

    private Integer pid;

    private Integer father;

    private String icon;

    private String resources;

    private String title;

    private List<MenuVo> children;

    public MenuVo() {
    }

    public MenuVo(Integer pid, Integer father, String icon, String resources, String title) {
        this.pid = pid;
        this.father = father;
        this.icon = icon;
        this.resources = resources;
        this.title = title;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getFather() {
        return father;
    }

    public void setFather(Integer father) {
        this.father = father;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public List<MenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVo> children) {
        this.children = children;
    }
}
