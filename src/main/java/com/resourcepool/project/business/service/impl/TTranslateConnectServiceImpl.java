package com.resourcepool.project.business.service.impl;

import java.util.List;
import com.resourcepool.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.resourcepool.project.business.mapper.TTranslateConnectMapper;
import com.resourcepool.project.business.domain.TTranslateConnect;
import com.resourcepool.project.business.service.ITTranslateConnectService;

/**
 * 企业沟通记录Service业务层处理
 * 
 * @author 任遵强
 * @date 2023-06-08
 */
@Service
public class TTranslateConnectServiceImpl implements ITTranslateConnectService 
{
    @Autowired
    private TTranslateConnectMapper tTranslateConnectMapper;

    /**
     * 查询企业沟通记录
     * 
     * @param translateConnectId 企业沟通记录主键
     * @return 企业沟通记录
     */
    @Override
    public TTranslateConnect selectTTranslateConnectByTranslateConnectId(Long translateConnectId)
    {
        return tTranslateConnectMapper.selectTTranslateConnectByTranslateConnectId(translateConnectId);
    }

    /**
     * 查询企业沟通记录列表
     * 
     * @param tTranslateConnect 企业沟通记录
     * @return 企业沟通记录
     */
    @Override
    public List<TTranslateConnect> selectTTranslateConnectList(TTranslateConnect tTranslateConnect)
    {
        return tTranslateConnectMapper.selectTTranslateConnectList(tTranslateConnect);
    }

    /**
     * 新增企业沟通记录
     * 
     * @param tTranslateConnect 企业沟通记录
     * @return 结果
     */
    @Override
    public int insertTTranslateConnect(TTranslateConnect tTranslateConnect)
    {
        tTranslateConnect.setCreateTime(DateUtils.getNowDate());
        return tTranslateConnectMapper.insertTTranslateConnect(tTranslateConnect);
    }

    /**
     * 修改企业沟通记录
     * 
     * @param tTranslateConnect 企业沟通记录
     * @return 结果
     */
    @Override
    public int updateTTranslateConnect(TTranslateConnect tTranslateConnect)
    {
        return tTranslateConnectMapper.updateTTranslateConnect(tTranslateConnect);
    }

    /**
     * 批量删除企业沟通记录
     * 
     * @param translateConnectIds 需要删除的企业沟通记录主键
     * @return 结果
     */
    @Override
    public int deleteTTranslateConnectByTranslateConnectIds(Long[] translateConnectIds)
    {
        return tTranslateConnectMapper.deleteTTranslateConnectByTranslateConnectIds(translateConnectIds);
    }

    /**
     * 删除企业沟通记录信息
     * 
     * @param translateConnectId 企业沟通记录主键
     * @return 结果
     */
    @Override
    public int deleteTTranslateConnectByTranslateConnectId(Long translateConnectId)
    {
        return tTranslateConnectMapper.deleteTTranslateConnectByTranslateConnectId(translateConnectId);
    }
}
