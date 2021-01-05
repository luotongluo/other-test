package bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author tong.luo
 * @description ListBeanVo
 * @date 2021/1/5 16:25
 */
public class ListBeanVo implements Serializable {
    private static final long serialVersionUID = 6182148883011056649L;
    private List<TestBeanVo> testBeanVoList;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    public ListBeanVo() {
    }

    public ListBeanVo(List<TestBeanVo> testBeanVoList, Date createdDate) {
        this.testBeanVoList = testBeanVoList;
        this.createdDate = createdDate;
    }

    public List<TestBeanVo> getTestBeanVoList() {
        return testBeanVoList;
    }

    public void setTestBeanVoList(List<TestBeanVo> testBeanVoList) {
        this.testBeanVoList = testBeanVoList;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "ListBeanVo{" +
                "testBeanVoList=" + testBeanVoList +
                ", createdDate=" + createdDate +
                '}';
    }
}
