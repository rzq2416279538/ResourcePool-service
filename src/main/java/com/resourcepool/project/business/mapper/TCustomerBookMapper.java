package com.resourcepool.project.business.mapper;

import java.util.List;
import com.resourcepool.project.business.domain.TCustomerBook;

/**
 * 客户通讯录Mapper接口
 * 
 * @author 任遵强
 * @date 2023-06-07
 */
public interface TCustomerBookMapper 
{
    /**
     * 查询客户通讯录
     * 
     * @param bookId 客户通讯录主键
     * @return 客户通讯录
     */
    public TCustomerBook selectTCustomerBookByBookId(Long bookId);

    /**
     * 查询客户通讯录列表
     * 
     * @param tCustomerBook 客户通讯录
     * @return 客户通讯录集合
     */
    public List<TCustomerBook> selectTCustomerBookList(TCustomerBook tCustomerBook);


    /**
     * 查询客户通讯录列表
     * 查询每天
     *
     * @param tCustomerBook 客户通讯录
     * @return 客户通讯录集合
     */
    public List<TCustomerBook> selectTCustomerBookListToDay(TCustomerBook tCustomerBook);


    /**
     * 查询客户通讯录列表
     * 权限处理
     *
     * @param tCustomerBook 客户通讯录
     * @return 客户通讯录集合
     */
    public List<TCustomerBook> selectTCustomerBookListAspect(TCustomerBook tCustomerBook);

    /**
     * 新增客户通讯录
     * 
     * @param tCustomerBook 客户通讯录
     * @return 结果
     */
    public int insertTCustomerBook(TCustomerBook tCustomerBook);

    /**
     * 修改客户通讯录
     * 
     * @param tCustomerBook 客户通讯录
     * @return 结果
     */
    public int updateTCustomerBook(TCustomerBook tCustomerBook);

    /**
     * 删除客户通讯录
     * 
     * @param bookId 客户通讯录主键
     * @return 结果
     */
    public int deleteTCustomerBookByBookId(Long bookId);

    /**
     * 批量删除客户通讯录
     * 
     * @param bookIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTCustomerBookByBookIds(Long[] bookIds);
}
