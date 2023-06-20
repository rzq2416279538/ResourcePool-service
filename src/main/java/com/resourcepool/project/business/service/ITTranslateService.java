package com.resourcepool.project.business.service;

import java.util.List;

import com.resourcepool.project.business.domain.TCustomer;
import com.resourcepool.project.business.domain.TTranslate;

/**
 * 企业资源Service接口
 * 
 * @author 任遵强
 * @date 2023-06-07
 */
public interface ITTranslateService 
{
    /**
     * 查询企业资源
     * 
     * @param translateId 企业资源主键
     * @return 企业资源
     */
    public TTranslate selectTTranslateByTranslateId(Long translateId);

    /**
     * 查询企业资源列表
     * 
     * @param tTranslate 企业资源
     * @return 企业资源集合
     */
    public List<TTranslate> selectTTranslateList(TTranslate tTranslate);

    /**
     * 新增企业资源
     * 
     * @param tTranslate 企业资源
     * @return 结果
     */
    public int insertTTranslate(TTranslate tTranslate);

    /**
     * 修改企业资源
     * 
     * @param tTranslate 企业资源
     * @return 结果
     */
    public int updateTTranslate(TTranslate tTranslate);

    /**
     * 批量删除企业资源
     * 
     * @param translateIds 需要删除的企业资源主键集合
     * @return 结果
     */
    public int deleteTTranslateByTranslateIds(Long[] translateIds);

    /**
     * 删除企业资源信息
     * 
     * @param translateId 企业资源主键
     * @return 结果
     */
    public int deleteTTranslateByTranslateId(Long translateId);

    public String imports(List<TTranslate> list);

    public TTranslate selectByName(String name, String phone);
}
