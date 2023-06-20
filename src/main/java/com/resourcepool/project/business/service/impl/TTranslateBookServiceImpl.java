package com.resourcepool.project.business.service.impl;

import java.util.List;
import com.resourcepool.common.utils.DateUtils;
import com.resourcepool.framework.aspectj.lang.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.resourcepool.project.business.mapper.TTranslateBookMapper;
import com.resourcepool.project.business.domain.TTranslateBook;
import com.resourcepool.project.business.service.ITTranslateBookService;

/**
 * 企业通讯录Service业务层处理
 * 
 * @author 任遵强
 * @date 2023-06-07
 */
@Service
public class TTranslateBookServiceImpl implements ITTranslateBookService 
{
    @Autowired
    private TTranslateBookMapper tTranslateBookMapper;

    /**
     * 查询企业通讯录
     * 
     * @param translateBookId 企业通讯录主键
     * @return 企业通讯录
     */
    @Override
    public TTranslateBook selectTTranslateBookByTranslateBookId(Long translateBookId)
    {
        return tTranslateBookMapper.selectTTranslateBookByTranslateBookId(translateBookId);
    }

    /**
     * 查询企业通讯录列表
     * 
     * @param tTranslateBook 企业通讯录
     * @return 企业通讯录
     */
    @Override
    public List<TTranslateBook> selectTTranslateBookList(TTranslateBook tTranslateBook)
    {
        return tTranslateBookMapper.selectTTranslateBookList(tTranslateBook);
    }

    @Override
    @DataScope(deptAlias = "d",userAlias = "t1")
    public List<TTranslateBook> selectTTranslateBookListAspect(TTranslateBook tTranslateBook)
    {
        return tTranslateBookMapper.selectTTranslateBookListAspect(tTranslateBook);
    }

    /**
     * 新增企业通讯录
     * 
     * @param tTranslateBook 企业通讯录
     * @return 结果
     */
    @Override
    public int insertTTranslateBook(TTranslateBook tTranslateBook)
    {
        tTranslateBook.setCreateTime(DateUtils.getNowDate());
        return tTranslateBookMapper.insertTTranslateBook(tTranslateBook);
    }

    /**
     * 修改企业通讯录
     * 
     * @param tTranslateBook 企业通讯录
     * @return 结果
     */
    @Override
    public int updateTTranslateBook(TTranslateBook tTranslateBook)
    {
        return tTranslateBookMapper.updateTTranslateBook(tTranslateBook);
    }

    /**
     * 批量删除企业通讯录
     * 
     * @param translateBookIds 需要删除的企业通讯录主键
     * @return 结果
     */
    @Override
    public int deleteTTranslateBookByTranslateBookIds(Long[] translateBookIds)
    {
        return tTranslateBookMapper.deleteTTranslateBookByTranslateBookIds(translateBookIds);
    }

    /**
     * 删除企业通讯录信息
     * 
     * @param translateBookId 企业通讯录主键
     * @return 结果
     */
    @Override
    public int deleteTTranslateBookByTranslateBookId(Long translateBookId)
    {
        return tTranslateBookMapper.deleteTTranslateBookByTranslateBookId(translateBookId);
    }
}
