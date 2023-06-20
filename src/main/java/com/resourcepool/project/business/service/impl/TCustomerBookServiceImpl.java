package com.resourcepool.project.business.service.impl;

import java.util.List;
import com.resourcepool.common.utils.DateUtils;
import com.resourcepool.framework.aspectj.lang.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.resourcepool.project.business.mapper.TCustomerBookMapper;
import com.resourcepool.project.business.domain.TCustomerBook;
import com.resourcepool.project.business.service.ITCustomerBookService;

/**
 * 客户通讯录Service业务层处理
 * 
 * @author 任遵强
 * @date 2023-06-07
 */
@Service
public class TCustomerBookServiceImpl implements ITCustomerBookService 
{
    @Autowired
    private TCustomerBookMapper tCustomerBookMapper;

    /**
     * 查询客户通讯录
     * 
     * @param bookId 客户通讯录主键
     * @return 客户通讯录
     */
    @Override
    public TCustomerBook selectTCustomerBookByBookId(Long bookId)
    {
        return tCustomerBookMapper.selectTCustomerBookByBookId(bookId);
    }

    /**
     * 查询客户通讯录列表
     * 
     * @param tCustomerBook 客户通讯录
     * @return 客户通讯录
     */
    @Override
    public List<TCustomerBook> selectTCustomerBookList(TCustomerBook tCustomerBook)
    {
        return tCustomerBookMapper.selectTCustomerBookList(tCustomerBook);
    }

    /**
     * 查询客户通讯录列表
     * 查询每天
     *
     * @param tCustomerBook 客户通讯录
     * @return 客户通讯录集合
     */
    @Override
    public List<TCustomerBook> selectTCustomerBookListToDay(TCustomerBook tCustomerBook) {
        return tCustomerBookMapper.selectTCustomerBookListToDay(tCustomerBook);
    }


    /**
     * 查询客户通讯录列表
     * 权限处理
     *
     * @param tCustomerBook 客户通讯录
     * @return 客户通讯录集合
     */
    @Override
    @DataScope(deptAlias = "d",userAlias = "t1")
    public List<TCustomerBook> selectTCustomerBookListAspect(TCustomerBook tCustomerBook)
    {
        return tCustomerBookMapper.selectTCustomerBookListAspect(tCustomerBook);
    }

    /**
     * 新增客户通讯录
     * 
     * @param tCustomerBook 客户通讯录
     * @return 结果
     */
    @Override
    public int insertTCustomerBook(TCustomerBook tCustomerBook)
    {
        tCustomerBook.setCreateTime(DateUtils.getNowDate());
        return tCustomerBookMapper.insertTCustomerBook(tCustomerBook);
    }

    /**
     * 修改客户通讯录
     * 
     * @param tCustomerBook 客户通讯录
     * @return 结果
     */
    @Override
    public int updateTCustomerBook(TCustomerBook tCustomerBook)
    {
        return tCustomerBookMapper.updateTCustomerBook(tCustomerBook);
    }

    /**
     * 批量删除客户通讯录
     * 
     * @param bookIds 需要删除的客户通讯录主键
     * @return 结果
     */
    @Override
    public int deleteTCustomerBookByBookIds(Long[] bookIds)
    {
        return tCustomerBookMapper.deleteTCustomerBookByBookIds(bookIds);
    }

    /**
     * 删除客户通讯录信息
     * 
     * @param bookId 客户通讯录主键
     * @return 结果
     */
    @Override
    public int deleteTCustomerBookByBookId(Long bookId)
    {
        return tCustomerBookMapper.deleteTCustomerBookByBookId(bookId);
    }
}
