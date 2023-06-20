package com.resourcepool.project.business.service.impl;

import java.util.Date;
import java.util.List;

import com.resourcepool.common.exception.ServiceException;
import com.resourcepool.common.utils.DateUtils;
import com.resourcepool.common.utils.SecurityUtils;
import com.resourcepool.common.utils.StringUtils;
import com.resourcepool.common.utils.bean.BeanValidators;
import com.resourcepool.framework.web.domain.AjaxResult;
import com.resourcepool.project.business.domain.TCustomerBook;
import com.resourcepool.project.business.service.ITCustomerBookService;
import com.resourcepool.project.system.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.resourcepool.project.business.mapper.TCustomerMapper;
import com.resourcepool.project.business.domain.TCustomer;
import com.resourcepool.project.business.service.ITCustomerService;

import static com.resourcepool.common.utils.SecurityUtils.getUsername;

/**
 * 客户资源Service业务层处理
 * 
 * @author 任遵强
 * @date 2023-06-07
 */
@Service
@Slf4j
public class TCustomerServiceImpl implements ITCustomerService 
{
    @Autowired
    private TCustomerMapper tCustomerMapper;

    @Autowired
    private ITCustomerBookService tCustomerBookService;


    /**
     * 查询客户资源
     * 
     * @param customerId 客户资源主键
     * @return 客户资源
     */
    @Override
    public TCustomer selectTCustomerByCustomerId(Long customerId)
    {
        return tCustomerMapper.selectTCustomerByCustomerId(customerId);
    }

    /**
     * 查询客户资源列表
     * 
     * @param tCustomer 客户资源
     * @return 客户资源
     */
    @Override
    public List<TCustomer> selectTCustomerList(TCustomer tCustomer)
    {
        return tCustomerMapper.selectTCustomerList(tCustomer);
    }

    /**
     * 新增客户资源
     * 
     * @param tCustomer 客户资源
     * @return 结果
     */
    @Override
    public int insertTCustomer(TCustomer tCustomer)
    {
        tCustomer.setCreateTime(DateUtils.getNowDate());
        return tCustomerMapper.insertTCustomer(tCustomer);
    }

    /**
     * 修改客户资源
     * 
     * @param tCustomer 客户资源
     * @return 结果
     */
    @Override
    public int updateTCustomer(TCustomer tCustomer)
    {
        return tCustomerMapper.updateTCustomer(tCustomer);
    }

    /**
     * 批量删除客户资源
     * 
     * @param customerIds 需要删除的客户资源主键
     * @return 结果
     */
    @Override
    public int deleteTCustomerByCustomerIds(Long[] customerIds)
    {
        return tCustomerMapper.deleteTCustomerByCustomerIds(customerIds);
    }

    /**
     * 删除客户资源信息
     * 
     * @param customerId 客户资源主键
     * @return 结果
     */
    @Override
    public int deleteTCustomerByCustomerId(Long customerId)
    {
        return tCustomerMapper.deleteTCustomerByCustomerId(customerId);
    }

    @Override
    public String importCustomer(List<TCustomer> list)
    {
        if (StringUtils.isNull(list) || list.size() == 0)
        {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (TCustomer customer : list)
        {
            try
            {
                // 验证是否存在这个用户
                TCustomer u = tCustomerMapper.selectByCusterName(customer.getCustomerName(),customer.getCustomerPhone());
                if (StringUtils.isNull(u))
                {

                    TCustomerBook param = new TCustomerBook();
                    param.setCustomerPhone(customer.getCustomerPhone());
                    param.setIsRecovery(0);

                    List<TCustomerBook> books = tCustomerBookService.selectTCustomerBookList(param);
                    if (books.size() > 0) {
                        failureNum++;
                        failureMsg.append("<br/>" + failureNum + "、客户 " + customer.getCustomerName() + " 已被领取");
                    }else{
                        customer.setCreateBy(getUsername());
                        customer.setCreateTime(new Date());
                        tCustomerMapper.insertTCustomer(customer);
                        successNum++;
                        successMsg.append("<br/>" + successNum + "、客户 " + customer.getCustomerName() + " 导入成功");
                    }
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、客户 " + customer.getCustomerName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、客户 " +  customer.getCustomerName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public TCustomer selectByCusterName(String customerName, String customerPhone) {
        return tCustomerMapper.selectByCusterName(customerName,customerPhone);
    }
}
