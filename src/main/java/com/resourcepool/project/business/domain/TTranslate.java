package com.resourcepool.project.business.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.resourcepool.framework.aspectj.lang.annotation.Excel;
import com.resourcepool.framework.web.domain.BaseEntity;

/**
 * 企业资源对象 t_translate
 * 
 * @author 任遵强
 * @date 2023-06-07
 */
@Data
public class TTranslate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
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

    /** 是否领取 0未领取 1已领取 */
    private Integer isUse;

    /** 是否删除 0未删除 1已删除 */
    private Integer isDel;

    private String pass = "****";

    /** 备注 */
    @Excel(name = "备注")
    private String notes;

}
