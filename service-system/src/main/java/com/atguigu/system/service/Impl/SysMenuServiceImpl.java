package com.atguigu.system.service.Impl;
import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysMenu;
import com.atguigu.model.system.SysRoleMenu;
import com.atguigu.model.vo.AssginMenuVo;
import com.atguigu.model.vo.RouterVo;
import com.atguigu.system.exception.GuiguException;
import com.atguigu.system.mapper.SysMenuMapper;
import com.atguigu.system.mapper.SysRoleMenuMapper;
import com.atguigu.system.service.SysMenuService;
import com.atguigu.system.utils.MenuHelper;
import com.atguigu.system.utils.RouterHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.webkit.graphics.WCRenderQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-05
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        //首先删除原先表中和role先关的数据
        QueryWrapper<SysRoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",assginMenuVo.getRoleId());
        sysRoleMenuMapper.delete(wrapper);
        //添加新数据
        List<String> menuIdList = assginMenuVo.getMenuIdList();
        for (String menuid: menuIdList) {
            if(menuid != null){
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(menuid);
                sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
                sysRoleMenuMapper.insert(sysRoleMenu);
            }
        }
    }

    @Override
    public List<SysMenu> findSysMenuByRoleId(String roleId){
        //查询全部的菜单
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("status",1);
        List<SysMenu> sysMenuList = baseMapper.selectList(wrapper);
        //查询角色已分配的菜单id
        QueryWrapper<SysRoleMenu> sysRoleMenuQueryWrapper = new QueryWrapper<>();
        sysRoleMenuQueryWrapper.eq("role_id", roleId);
        List<SysRoleMenu> roleMenuList = sysRoleMenuMapper.selectList(sysRoleMenuQueryWrapper);
        //处理菜单id数组
        ArrayList<String> menulist = new ArrayList<>();
        for (SysRoleMenu menu : roleMenuList) {
           menulist.add(menu.getMenuId());
        }
        //比对数组
        for (SysMenu sysMenu : sysMenuList) {
            if(menulist.contains(sysMenu.getId())){
                sysMenu.setSelect(true);
            }else{
                sysMenu.setSelect(false);
            }
        }
        //调用方法树形展示
        List<SysMenu> sysMenus = MenuHelper.buildTree(sysMenuList);
        return sysMenus;
    }

    @Override
    public List<SysMenu> findNodes(){
        //查询全部菜单
        List<SysMenu> sysMenus = baseMapper.selectList(null);
        //构建树形数据
        List<SysMenu> result = MenuHelper.buildTree(sysMenus);
        return result;
    }

    @Override
    public void removeById(String id) {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(wrapper);
        if(count > 0) {
            throw new GuiguException(201, "子菜单存在,无法删除");
        }
        baseMapper.deleteById(id);
    }

    //菜单
    @Override
    public List<RouterVo> getUserMenuList(String id) {
        List<SysMenu> sysMenuList = null;
        if ("1".equals(id)) {
            sysMenuList = sysMenuMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 1).orderByAsc("sort_value"));
        } else {
            sysMenuList = sysMenuMapper.getUserMenuList(id);
        }
        //构建树形菜单表示
        List<SysMenu> sysMenusTree = MenuHelper.buildTree(sysMenuList);
        //路由方式展现
        List<RouterVo> routerVoList = RouterHelper.buildRouters(sysMenusTree);
        return routerVoList;
    }

    //按钮权限值获取
    @Override
    public List<String> getUserButtonList(String id){
        //拿到sysmenu数据
        List<SysMenu> sysMenuList = null;
        if ("1".equals(id)) {
            sysMenuList = sysMenuMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 1).orderByAsc("sort_value"));
        } else {
            sysMenuList = sysMenuMapper.getUserMenuList(id);
        }
        //创建返回数据列表
        ArrayList<String> permissionList = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            if(sysMenu.getType() == 2){
                String sysMenuPerms = sysMenu.getPerms();
                permissionList.add(sysMenuPerms);
            }
        }
        return permissionList;
    }
}
