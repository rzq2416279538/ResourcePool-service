package com.resourcepool.project.business.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.resourcepool.project.business.domain.TTranslateConnect;
import com.resourcepool.project.business.service.ITTranslateConnectService;
import com.resourcepool.framework.web.controller.BaseController;
import com.resourcepool.framework.web.domain.AjaxResult;
import com.resourcepool.common.utils.poi.ExcelUtil;
import com.resourcepool.framework.web.page.TableDataInfo;

/**
 * 企业沟通记录Controller
 * 
 * @author 任遵强
 * @date 2023-06-08
 */
@RestController
@RequestMapping("/business/translateConnect")
public class TTranslateConnectController extends BaseController
{
    @Autowired
    private ITTranslateConnectService tTranslateConnectService;

    /**
     * 查询企业沟通记录列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TTranslateConnect tTranslateConnect)
    {
        startPage();
        List<TTranslateConnect> list = tTranslateConnectService.selectTTranslateConnectList(tTranslateConnect);
        return getDataTable(list);
    }

    /**
     * 导出企业沟通记录列表
     */
    @Log(title = "企业沟通记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TTranslateConnect tTranslateConnect)
    {
        List<TTranslateConnect> list = tTranslateConnectService.selectTTranslateConnectList(tTranslateConnect);
        ExcelUtil<TTranslateConnect> util = new ExcelUtil<TTranslateConnect>(TTranslateConnect.class);
        util.exportExcel(response, list, "企业沟通记录数据");
    }

    /**
     * 获取企业沟通记录详细信息
     */
    @GetMapping(value = "/{translateConnectId}")
    public AjaxResult getInfo(@PathVariable("translateConnectId") Long translateConnectId)
    {
        return success(tTranslateConnectService.selectTTranslateConnectByTranslateConnectId(translateConnectId));
    }

    /**
     * 新增企业沟通记录
     */
    @Log(title = "企业沟通记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TTranslateConnect tTranslateConnect)
    {
        tTranslateConnect.setUserId(getUserId());
        tTranslateConnect.setUserName(getLoginUser().getUser().getNickName());
        tTranslateConnect.setCreateTime(new Date());
        return toAjax(tTranslateConnectService.insertTTranslateConnect(tTranslateConnect));
    }

    /**
     * 修改企业沟通记录
     */
    @Log(title = "企业沟通记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TTranslateConnect tTranslateConnect)
    {
        return toAjax(tTranslateConnectService.updateTTranslateConnect(tTranslateConnect));
    }

    /**
     * 删除企业沟通记录
     */
    @Log(title = "企业沟通记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{translateConnectIds}")
    public AjaxResult remove(@PathVariable Long[] translateConnectIds)
    {
        return toAjax(tTranslateConnectService.deleteTTranslateConnectByTranslateConnectIds(translateConnectIds));
    }
}
