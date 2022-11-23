package com.atguigu.system.utils;

import com.atguigu.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {
    /**
     * 递归方法创建菜单
     * @param sysMenus
     * @return
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenus) {
        ArrayList<SysMenu> trees = new ArrayList<>();
        for (SysMenu sysMenu : sysMenus) {
            if(sysMenu.getParentId().equals("0")){
                trees.add(findChildren(sysMenu,sysMenus));
            }
        }
        return trees;
    }

    /**
     * 递归方法查找子节点
     * @param sysMenu
     * @param sysMenus
     * @return
     */
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenus){
        //初始化防止报错
        sysMenu.setChildren(new ArrayList<>());

        for (SysMenu menu : sysMenus) {
            String id = sysMenu.getId();
            String parentId = menu.getParentId();
            if(id.equals(parentId)){
                sysMenu.getChildren().add(findChildren(menu,sysMenus));
            }
        }
        return sysMenu;
    }
}
