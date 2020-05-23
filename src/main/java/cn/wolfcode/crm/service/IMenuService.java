package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Menu;

import java.util.List;

public interface IMenuService {
    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Long id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

    List<Menu> getRootMenu();
}
