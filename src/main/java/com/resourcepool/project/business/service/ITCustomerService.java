package com.resourcepool.project.business.service;

import java.util.List;

import com.resourcepool.project.business.domain.TCustomer;
import com.resourcepool.project.system.domain.SysUser;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * 客户资源Service接口
 *
 * @author 任遵强
 * @date 2023-06-07
 */
public interface ITCustomerService {
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
     * 批量删除客户资源
     *
     * @param customerIds 需要删除的客户资源主键集合
     * @return 结果
     */
    public int deleteTCustomerByCustomerIds(Long[] customerIds);

    /**
     * 删除客户资源信息
     *
     * @param customerId 客户资源主键
     * @return 结果
     */
    public int deleteTCustomerByCustomerId(Long customerId);


    public String importCustomer(List<TCustomer> list);

    public TCustomer selectByCusterName(String customerName, String customerPhone);
}
