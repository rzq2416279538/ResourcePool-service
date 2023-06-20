package com.resourcepool.project.business.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.resourcepool.common.utils.StringUtils;
import com.resourcepool.framework.security.LoginUser;
import com.resourcepool.project.business.domain.TCustomer;
import com.resourcepool.project.business.domain.TCustomerBook;
import com.resourcepool.project.business.domain.TTranslateBook;
import com.resourcepool.project.business.service.ITTranslateBookService;
import org.aspectj.weaver.loadtime.Aj;
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
import com.resourcepool.project.business.domain.TTranslate;
import com.resourcepool.project.business.service.ITTranslateService;
import com.resourcepool.framework.web.controller.BaseController;
import com.resourcepool.framework.web.domain.AjaxResult;
import com.resourcepool.common.utils.poi.ExcelUtil;
import com.resourcepool.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 企业资源Controller
 *
 * @author 任遵强
 * @date 2023-06-07
 */
@RestController
@RequestMapping("/business/translate")
public class TTranslateController extends BaseController
{
    @Autowired
    private ITTranslateService tTranslateService;

    @Autowired
    private ITTranslateBookService tTranslateBookService;

    /**
     * 查询企业资源列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TTranslate tTranslate)
    {

        startPage();
        List<TTranslate> list = tTranslateService.selectTTranslateList(tTranslate);
        return getDataTable(list);
    }

    /**
     * 导出企业资源列表
     */
    @Log(title = "企业资源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TTranslate tTranslate)
    {
        List<TTranslate> list = tTranslateService.selectTTranslateList(tTranslate);
        ExcelUtil<TTranslate> util = new ExcelUtil<TTranslate>(TTranslate.class);
        util.exportExcel(response, list, "企业资源数据");
    }

    /**
     * 获取企业资源详细信息
     */
    @GetMapping(value = "/{translateId}")
    public AjaxResult getInfo(@PathVariable("translateId") Long translateId)
    {
        return success(tTranslateService.selectTTranslateByTranslateId(translateId));
    }

    /**
     * 新增企业资源
     */
    @Log(title = "企业资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TTranslate tTranslate)
    {
        TTranslate u = tTranslateService.selectByName(tTranslate.getTranslateName(),tTranslate.getPhone());
        if (StringUtils.isNull(u)) {
            TTranslateBook param = new TTranslateBook();
            param.setPhone(tTranslate.getPhone());
            param.setIsRecovery(0);
            List<TTranslateBook> books = tTranslateBookService.selectTTranslateBookList(param);
            if(books.size() > 0){
                return AjaxResult.error("该企业电话:" + tTranslate.getPhone() + "已被领取");
            }
            tTranslate.setCreateBy(getUsername());
            tTranslate.setCreateTime(new Date());
            return toAjax(tTranslateService.insertTTranslate(tTranslate));
        } else {
            return AjaxResult.error("添加失败，资源" + tTranslate.getTranslateName() + "已存在");
        }
    }

    /**
     * 修改企业资源
     */
    @Log(title = "企业资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TTranslate tTranslate)
    {
        return toAjax(tTranslateService.updateTTranslate(tTranslate));
    }

    /**
     * 删除企业资源
     */
    @PreAuthorize("@ss.hasPermi('business:translate:remove')")
    @Log(title = "企业资源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{translateIds}")
    public AjaxResult remove(@PathVariable Long[] translateIds)
    {
        return toAjax(tTranslateService.deleteTTranslateByTranslateIds(translateIds));
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<TTranslate> util = new ExcelUtil<TTranslate>(TTranslate.class);
        util.importTemplateExcel(response, "客户数据");
    }


    @Log(title = "企业资源" , businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<TTranslate> util = new ExcelUtil<TTranslate>(TTranslate.class);
        List<TTranslate> list = util.importExcel(file.getInputStream());
        String message = tTranslateService.imports(list);
        return success(message);
    }

    /**
     * 领取企业资源
     */
    @Log(title = "企业资源", businessType = BusinessType.UPDATE)
    @PostMapping("/getBookIds/{translateIds}")
    public AjaxResult getBookIds(@PathVariable Long[] translateIds) {
        LoginUser user = getLoginUser();

        for (int i = 0; i < translateIds.length; i++) {
            TTranslate tTranslate = tTranslateService.selectTTranslateByTranslateId(translateIds[i]);

            //判断次数
            TTranslateBook param = new TTranslateBook();
            param.setUserId(getUserId());
//            param.setIsRecovery(0);
            int num = tTranslateBookService.selectTTranslateBookListtoDay(param).size();

            if(num >= getLoginUser().getUser().getUseNumTrn()){
                return AjaxResult.error("您已没有领取次数！");
            }

            TTranslateBook tTranslateBook = new TTranslateBook();
            tTranslateBook.setTranslateId(tTranslate.getTranslateId());
            tTranslateBook.setTranslateName(tTranslate.getTranslateName());
            tTranslateBook.setContacts(tTranslate.getContacts());
            tTranslateBook.setPhone(tTranslate.getPhone());
            tTranslateBook.setAddr(tTranslate.getAddr());
            tTranslateBook.setCustomerDemand(tTranslate.getCustomerDemand());
            tTranslateBook.setSources(tTranslate.getSources());
            tTranslateBook.setUserId(getUserId());
            tTranslateBook.setUserName(user.getUser().getNickName());
            tTranslateBook.setNotes(tTranslate.getNotes());
            tTranslateBook.setDeptId(user.getDeptId());
            tTranslateBookService.insertTTranslateBook(tTranslateBook);
            tTranslateService.deleteTTranslateByTranslateId(tTranslate.getTranslateId());
        }

        return AjaxResult.success();

    }


    /**
     * 领取企业资源
     */
    @Log(title = "企业资源", businessType = BusinessType.UPDATE)
    @PostMapping("/getBook")
    public AjaxResult getBook(@RequestBody TTranslate tTranslate) {
        LoginUser user = getLoginUser();

        //判断次数
        TTranslateBook param = new TTranslateBook();
        param.setUserId(getUserId());
//        param.setIsRecovery(0);
        int num = tTranslateBookService.selectTTranslateBookListtoDay(param).size();

        if(num >= getLoginUser().getUser().getUseNumTrn()){
            return AjaxResult.error("您已没有领取次数！");
        }

        TTranslateBook tTranslateBook = new TTranslateBook();
        tTranslateBook.setTranslateId(tTranslate.getTranslateId());
        tTranslateBook.setTranslateName(tTranslate.getTranslateName());
        tTranslateBook.setContacts(tTranslate.getContacts());
        tTranslateBook.setPhone(tTranslate.getPhone());
        tTranslateBook.setAddr(tTranslate.getAddr());
        tTranslateBook.setCustomerDemand(tTranslate.getCustomerDemand());
        tTranslateBook.setSources(tTranslate.getSources());
        tTranslateBook.setUserId(getUserId());
        tTranslateBook.setUserName(user.getUser().getNickName());
        tTranslateBook.setNotes(tTranslate.getNotes());
        tTranslateBook.setDeptId(user.getDeptId());
        tTranslateBookService.insertTTranslateBook(tTranslateBook);
        tTranslateService.deleteTTranslateByTranslateId(tTranslate.getTranslateId());

        return AjaxResult.success();

    }
}
