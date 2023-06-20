package com.resourcepool.project.business.service;

import java.util.List;
import com.resourcepool.project.business.domain.TTranslateConnect;

/**
 * 企业沟通记录Service接口
 * 
 * @author 任遵强
 * @date 2023-06-08
 */
public interface ITTranslateConnectService 
{
    /**
     * 查询企业沟通记录
     * 
     * @param translateConnectId 企业沟通记录主键
     * @return 企业沟通记录
     */
    public TTranslateConnect selectTTranslateConnectByTranslateConnectId(Long translateConnectId);

    /**
     * 查询企业沟通记录列表
     * 
     * @param tTranslateConnect 企业沟通记录
     * @return 企业沟通记录集合
     */
    public List<TTranslateConnect> selectTTranslateConnectList(TTranslateConnect tTranslateConnect);

    /**
     * 新增企业沟通记录
     * 
     * @param tTranslateConnect 企业沟通记录
     * @return 结果
     */
    public int insertTTranslateConnect(TTranslateConnect tTranslateConnect);

    /**
     * 修改企业沟通记录
     * 
     * @param tTranslateConnect 企业沟通记录
     * @return 结果
     */
    public int updateTTranslateConnect(TTranslateConnect tTranslateConnect);

    /**
     * 批量删除企业沟通记录
     * 
     * @param translateConnectIds 需要删除的企业沟通记录主键集合
     * @return 结果
     */
    public int deleteTTranslateConnectByTranslateConnectIds(Long[] translateConnectIds);

    /**
     * 删除企业沟通记录信息
     * 
     * @param translateConnectId 企业沟通记录主键
     * @return 结果
     */
    public int deleteTTranslateConnectByTranslateConnectId(Long translateConnectId);
}
