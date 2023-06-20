package com.resourcepool.project.business.service.impl;

import java.util.List;
import com.resourcepool.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.resourcepool.project.business.mapper.TCustomerConnectMapper;
import com.resourcepool.project.business.domain.TCustomerConnect;
import com.resourcepool.project.business.service.ITCustomerConnectService;

/**
 * 客户沟通记录Service业务层处理
 * 
 * @author 任遵强
 * @date 2023-06-07
 */
@Service
public class TCustomerConnectServiceImpl implements ITCustomerConnectService 
{
    @Autowired
    private TCustomerConnectMapper tCustomerConnectMapper;

    /**
     * 查询客户沟通记录
     * 
     * @param customerConnectId 客户沟通记录主键
     * @return 客户沟通记录
     */
    @Override
    public TCustomerConnect selectTCustomerConnectByCustomerConnectId(Long customerConnectId)
    {
        return tCustomerConnectMapper.selectTCustomerConnectByCustomerConnectId(customerConnectId);
    }

    /**
     * 查询客户沟通记录列表
     * 
     * @param tCustomerConnect 客户沟通记录
     * @return 客户沟通记录
     */
    @Override
    public List<TCustomerConnect> selectTCustomerConnectList(TCustomerConnect tCustomerConnect)
    {
        return tCustomerConnectMapper.selectTCustomerConnectList(tCustomerConnect);
    }

    /**
     * 新增客户沟通记录
     * 
     * @param tCustomerConnect 客户沟通记录
     * @return 结果
     */
    @Override
    public int insertTCustomerConnect(TCustomerConnect tCustomerConnect)
    {
        tCustomerConnect.setCreateTime(DateUtils.getNowDate());
        return tCustomerConnectMapper.insertTCustomerConnect(tCustomerConnect);
    }

    /**
     * 修改客户沟通记录
     * 
     * @param tCustomerConnect 客户沟通记录
     * @return 结果
     */
    @Override
    public int updateTCustomerConnect(TCustomerConnect tCustomerConnect)
    {
        return tCustomerConnectMapper.updateTCustomerConnect(tCustomerConnect);
    }

    /**
     * 批量删除客户沟通记录
     * 
     * @param customerConnectIds 需要删除的客户沟通记录主键
     * @return 结果
     */
    @Override
    public int deleteTCustomerConnectByCustomerConnectIds(Long[] customerConnectIds)
    {
        return tCustomerConnectMapper.deleteTCustomerConnectByCustomerConnectIds(customerConnectIds);
    }

    /**
     * 删除客户沟通记录信息
     * 
     * @param customerConnectId 客户沟通记录主键
     * @return 结果
     */
    @Override
    public int deleteTCustomerConnectByCustomerConnectId(Long customerConnectId)
    {
        return tCustomerConnectMapper.deleteTCustomerConnectByCustomerConnectId(customerConnectId);
    }
}
