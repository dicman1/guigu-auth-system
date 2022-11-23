package com.atguigu.system.test;

import com.atguigu.model.system.SysRole;
import com.atguigu.system.mapper.SysRoleMapper;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SysRoleMapperTest {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    //查询表所有记录
    @Test
    public void findAll(){
        List<SysRole> list = sysRoleMapper.selectList(null);
        for (SysRole sysRole : list) {
            System.out.println(sysRole);
        }
    }
    //增加
    @Test
    public void insert(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("测试1");
        sysRole.setRoleCode("测试111");
        sysRole.setDescription("测试1111");
        int row = sysRoleMapper.insert(sysRole);
        System.out.println(row);
        System.out.println(sysRole.getId());
    }
    //修改
    @Test
    public void update(){
        SysRole sysRole = sysRoleMapper.selectById(9);
        sysRole.setDescription("gjj");
        int i = sysRoleMapper.updateById(sysRole);
        System.out.println(i);
    }
    //删除
    @Test
    public void delete(){
        int i = sysRoleMapper.deleteById(9);
        System.out.println(i);
    }

    @Test
    public void deletebyids(){
        int i = sysRoleMapper.deleteBatchIds(Arrays.asList(1, 2));
        System.out.println(i);
    }




}
