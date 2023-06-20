package com.resourcepool.project.business.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.resourcepool.project.business.domain.TTranslate;
import com.resourcepool.project.business.service.ITTranslateService;
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
import com.resourcepool.project.business.domain.TTranslateBook;
import com.resourcepool.project.business.service.ITTranslateBookService;
import com.resourcepool.framework.web.controller.BaseController;
import com.resourcepool.framework.web.domain.AjaxResult;
import com.resourcepool.common.utils.poi.ExcelUtil;
import com.resourcepool.framework.web.page.TableDataInfo;

/**
 * 企业通讯录Controller
 * 
 * @author 任遵强
 * @date 2023-06-07
 */
@RestController
@RequestMapping("/business/translateBook")
public class TTranslateBookController extends BaseController
{
    @Autowired
    private ITTranslateBookService tTranslateBookService;

    @Autowired
    private ITTranslateService tTranslateService;

    /**
     * 查询企业通讯录列表
     */
    @PreAuthorize("@ss.hasPermi('business:translateBook:list')")
    @GetMapping("/list")
    public TableDataInfo list(TTranslateBook tTranslateBook)
    {
//        tTranslateBook.setUserId(getUserId());
        tTranslateBook.setIsRecovery(0);

        startPage();
        List<TTranslateBook> list = tTranslateBookService.selectTTranslateBookListAspect(tTranslateBook);
        return getDataTable(list);
    }

    /**
     * 导出企业通讯录列表
     */
    @PreAuthorize("@ss.hasPermi('business:translateBook:export')")
    @Log(title = "企业通讯录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TTranslateBook tTranslateBook)
    {
        List<TTranslateBook> list = tTranslateBookService.selectTTranslateBookList(tTranslateBook);
        ExcelUtil<TTranslateBook> util = new ExcelUtil<TTranslateBook>(TTranslateBook.class);
        util.exportExcel(response, list, "企业通讯录数据");
    }

    /**
     * 获取企业通讯录详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:translateBook:query')")
    @GetMapping(value = "/{translateBookId}")
    public AjaxResult getInfo(@PathVariable("translateBookId") Long translateBookId)
    {
        return success(tTranslateBookService.selectTTranslateBookByTranslateBookId(translateBookId));
    }

    /**
     * 新增企业通讯录
     */
    @PreAuthorize("@ss.hasPermi('business:translateBook:add')")
    @Log(title = "企业通讯录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TTranslateBook tTranslateBook)
    {
        return toAjax(tTranslateBookService.insertTTranslateBook(tTranslateBook));
    }

    /**
     * 修改企业通讯录
     */
    @PreAuthorize("@ss.hasPermi('business:translateBook:edit')")
    @Log(title = "企业通讯录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TTranslateBook tTranslateBook)
    {
        return toAjax(tTranslateBookService.updateTTranslateBook(tTranslateBook));
    }

    /**
     * 删除企业通讯录
     */
    @PreAuthorize("@ss.hasPermi('business:translateBook:remove')")
    @Log(title = "企业通讯录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{translateBookIds}")
    public AjaxResult remove(@PathVariable Long[] translateBookIds)
    {
        return toAjax(tTranslateBookService.deleteTTranslateBookByTranslateBookIds(translateBookIds));
    }


    /**
     * 回收
     */
    @Log(title = "企业通讯录", businessType = BusinessType.EXPORT)
    @PostMapping("/recoverys")
    public AjaxResult recoverys(@RequestBody TTranslateBook tTranslateBook)
    {
        TTranslate translate = new TTranslate();
        translate.setTranslateName(tTranslateBook.getTranslateName());
        translate.setContacts(tTranslateBook.getContacts());
        translate.setPhone(tTranslateBook.getPhone());
        translate.setAddr(tTranslateBook.getAddr());
        translate.setCustomerDemand(tTranslateBook.getCustomerDemand());
        translate.setSources(tTranslateBook.getSources());
        translate.setNotes(tTranslateBook.getNotes());
        translate.setCreateBy(getUsername());
        tTranslateService.insertTTranslate(translate);

        tTranslateBook.setIsRecovery(1);
        tTranslateBookService.updateTTranslateBook(tTranslateBook);

        return AjaxResult.success();
    }
}
