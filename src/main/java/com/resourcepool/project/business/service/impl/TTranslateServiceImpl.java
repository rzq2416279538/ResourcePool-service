package com.resourcepool.project.business.service.impl;

import java.util.Date;
import java.util.List;

import com.resourcepool.common.exception.ServiceException;
import com.resourcepool.common.utils.DateUtils;
import com.resourcepool.common.utils.StringUtils;
import com.resourcepool.framework.web.domain.AjaxResult;
import com.resourcepool.project.business.domain.TCustomer;
import com.resourcepool.project.business.domain.TTranslateBook;
import com.resourcepool.project.business.service.ITTranslateBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.resourcepool.project.business.mapper.TTranslateMapper;
import com.resourcepool.project.business.domain.TTranslate;
import com.resourcepool.project.business.service.ITTranslateService;

import static com.resourcepool.common.utils.SecurityUtils.getUsername;

/**
 * 企业资源Service业务层处理
 * 
 * @author 任遵强
 * @date 2023-06-07
 */
@Slf4j
@Service
public class TTranslateServiceImpl implements ITTranslateService 
{
    @Autowired
    private TTranslateMapper tTranslateMapper;


    @Autowired
    private ITTranslateBookService tTranslateBookService;

    /**
     * 查询企业资源
     * 
     * @param translateId 企业资源主键
     * @return 企业资源
     */
    @Override
    public TTranslate selectTTranslateByTranslateId(Long translateId)
    {
        return tTranslateMapper.selectTTranslateByTranslateId(translateId);
    }

    /**
     * 查询企业资源列表
     * 
     * @param tTranslate 企业资源
     * @return 企业资源
     */
    @Override
    public List<TTranslate> selectTTranslateList(TTranslate tTranslate)
    {
        return tTranslateMapper.selectTTranslateList(tTranslate);
    }

    /**
     * 新增企业资源
     * 
     * @param tTranslate 企业资源
     * @return 结果
     */
    @Override
    public int insertTTranslate(TTranslate tTranslate)
    {
        tTranslate.setCreateTime(DateUtils.getNowDate());
        return tTranslateMapper.insertTTranslate(tTranslate);
    }

    /**
     * 修改企业资源
     * 
     * @param tTranslate 企业资源
     * @return 结果
     */
    @Override
    public int updateTTranslate(TTranslate tTranslate)
    {
        return tTranslateMapper.updateTTranslate(tTranslate);
    }

    /**
     * 批量删除企业资源
     * 
     * @param translateIds 需要删除的企业资源主键
     * @return 结果
     */
    @Override
    public int deleteTTranslateByTranslateIds(Long[] translateIds)
    {
        return tTranslateMapper.deleteTTranslateByTranslateIds(translateIds);
    }

    /**
     * 删除企业资源信息
     * 
     * @param translateId 企业资源主键
     * @return 结果
     */
    @Override
    public int deleteTTranslateByTranslateId(Long translateId)
    {
        return tTranslateMapper.deleteTTranslateByTranslateId(translateId);
    }


    @Override
    public String imports(List<TTranslate> list)
    {
        if (StringUtils.isNull(list) || list.size() == 0)
        {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (TTranslate translate : list)
        {
            try
            {
                // 验证是否存在这个用户
                TTranslate u = tTranslateMapper.selectByName(translate.getTranslateName(),translate.getPhone());
                if (StringUtils.isNull(u))
                {
                    TTranslateBook param = new TTranslateBook();
                    param.setPhone(translate.getPhone());
                    param.setIsRecovery(0);
                    List<TTranslateBook> books = tTranslateBookService.selectTTranslateBookList(param);
                    if(books.size() > 0){
                        failureNum++;
                        failureMsg.append("<br/>" + failureNum + "、企业 " + translate.getTranslateName() + " 已被领取");
                    }else{
                        translate.setCreateBy(getUsername());
                        translate.setCreateTime(new Date());
                        tTranslateMapper.insertTTranslate(translate);
                        successNum++;
                        successMsg.append("<br/>" + successNum + "、企业 " + translate.getTranslateName() + " 导入成功");
                    }

                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、企业 " + translate.getTranslateName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、企业 " +  translate.getTranslateName() + " 导入失败：";
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
    public TTranslate selectByName(String name, String phone) {
        return tTranslateMapper.selectByName(name,phone);
    }
}
