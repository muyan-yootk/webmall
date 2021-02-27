package com.yootk.mall.action.back;

import com.yootk.common.action.abs.AbstractAction;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Controller;
import com.yootk.common.mvc.annotation.RequestMapping;
import com.yootk.common.servlet.ModelAndView;
import com.yootk.common.servlet.MultipartFile;
import com.yootk.common.util.PageUtil;
import com.yootk.common.util.ftp.FTPUtil;
import com.yootk.mall.service.back.IGoodsServiceBack;
import com.yootk.mall.util.UploadFileToServer;
import com.yootk.mall.vo.Goods;

@Controller
@RequestMapping("/pages/back/admin/goods/")
public class GoodsActionBack extends AbstractAction {
    @Autowired
    private IGoodsServiceBack goodsService;

    @RequestMapping("add_pre")
    public String addPre() {
        return super.getPage("add.page");
    }

    @RequestMapping("add")
    public ModelAndView add(Goods goods, MultipartFile file) {
        // 所有表单提交的参数一定要自动封装在VO类的对象实例之中
        // 同时要进行的文件上传则必须使用一个MultipartFile包装
        ModelAndView mav = new ModelAndView(super.getBackForwardPage());
        System.err.println(goods);
        try {
            String fileName = super.createUploadFileName(file); // 生成上传文件名称
            goods.setPhoto(fileName); // 将生成的文件名称保存在goods对象之中
            String msg = super.getMessge("vo.add.failure", "商品");
            if (this.goodsService.add(goods)) {
                FTPUtil.upload(this.getUploadPath(), file, fileName); // 实现文件上传
                msg = super.getMessge("vo.add.success", "商品"); // 保存成功
            }
            String path = super.getPage("add.action");
            mav.add(AbstractAction.PATH_ATTRIBUTE_NAME, path);
            mav.add(AbstractAction.MSG_ATTRIBUTE_NAME, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping("list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView(super.getPage("list.page"));
        PageUtil pu = new PageUtil(super.getPage("list.action"), "商品名称:name");
        try {
            mav.add(
                    this.goodsService.list(pu.getCurrentPage(), pu.getLineSize(), pu.getColumn(), pu.getKeyword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @Override
    public String getUploadPath() {
        return "/upload/goods/";
    }
}
