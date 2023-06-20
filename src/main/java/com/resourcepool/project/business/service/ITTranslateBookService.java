package com.resourcepool.project.business.service;

import java.util.List;
import com.resourcepool.project.business.domain.TTranslateBook;

/**
 * 企业通讯录Service接口
 * 
 * @author 任遵强
 * @date 2023-06-07
 */
public interface ITTranslateBookService 
{
    /**
     * 查询企业通讯录
     * 
     * @param translateBookId 企业通讯录主键
     * @return 企业通讯录
     */
    public TTranslateBook selectTTranslateBookByTranslateBookId(Long translateBookId);

    /**
     * 查询企业通讯录列表
     * 
     * @param tTranslateBook 企业通讯录
     * @return 企业通讯录集合
     */
    public List<TTranslateBook> selectTTranslateBookList(TTranslateBook tTranslateBook);
    public List<TTranslateBook> selectTTranslateBookListAspect(TTranslateBook tTranslateBook);

    /**
     * 新增企业通讯录
     * 
     * @param tTranslateBook 企业通讯录
     * @return 结果
     */
    public int insertTTranslateBook(TTranslateBook tTranslateBook);

    /**
     * 修改企业通讯录
     * 
     * @param tTranslateBook 企业通讯录
     * @return 结果
     */
    public int updateTTranslateBook(TTranslateBook tTranslateBook);

    /**
     * 批量删除企业通讯录
     * 
     * @param translateBookIds 需要删除的企业通讯录主键集合
     * @return 结果
     */
    public int deleteTTranslateBookByTranslateBookIds(Long[] translateBookIds);

    /**
     * 删除企业通讯录信息
     * 
     * @param translateBookId 企业通讯录主键
     * @return 结果
     */
    public int deleteTTranslateBookByTranslateBookId(Long translateBookId);
}
