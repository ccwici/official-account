package com.xmlmg.wechat.entity;

public class BaseMessage {
    
    private String toUserName;
    private String fromUserName;
    private long createTime;
    private String msgType;
    private int funcFlag;
  
    public String getToUserName() {  
        return toUserName;
    }  
  
    public void setToUserName(String toUserName) {  
        this.toUserName = toUserName;
    }  
  
    public String getFromUserName() {  
        return fromUserName;
    }  
  
    public void setFromUserName(String fromUserName) {  
        this.fromUserName = fromUserName;
    }  
  
    public long getCreateTime() {  
        return createTime;
    }  
  
    public void setCreateTime(long createTime) {  
        this.createTime = createTime;
    }  
  
    public String getMsgType() {  
        return msgType;
    }  
  
    public void setMsgType(String msgType) {  
        this.msgType = msgType;
    }  
  
    public int getFuncFlag() {  
        return funcFlag;
    }  
  
    public void setFuncFlag(int funcFlag) {  
        this.funcFlag = funcFlag;
    } 
}