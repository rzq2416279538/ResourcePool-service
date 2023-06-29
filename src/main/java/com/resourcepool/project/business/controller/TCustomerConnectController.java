package com.resourcepool.project.business.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.resourcepool.project.business.domain.TCustomerBook;
import com.resourcepool.project.business.service.ITCustomerBookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.resourcepool.framework.aspectj.lang.annotation.Log;
import com.resourcepool.framework.aspectj.lang.enums.BusinessType;
import com.resourcepool.project.business.domain.TCustomerConnect;
import com.resourcepool.project.business.service.ITCustomerConnectService;
import com.resourcepool.framework.web.controller.BaseController;
import com.resourcepool.framework.web.domain.AjaxResult;
import com.resourcepool.common.utils.poi.ExcelUtil;
import com.resourcepool.framework.web.page.TableDataInfo;

/**
 * 客户沟通记录Controller
 * 
 * @author 任遵强
 * @date 2023-06-07
 */
@RestController
@RequestMapping("/business/customerConnect")
public class TCustomerConnectController extends BaseController
{
    @Autowired
    private ITCustomerConnectService tCustomerConnectService;

    @Autowired
    private ITCustomerBookService tCustomerBookService;

    /**
     * 查询客户沟通记录列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TCustomerConnect tCustomerConnect)
    {
        startPage();
        List<TCustomerConnect> list = tCustomerConnectService.selectTCustomerConnectList(tCustomerConnect);
        return getDataTable(list);
    }

    /**
     * 导出客户沟通记录列表
     */
    @Log(title = "客户沟通记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TCustomerConnect tCustomerConnect)
    {
        List<TCustomerConnect> list = tCustomerConnectService.selectTCustomerConnectList(tCustomerConnect);
        ExcelUtil<TCustomerConnect> util = new ExcelUtil<TCustomerConnect>(TCustomerConnect.class);
        util.exportExcel(response, list, "客户沟通记录数据");
    }

    /**
     * 获取客户沟通记录详细信息
     */
    @GetMapping(value = "/{customerConnectId}")
    public AjaxResult getInfo(@PathVariable("customerConnectId") Long customerConnectId)
    {
        return success(tCustomerConnectService.selectTCustomerConnectByCustomerConnectId(customerConnectId));
    }

    /**
     * 新增客户沟通记录
     */
    @Log(title = "客户沟通记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TCustomerConnect tCustomerConnect)
    {
        if(tCustomerConnect.getEndTime() != null){
            TCustomerBook book = new TCustomerBook();
            book.setBookId(tCustomerConnect.getBookId());
            book.setEndTime(tCustomerConnect.getEndTime());
            tCustomerBookService.updateTCustomerBook(book);
        }
        tCustomerConnect.setUserId(getUserId());
        tCustomerConnect.setUserName(getLoginUser().getUser().getNickName());
        tCustomerConnect.setCreateTime(new Date());
        return toAjax(tCustomerConnectService.insertTCustomerConnect(tCustomerConnect));
    }

    /**
     * 修改客户沟通记录
     */
    @Log(title = "客户沟通记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TCustomerConnect tCustomerConnect)
    {
        return toAjax(tCustomerConnectService.updateTCustomerConnect(tCustomerConnect));
    }

    /**
     * 删除客户沟通记录
     */
    @Log(title = "客户沟通记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{customerConnectIds}")
    public AjaxResult remove(@PathVariable Long[] customerConnectIds)
    {
        return toAjax(tCustomerConnectService.deleteTCustomerConnectByCustomerConnectIds(customerConnectIds));
    }
}
