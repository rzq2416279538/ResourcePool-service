package com.resourcepool.project.business.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.resourcepool.framework.aspectj.lang.annotation.Excel;
import com.resourcepool.framework.web.domain.BaseEntity;

/**
 * 客户通讯录对象 t_customer_book
 * 
 * @author 任遵强
 * @date 2023-06-07
 */
@Data
public class TCustomerBook extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long bookId;

    /** 客户id */
//    @Excel(name = "客户id")
    private Long customerId;

    @Excel(name = "客户名称")
    private String customerName;

    /** 用户id */
//    @Excel(name = "用户id")
    private Long userId;

    /** 是否回收 0未回收 1已回收 */
//    @Excel(name = "是否回收 0未回收 1已回收")
    private Integer isRecovery;

    /** 到期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "到期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    private String customerPhone;

    private String certificateType;

    private String certificateSpeciality;

    private String remark;

    private String addr;

    private String pass = "****";

    private String userName;

    private String sources;

    /** 备注 */
    @Excel(name = "备注")
    private String notes;

    private Long deptId;

    private Long connectCount;

    private String connectContent;

}
