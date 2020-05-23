package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Menu;
import cn.wolfcode.crm.mapper.MenuMapper;
import cn.wolfcode.crm.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    MenuMapper mapper;

    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    public int insert(Menu record) {
        return mapper.insert(record);
    }

    public Menu selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<Menu> selectAll() {
        return mapper.selectAll();
    }

    public int updateByPrimaryKey(Menu record) {
        return mapper.updateByPrimaryKey(record);
    }

    public List<Menu> getRootMenu() {
        return mapper.getRootMenu();
    }

}
