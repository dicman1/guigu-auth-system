package com.atguigu.system.service.Impl;
import com.atguigu.model.system.SysMenu;
import com.atguigu.model.system.SysUser;
import com.atguigu.model.vo.RouterVo;
import com.atguigu.model.vo.SysUserQueryVo;
import com.atguigu.system.mapper.SysUserMapper;
import com.atguigu.system.service.SysMenuService;
import com.atguigu.system.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-02
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysMenuService sysMenuService;
    @Override
    public SysUser getByUserName(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        System.out.println(sysUser);
        return sysUser;
    }

    /**
     * 用户分页条件查询
     * @param pageParam
     * @param userQueryVo
     * @return
     */
    @Override
    public IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo userQueryVo) {
        return baseMapper.selectPage(pageParam,userQueryVo);
    }
    /**
     * 更改用户状态
     * @param id
     * @param status
     */
    @Override
    public void updateStatus(String id, Integer status){
        //容易犯的错误是sysUser只是获取的对象 并未传入dao层去计算
        SysUser sysUser = baseMapper.selectById(id);
        sysUser.setStatus(status);
        baseMapper.updateById(sysUser);
    }
    @Override
    public HashMap<String, Object> getUserInfo(String username) {
        SysUser sysUser = this.getByUserName(username);

        HashMap<String, Object> hashMap = new HashMap<>();
        List<RouterVo> routerVoList = sysMenuService.getUserMenuList(sysUser.getId());
        List<String> permsList = sysMenuService.getUserButtonList(sysUser.getId());

        hashMap.put("name", sysUser.getName());
        hashMap.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        //当前权限控制使用不到，我们暂时忽略
        hashMap.put("roles",  new HashSet<>());
        hashMap.put("buttons", permsList);
        hashMap.put("routers", routerVoList);
        return hashMap;
    }
}
