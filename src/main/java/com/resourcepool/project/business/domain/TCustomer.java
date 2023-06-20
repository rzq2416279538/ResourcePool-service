package com.resourcepool.project.business.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.resourcepool.framework.aspectj.lang.annotation.Excel;
import com.resourcepool.framework.web.domain.BaseEntity;

/**
 * 客户资源对象 t_customer
 * 
 * @author 任遵强
 * @date 2023-06-07
 */

@Data
public class TCustomer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long customerId;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String customerName;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String customerPhone;

    /** 是否领取 0未领取 1已领取 */
//    @Excel(name = "是否领取 0未领取 1已领取")
    private Integer isUse;

    /** 证书类型 */
    @Excel(name = "证书类型")
    private String certificateType;

    /** 证书专业 */
    @Excel(name = "证书专业")
    private String certificateSpeciality;

    @Excel(name = "初始/转注")
    private String remark;

    /** 省份-城市 */
    @Excel(name = "省份-城市")
    private String addr;

    /** 客户来源 */
    @Excel(name = "客户来源")
    private String sources;

    /** 是否删除 0未删除 1已删除 */
//    @Excel(name = "是否删除 0未删除 1已删除")
    private Integer isDel;

    private String pass = "****";

    /** 备注 */
    @Excel(name = "备注")
    private String notes;



}
