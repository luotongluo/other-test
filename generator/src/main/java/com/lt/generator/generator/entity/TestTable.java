package com.lt.generator.generator.entity;

import java.io.Serializable;
import java.util.Date;

public class TestTable implements Serializable {
    private static final long serialVersionUID = 5387559424517597031L;
    private Integer id;

    private String name;

    private Date createTime;

    private Integer age;

    private String createId;

    private Date updateTime;


    public TestTable(Integer id, String name, Date createTime, Integer age, String createId, Date updateTime) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
        this.age = age;
        this.createId = createId;
        this.updateTime = updateTime;
    }

    public TestTable() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", createTime=").append(createTime);
        sb.append(", age=").append(age);
        sb.append(", createId=").append(createId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}