package com.resourcepool.project.business.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.resourcepool.framework.aspectj.lang.annotation.Excel;
import com.resourcepool.framework.web.domain.BaseEntity;

/**
 * 企业通讯录对象 t_translate_book
 * 
 * @author 任遵强
 * @date 2023-06-07
 */
@Data
public class TTranslateBook extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long translateBookId;

    /** 企业id */
    @Excel(name = "企业id")
    private Long translateId;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String translateName;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contacts;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 省份-城市 */
    @Excel(name = "省份-城市")
    private String addr;

    /** 客户需求 */
    @Excel(name = "客户需求")
    private String customerDemand;

    /** 客户来源 */
    @Excel(name = "客户来源")
    private String sources;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 是否回收 0未回收 1已回收 */
    @Excel(name = "是否回收 0未回收 1已回收")
    private Integer isRecovery;

    private String pass = "****";

    /** 备注 */
    @Excel(name = "备注")
    private String notes;

    private Long deptId;

    private Long connectCount;

}
