package com.atguigu.system.service;


import com.atguigu.model.system.SysPost;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.vo.SysPostQueryVo;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 岗位信息表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-14
 */
public interface SysPostService extends IService<SysPost> {

    IPage<SysPost> selectPage(Page<SysPost> pageParam, SysPostQueryVo sysPostQueryVo);
}
