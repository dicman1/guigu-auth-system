package com.atguigu.system.service.Impl;

import com.atguigu.model.system.SysRole;
import com.atguigu.model.system.SysUserRole;
import com.atguigu.model.vo.AssginRoleVo;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.atguigu.system.mapper.SysRoleMapper;
import com.atguigu.system.mapper.SysUserRoleMapper;
import com.atguigu.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>implements SysRoleService{

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo){
        IPage<SysRole> pageModel = baseMapper.selectPage(pageParam, sysRoleQueryVo);
        return pageModel;
    }

    /**
     * 查询角色实现
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> getRoleByUserId(String id) {
        HashMap<String, Object> roleMap = new HashMap<>();
        //查询全部角色
        List<SysRole> sysRolesList = sysRoleMapper.selectList(null);
        roleMap.put("allRoles",sysRolesList);
        //查询属于用户的角色id
        QueryWrapper<SysUserRole> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.eq("user_id",id);
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.selectList(roleQueryWrapper);
        ArrayList<String> arrayList = new ArrayList<>();
        for (SysUserRole sysUserRole : sysUserRoleList) {
            String roleId = sysUserRole.getRoleId();
            arrayList.add(roleId);
        }
        roleMap.put("userRoleIds",arrayList);
        return roleMap;
    }

    /**
     * 分配角色功能
     * @param assginRoleVo
     */
    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        //首先删除数据
        QueryWrapper<SysUserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.eq("user_id",assginRoleVo.getUserId());
        sysUserRoleMapper.delete(userRoleQueryWrapper);
        //添加数据
        List<String> roleIdList = assginRoleVo.getRoleIdList();
        for (String id : roleIdList) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(assginRoleVo.getUserId());
            sysUserRole.setRoleId(id);
            sysUserRoleMapper.insert(sysUserRole);
        }
    }
}
