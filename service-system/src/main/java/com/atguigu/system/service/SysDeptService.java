package com.atguigu.system.service;

import com.atguigu.model.system.SysDept;
import com.atguigu.model.system.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 组织机构 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-13
 */
public interface SysDeptService extends IService<SysDept> {

    List<SysDept> findNodes();
}
