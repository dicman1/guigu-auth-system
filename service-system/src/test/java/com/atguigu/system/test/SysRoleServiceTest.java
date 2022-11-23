package com.atguigu.system.test;

import com.atguigu.model.system.SysRole;
import com.atguigu.system.mapper.SysRoleMapper;
import com.atguigu.system.service.SysRoleService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SysRoleServiceTest {

    //调用service就可以直接完成操作
    @Autowired
    private SysRoleService sysRoleService;

    //查询所有
    @Test
    public void findAll(){
        List<SysRole> list = sysRoleService.list();
        System.out.println(list);
    }
    //修改
    @Test
    public void update(){
        SysRole sysRole = new SysRole();
        sysRole.setId("11");
        sysRole.setRoleName("测试2");
        sysRole.setRoleCode("测试222");
        sysRole.setDescription("测试2222");
        boolean b = sysRoleService.updateById(sysRole);
        System.out.println(b);
    }

}
