package com.yootk.mall.action.front;

import com.alibaba.fastjson.JSONObject;
import com.yootk.common.action.abs.AbstractAction;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Controller;
import com.yootk.common.mvc.annotation.RequestMapping;
import com.yootk.mall.service.front.ICityServiceFront;

@Controller
@RequestMapping("/pages/front/center/city/")
public class CityActionFront extends AbstractAction {
    @Autowired
    private ICityServiceFront cityServiceFront;
    @RequestMapping("list")
    public void listByProvince(long pid) {
        try {
            super.print(JSONObject.toJSONString(this.cityServiceFront.list(pid)));
        } catch (Exception e) {
            super.print("{}");
        }
    }
}
