package com.atguigu.system.service.Impl;
import com.atguigu.model.system.SysDept;
import com.atguigu.system.mapper.SysDeptMapper;
import com.atguigu.system.service.SysDeptService;
import com.atguigu.system.utils.DeptHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 组织机构 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-13
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
    @Override
    public List<SysDept> findNodes() {
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>(null);
        List<SysDept> sysDepts = baseMapper.selectList(queryWrapper);
        List<SysDept> sysDeptList = DeptHelper.buildTree(sysDepts);
        return sysDeptList;
    }
}
