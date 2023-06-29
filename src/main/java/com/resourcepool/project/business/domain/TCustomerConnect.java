package com.resourcepool.project.business.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.resourcepool.framework.aspectj.lang.annotation.Excel;
import com.resourcepool.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 客户沟通记录对象 t_customer_connect
 * 
 * @author 任遵强
 * @date 2023-06-07
 */
@Data
public class TCustomerConnect extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long customerConnectId;

    /** 录入用户id */
    @Excel(name = "录入用户id")
    private Long userId;

    /** 录入用户 */
    @Excel(name = "录入用户")
    private String userName;

    /** 客户id */
    @Excel(name = "客户id")
    private Long customerId;

    /** 回访内容 */
    @Excel(name = "回访内容")
    private String content;

    private String customerName;

    private Date endTime;

    private Long bookId;

}
