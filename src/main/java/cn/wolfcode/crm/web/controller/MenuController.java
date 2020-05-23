package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Menu;
import cn.wolfcode.crm.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    IMenuService menuService;

    @RequestMapping("getRootMenu")
    @ResponseBody
    public List<Menu> getRootMenu() {
        return menuService.getRootMenu();
    }
}
