package com.xmlmg.wechat.entity.auth;

import java.util.Date;
import java.util.Set;

public class SysPermission {

    private Integer pid;

    private Integer father;

    private String resources;

    private String title;

    private String icon;

    private String type;

    private Date addTime;

    private Date upTime;

    private String describe;

    private Set<SysPermission> childNodes;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Set<SysPermission> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(Set<SysPermission> childNodes) {
        this.childNodes = childNodes;
    }
}

