package com.atguigu.system.mapper;


import com.atguigu.model.system.SysPost;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.vo.SysPostQueryVo;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 岗位信息表 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2022-11-14
 */
public interface SysPostMapper extends BaseMapper<SysPost> {

    IPage<SysPost> selectPage(Page<SysPost> pageParam, @Param("vo")SysPostQueryVo sysPostQueryVo);

}
