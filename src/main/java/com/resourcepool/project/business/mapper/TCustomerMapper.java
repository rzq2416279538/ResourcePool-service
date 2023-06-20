package com.resourcepool.project.business.mapper;

import java.util.List;
import com.resourcepool.project.business.domain.TCustomer;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * 客户资源Mapper接口
 * 
 * @author 任遵强
 * @date 2023-06-07
 */
public interface TCustomerMapper 
{
    /**
     * 查询客户资源
     * 
     * @param customerId 客户资源主键
     * @return 客户资源
     */
    public TCustomer selectTCustomerByCustomerId(Long customerId);

    /**
     * 查询客户资源列表
     * 
     * @param tCustomer 客户资源
     * @return 客户资源集合
     */
    public List<TCustomer> selectTCustomerList(TCustomer tCustomer);

    /**
     * 新增客户资源
     * 
     * @param tCustomer 客户资源
     * @return 结果
     */
    public int insertTCustomer(TCustomer tCustomer);

    /**
     * 修改客户资源
     * 
     * @param tCustomer 客户资源
     * @return 结果
     */
    public int updateTCustomer(TCustomer tCustomer);

    /**
     * 删除客户资源
     * 
     * @param customerId 客户资源主键
     * @return 结果
     */
    public int deleteTCustomerByCustomerId(Long customerId);

    /**
     * 批量删除客户资源
     * 
     * @param customerIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTCustomerByCustomerIds(Long[] customerIds);

    /**
     * 验证是否存在
     */
    public TCustomer selectByCusterName(@Param("customerName") String customerName,@Param("customerPhone") String customerPhone);
}
