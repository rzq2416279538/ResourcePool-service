package com.resourcepool.project.business.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.resourcepool.common.utils.StringUtils;
import com.resourcepool.framework.security.LoginUser;
import com.resourcepool.project.business.domain.TCustomerBook;
import com.resourcepool.project.business.service.ITCustomerBookService;
import com.resourcepool.project.system.domain.SysUser;
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
import com.resourcepool.project.business.domain.TCustomer;
import com.resourcepool.project.business.service.ITCustomerService;
import com.resourcepool.framework.web.controller.BaseController;
import com.resourcepool.framework.web.domain.AjaxResult;
import com.resourcepool.common.utils.poi.ExcelUtil;
import com.resourcepool.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import static com.resourcepool.common.utils.SecurityUtils.getUsername;

/**
 * 客户资源Controller
 *
 * @author 任遵强
 * @date 2023-06-07
 */
@RestController
@RequestMapping("/business/customer")
public class TCustomerController extends BaseController {
    @Autowired
    private ITCustomerService tCustomerService;

    @Autowired
    private ITCustomerBookService tCustomerBookService;

    /**
     * 查询客户资源列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TCustomer tCustomer) {
        startPage();
        List<TCustomer> list = tCustomerService.selectTCustomerList(tCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户资源列表
     */
    @Log(title = "客户资源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TCustomer tCustomer) {
        List<TCustomer> list = tCustomerService.selectTCustomerList(tCustomer);
        ExcelUtil<TCustomer> util = new ExcelUtil<TCustomer>(TCustomer.class);
        util.exportExcel(response, list, "客户资源数据");
    }

    /**
     * 获取客户资源详细信息
     */
    @GetMapping(value = "/{customerId}")
    public AjaxResult getInfo(@PathVariable("customerId") Long customerId) {
        return success(tCustomerService.selectTCustomerByCustomerId(customerId));
    }

    /**
     * 新增客户资源
     */
    @Log(title = "客户资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TCustomer tCustomer) {

        TCustomer u = tCustomerService.selectByCusterName(tCustomer.getCustomerName(), tCustomer.getCustomerPhone());
        if (StringUtils.isNull(u)) {

            TCustomerBook param = new TCustomerBook();
            param.setCustomerPhone(tCustomer.getCustomerPhone());
            param.setIsRecovery(0);

            List<TCustomerBook> books = tCustomerBookService.selectTCustomerBookList(param);
            if (books.size() > 0) {
                return AjaxResult.error("该客户电话:" + tCustomer.getCustomerPhone() + "已被领取");
            }

            tCustomer.setCreateBy(getUsername());
            tCustomer.setCreateTime(new Date());
            return toAjax(tCustomerService.insertTCustomer(tCustomer));
        } else {
            return AjaxResult.error("添加失败，客户" + tCustomer.getCustomerName() + "已存在");
        }
    }

    /**
     * 修改客户资源
     */
    @Log(title = "客户资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TCustomer tCustomer) {
        return toAjax(tCustomerService.updateTCustomer(tCustomer));
    }


    /**
     * 领取客户资源
     */
    @Log(title = "客户资源", businessType = BusinessType.UPDATE)
    @PostMapping("/getBook")
    public AjaxResult getBook(@RequestBody TCustomer tCustomer) {
        LoginUser user = getLoginUser();

        //判断次数
        TCustomerBook param = new TCustomerBook();
        param.setUserId(getUserId());
//        param.setIsRecovery(0);
        int num = tCustomerBookService.selectTCustomerBookListToDay(param).size();

        if(num >= getLoginUser().getUser().getUseNum()){
            return AjaxResult.error("您已没有领取次数！");
        }

        TCustomerBook tCustomerBook = new TCustomerBook();
        tCustomerBook.setCustomerId(tCustomer.getCustomerId());
        tCustomerBook.setUserId(getUserId());
        tCustomerBook.setCreateTime(new Date());
        tCustomerBook.setCustomerName(tCustomer.getCustomerName());
        tCustomerBook.setCustomerPhone(tCustomer.getCustomerPhone());
        tCustomerBook.setCertificateType(tCustomer.getCertificateType());
        tCustomerBook.setCertificateSpeciality(tCustomer.getCertificateSpeciality());
        tCustomerBook.setRemark(tCustomer.getRemark());
        tCustomerBook.setAddr(tCustomer.getAddr());
        tCustomerBook.setUserName(user.getUser().getNickName());
        tCustomerBook.setSources(tCustomer.getSources());
        tCustomerBook.setNotes(tCustomer.getNotes());
        tCustomerBook.setDeptId(user.getDeptId());
        tCustomerBookService.insertTCustomerBook(tCustomerBook);
        tCustomerService.deleteTCustomerByCustomerId(tCustomer.getCustomerId());

        return AjaxResult.success();
    }

    /**
     * 批量领取
     */
    @Log(title = "客户资源", businessType = BusinessType.UPDATE)
    @PostMapping("/getBookIds/{customerIds}")
    public AjaxResult getBookIds(@PathVariable Long[] customerIds) {
        LoginUser user = getLoginUser();

        for (int i = 0; i < customerIds.length; i++) {
            TCustomer tCustomer = tCustomerService.selectTCustomerByCustomerId(customerIds[i]);
            //判断次数
            TCustomerBook param = new TCustomerBook();
            param.setUserId(getUserId());
//            param.setIsRecovery(0);
            int num = tCustomerBookService.selectTCustomerBookListToDay(param).size();

            if(num >= getLoginUser().getUser().getUseNum()){
                return AjaxResult.error("您已没有领取次数！");
            }

            TCustomerBook tCustomerBook = new TCustomerBook();
            tCustomerBook.setCustomerId(tCustomer.getCustomerId());
            tCustomerBook.setUserId(getUserId());
            tCustomerBook.setCreateTime(new Date());
            tCustomerBook.setCustomerName(tCustomer.getCustomerName());
            tCustomerBook.setCustomerPhone(tCustomer.getCustomerPhone());
            tCustomerBook.setCertificateType(tCustomer.getCertificateType());
            tCustomerBook.setCertificateSpeciality(tCustomer.getCertificateSpeciality());
            tCustomerBook.setRemark(tCustomer.getRemark());
            tCustomerBook.setAddr(tCustomer.getAddr());
            tCustomerBook.setUserName(user.getUser().getNickName());
            tCustomerBook.setSources(tCustomer.getSources());
            tCustomerBook.setNotes(tCustomer.getNotes());
            tCustomerBook.setDeptId(user.getDeptId());
            tCustomerBookService.insertTCustomerBook(tCustomerBook);
            tCustomerService.deleteTCustomerByCustomerId(tCustomer.getCustomerId());
        }

        return AjaxResult.success();
    }

    /**
     * 删除客户资源
     */
    @PreAuthorize("@ss.hasPermi('business:customer:remove')")
    @Log(title = "客户资源", businessType = BusinessType.DELETE)
    @DeleteMapping("/{customerIds}")
    public AjaxResult remove(@PathVariable Long[] customerIds) {
        return toAjax(tCustomerService.deleteTCustomerByCustomerIds(customerIds));
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<TCustomer> util = new ExcelUtil<TCustomer>(TCustomer.class);
        util.importTemplateExcel(response, "客户数据");
    }


    @Log(title = "客户资源", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<TCustomer> util = new ExcelUtil<TCustomer>(TCustomer.class);
        List<TCustomer> list = util.importExcel(file.getInputStream());
        String message = tCustomerService.importCustomer(list);
        return success(message);
    }
}
