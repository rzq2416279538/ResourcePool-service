package com.resourcepool.project.business.service;

import java.util.List;
import com.resourcepool.project.business.domain.TCustomerConnect;

/**
 * 客户沟通记录Service接口
 * 
 * @author 任遵强
 * @date 2023-06-07
 */
public interface ITCustomerConnectService 
{
    /**
     * 查询客户沟通记录
     * 
     * @param customerConnectId 客户沟通记录主键
     * @return 客户沟通记录
     */
    public TCustomerConnect selectTCustomerConnectByCustomerConnectId(Long customerConnectId);

    /**
     * 查询客户沟通记录列表
     * 
     * @param tCustomerConnect 客户沟通记录
     * @return 客户沟通记录集合
     */
    public List<TCustomerConnect> selectTCustomerConnectList(TCustomerConnect tCustomerConnect);

    /**
     * 新增客户沟通记录
     * 
     * @param tCustomerConnect 客户沟通记录
     * @return 结果
     */
    public int insertTCustomerConnect(TCustomerConnect tCustomerConnect);

    /**
     * 修改客户沟通记录
     * 
     * @param tCustomerConnect 客户沟通记录
     * @return 结果
     */
    public int updateTCustomerConnect(TCustomerConnect tCustomerConnect);

    /**
     * 批量删除客户沟通记录
     * 
     * @param customerConnectIds 需要删除的客户沟通记录主键集合
     * @return 结果
     */
    public int deleteTCustomerConnectByCustomerConnectIds(Long[] customerConnectIds);

    /**
     * 删除客户沟通记录信息
     * 
     * @param customerConnectId 客户沟通记录主键
     * @return 结果
     */
    public int deleteTCustomerConnectByCustomerConnectId(Long customerConnectId);
}
