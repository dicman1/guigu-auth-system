package com.atguigu.system.service.Impl;

import com.atguigu.system.custom.CustomUser;
import com.atguigu.model.system.SysUser;
import com.atguigu.system.service.SysMenuService;
import com.atguigu.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService  sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getByUserName(username);
        if(sysUser == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        //注意integer的比较
        if(sysUser.getStatus().intValue() == 0){
            throw new RuntimeException("用户已经被禁用了");
        }
        List<String> userButtonList = sysMenuService.getUserButtonList(sysUser.getId());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String perm : userButtonList) {
            authorities.add(new SimpleGrantedAuthority(perm.trim()));
        }
        //根据userid查询操作权限数据
        return new CustomUser(sysUser, authorities);

    }
}
