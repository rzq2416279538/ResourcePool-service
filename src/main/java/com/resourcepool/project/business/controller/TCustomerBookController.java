package com.resourcepool.project.business.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.resourcepool.project.business.domain.TCustomer;
import com.resourcepool.project.business.service.ITCustomerService;
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
import com.resourcepool.project.business.domain.TCustomerBook;
import com.resourcepool.project.business.service.ITCustomerBookService;
import com.resourcepool.framework.web.controller.BaseController;
import com.resourcepool.framework.web.domain.AjaxResult;
import com.resourcepool.common.utils.poi.ExcelUtil;
import com.resourcepool.framework.web.page.TableDataInfo;

/**
 * 客户通讯录Controller
 * 
 * @author 任遵强
 * @date 2023-06-07
 */
@RestController
@RequestMapping("/business/book")
public class TCustomerBookController extends BaseController
{
    @Autowired
    private ITCustomerBookService tCustomerBookService;

    @Autowired
    private ITCustomerService tCustomerService;

    /**
     * 查询客户通讯录列表
     */
    @PreAuthorize("@ss.hasPermi('business:book:list')")
    @GetMapping("/list")
    public TableDataInfo list(TCustomerBook tCustomerBook)
    {
//        tCustomerBook.setUserId(getUserId());
        tCustomerBook.setIsRecovery(0);

        startPage();
        List<TCustomerBook> list = tCustomerBookService.selectTCustomerBookListAspect(tCustomerBook);
        return getDataTable(list);
    }

    /**
     * 导出客户通讯录列表
     */
    @PreAuthorize("@ss.hasPermi('business:book:export')")
    @Log(title = "客户通讯录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TCustomerBook tCustomerBook)
    {
        List<TCustomerBook> list = tCustomerBookService.selectTCustomerBookList(tCustomerBook);
        ExcelUtil<TCustomerBook> util = new ExcelUtil<TCustomerBook>(TCustomerBook.class);
        util.exportExcel(response, list, "客户通讯录数据");
    }

    /**
     * 获取客户通讯录详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:book:query')")
    @GetMapping(value = "/{bookId}")
    public AjaxResult getInfo(@PathVariable("bookId") Long bookId)
    {
        return success(tCustomerBookService.selectTCustomerBookByBookId(bookId));
    }

    /**
     * 新增客户通讯录
     */
    @PreAuthorize("@ss.hasPermi('business:book:add')")
    @Log(title = "客户通讯录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TCustomerBook tCustomerBook)
    {
        return toAjax(tCustomerBookService.insertTCustomerBook(tCustomerBook));
    }

    /**
     * 修改客户通讯录
     */
    @PreAuthorize("@ss.hasPermi('business:book:edit')")
    @Log(title = "客户通讯录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TCustomerBook tCustomerBook)
    {
        return toAjax(tCustomerBookService.updateTCustomerBook(tCustomerBook));
    }

    /**
     * 删除客户通讯录
     */
    @PreAuthorize("@ss.hasPermi('business:book:remove')")
    @Log(title = "客户通讯录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bookIds}")
    public AjaxResult remove(@PathVariable Long[] bookIds)
    {
        return toAjax(tCustomerBookService.deleteTCustomerBookByBookIds(bookIds));
    }


    /**
     * 回收
     */
    @Log(title = "客户通讯录", businessType = BusinessType.DELETE)
    @PostMapping("/recoverys")
    public AjaxResult recoverys(@RequestBody TCustomerBook tCustomerBook)
    {
        TCustomer customer = new TCustomer();
        customer.setCustomerName(tCustomerBook.getCustomerName());
        customer.setCustomerPhone(tCustomerBook.getCustomerPhone());
        customer.setCertificateType(tCustomerBook.getCertificateType());
        customer.setCertificateSpeciality(tCustomerBook.getCertificateSpeciality());
        customer.setRemark(tCustomerBook.getRemark());
        customer.setAddr(tCustomerBook.getAddr());
        customer.setSources(tCustomerBook.getSources());
        customer.setNotes(tCustomerBook.getNotes());
        customer.setCreateBy(getUsername());
        tCustomerService.insertTCustomer(customer);

        tCustomerBook.setIsRecovery(1);
        tCustomerBookService.updateTCustomerBook(tCustomerBook);
        return AjaxResult.success();
    }
}
