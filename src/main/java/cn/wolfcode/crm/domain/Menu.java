package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Menu extends BaseDomain {

    private String text;

    private String url;

    //上级菜单
    private Method parent;

    //子菜单
    private List<Menu> children = new ArrayList<>();

}