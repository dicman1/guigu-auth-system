package com.atguigu.system.utils;

import com.atguigu.model.system.SysDept;
import com.atguigu.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class DeptHelper{
    /**
     * 递归方法创建部门
     * @param sysDepts 查询出来的部门集合
     * @return
     */
    public static List<SysDept> buildTree(List<SysDept> sysDepts) {
        ArrayList<SysDept> trees = new ArrayList<>();
        for (SysDept sysDept : sysDepts) {
            if(sysDept.getParentId().equals("0")){
                trees.add(findChildren(sysDept,sysDepts));
            }
        }
        return trees;
    }

    /**
     * 递归方法查找子节点
     * @param sysDept 匹配的子节点
     * @param sysDepts 所有部门集合
     * @return
     */
    private static SysDept findChildren(SysDept sysDept,List<SysDept> sysDepts){
        //初始化防止报错
        sysDept.setChildren(new ArrayList<SysDept>());

        for (SysDept dept : sysDepts) {
            String id = sysDept.getId();
            String parentId = dept.getParentId();
            if(id.equals(parentId)){
                sysDept.getChildren().add(findChildren(dept,sysDepts));
            }
        }
        return sysDept;
    }
}
