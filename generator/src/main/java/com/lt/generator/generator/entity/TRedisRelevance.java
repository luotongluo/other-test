package com.lt.generator.generator.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 赋码和红字申请日志表(TRedisRelevance)实体类
 *
 * @author tong.luo
 * @since 2021-02-01 10:09:04
 */
public class TRedisRelevance implements Serializable {
    private static final long serialVersionUID = 617796974795201144L;
    /**
    * 主键
    */
    private Long id;
    /**
    * 纳税人识别号
    */
    private String nsrsbh;
    /**
    * 分机号
    */
    private String fjh;
    /**
    * 机器编号
    */
    private String jqbh;
    /**
    * 发票请求唯一流水号
    */
    private String fpqqlsh;
    /**
    * Redis中存储信息的类型（1非机动车发票;4机动车发票;2红字发票申请单;3红字通知单下载请求）
    */
    private Integer xxlx;
    /**
    * 处理状态, 0=待处理，1=处理中,2=赋码成功,3=不再处理
    */
    private String status;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 最后更新时间
    */
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getFjh() {
        return fjh;
    }

    public void setFjh(String fjh) {
        this.fjh = fjh;
    }

    public String getJqbh() {
        return jqbh;
    }

    public void setJqbh(String jqbh) {
        this.jqbh = jqbh;
    }

    public String getFpqqlsh() {
        return fpqqlsh;
    }

    public void setFpqqlsh(String fpqqlsh) {
        this.fpqqlsh = fpqqlsh;
    }

    public Integer getXxlx() {
        return xxlx;
    }

    public void setXxlx(Integer xxlx) {
        this.xxlx = xxlx;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

}