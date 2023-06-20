package com.resourcepool.project.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.resourcepool.framework.aspectj.lang.annotation.Excel;
import com.resourcepool.framework.web.domain.BaseEntity;

/**
 * 企业沟通记录对象 t_translate_connect
 * 
 * @author 任遵强
 * @date 2023-06-08
 */
public class TTranslateConnect extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long translateConnectId;

    /** 录入用户id */
    @Excel(name = "录入用户id")
    private Long userId;

    /** 录入用户 */
    @Excel(name = "录入用户")
    private String userName;

    /** 企业id */
    @Excel(name = "企业id")
    private Long translateId;

    /** 回访内容 */
    @Excel(name = "回访内容")
    private String content;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String translateName;

    public void setTranslateConnectId(Long translateConnectId) 
    {
        this.translateConnectId = translateConnectId;
    }

    public Long getTranslateConnectId() 
    {
        return translateConnectId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setTranslateId(Long translateId) 
    {
        this.translateId = translateId;
    }

    public Long getTranslateId() 
    {
        return translateId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setTranslateName(String translateName) 
    {
        this.translateName = translateName;
    }

    public String getTranslateName() 
    {
        return translateName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("translateConnectId", getTranslateConnectId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("translateId", getTranslateId())
            .append("content", getContent())
            .append("createTime", getCreateTime())
            .append("translateName", getTranslateName())
            .toString();
    }
}
