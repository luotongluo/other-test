package com.lt.springcloudtest.bean;

import java.util.Date;
import java.io.Serializable;

/**
 * (TestBinaryCode)实体类
 *
 * @author tong.luo
 * @since 2021-01-27 16:35:29
 */
public class TestBinaryCode implements Serializable {
    private static final long serialVersionUID = 369335836476915545L;
    /**
     * id
     */
    private Integer id;
    /**
     * url
     */
    private String url;
    /**
     * create_time
     */
    private Date createTime;
    /**
     * update_time
     */
    private Date updateTime;
    /**
     * 0已创建，1已生成
     */
    private Integer status;
    /**
     * create_id
     */
    private String createId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

}