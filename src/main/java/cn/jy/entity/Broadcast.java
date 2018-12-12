package cn.jy.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Broadcast {
    private Long id;

    private Long enterpriseId;

    private String title;

    private String content;

    private Integer toEmployee;

    private Integer toCustomer;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Long sender;

    private Integer isRead = 1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getToEmployee() {
        return toEmployee;
    }

    public void setToEmployee(Integer toEmployee) {
        this.toEmployee = toEmployee;
    }

    public Integer getToCustomer() {
        return toCustomer;
    }

    public void setToCustomer(Integer toCustomer) {
        this.toCustomer = toCustomer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Integer getisRead() {
        return isRead;
    }

    public void setisRead(Integer isRead) {
        this.isRead = isRead;
    }
}