package com.atguigu.system.service.Impl;

import com.atguigu.model.system.SysPost;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.vo.SysPostQueryVo;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.atguigu.system.mapper.SysPostMapper;
import com.atguigu.system.service.SysPostService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 岗位信息表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-14
 */
@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements SysPostService {
    @Override
    public IPage<SysPost> selectPage(Page<SysPost> pageParam, SysPostQueryVo sysPostQueryVo) {
        IPage<SysPost> pageModel = baseMapper.selectPage(pageParam,sysPostQueryVo);
        return pageModel;
    }
}
