package com.resourcepool.project.business.mapper;

import java.util.List;

import com.resourcepool.project.business.domain.TCustomer;
import com.resourcepool.project.business.domain.TTranslate;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * 企业资源Mapper接口
 * 
 * @author 任遵强
 * @date 2023-06-07
 */
public interface TTranslateMapper 
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
     * 删除企业资源
     * 
     * @param translateId 企业资源主键
     * @return 结果
     */
    public int deleteTTranslateByTranslateId(Long translateId);

    /**
     * 批量删除企业资源
     * 
     * @param translateIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTTranslateByTranslateIds(Long[] translateIds);


    public TTranslate selectByName(@Param("name") String name, @Param("phone") String phone);
}
