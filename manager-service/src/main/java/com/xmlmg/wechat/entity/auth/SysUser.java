package com.xmlmg.wechat.entity.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.Set;
@ApiModel(value="用户对象",description="系统用户")
public class SysUser {
    @ApiModelProperty(hidden = true)
    private String uid;

    @ApiModelProperty(hidden = true)
    private String avatar;

    @ApiModelProperty(name = "登录用户名", required = true)
    private String username;

    @ApiModelProperty(hidden = true)
    private String nickname;

    @ApiModelProperty(name = "密码", required = true)
    private String password;

    @ApiModelProperty(hidden = true)
    private String phone;

    @ApiModelProperty(hidden = true)
    private String mail;

    @ApiModelProperty(hidden = true)
    private Integer state;

    @ApiModelProperty(hidden = true)
    private Date addTime;

    @ApiModelProperty(hidden = true)
    private Date upTime;

    @ApiModelProperty(hidden = true)
    private Integer dept;

    @ApiModelProperty(hidden = true)
    private Set<SysRole> roles;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public Integer getDept() {
        return dept;
    }

    public void setDept(Integer dept) {
        this.dept = dept;
    }

    public Set<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }
}