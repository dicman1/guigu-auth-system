package com.atguigu.system.service;
import com.atguigu.model.system.SysUser;
import com.atguigu.model.vo.SysUserQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-02
 */
public interface SysUserService extends IService<SysUser> {

    IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo userQueryVo);
    void updateStatus(String id, Integer status);

    SysUser getByUserName(String username);

    HashMap<String, Object> getUserInfo(String username);
}
