package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysPost;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.vo.SysPostQueryVo;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.atguigu.system.service.SysPostService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 岗位信息表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-11-14
 */
@RestController
@RequestMapping("/admin/system/sysPost")
public class SysPostController {

    @Autowired
    private SysPostService sysPostService;

    @ApiOperation("逻辑删除接口")
    @DeleteMapping("/remove/{id}")
    public Result removeRole(@PathVariable Long id){
        boolean isSuccess = sysPostService.removeById(id);
        if(isSuccess){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }


    @ApiOperation("条件分页查询接口")
    @GetMapping("/{page}/{limit}")
    public Result findPageQueryRole(@PathVariable Long page,
                                    @PathVariable Long limit,
                                    SysPostQueryVo sysPostQueryVo){
        //创建page对象
        Page<SysPost> sysPostPage = new Page<>(page, limit);
        //调用service方法
        IPage<SysPost> pageModel = sysPostService.selectPage(sysPostPage,sysPostQueryVo);
        return Result.ok(pageModel);
    }


    @ApiOperation("添加操作接口")
    @PostMapping("/save")
    public Result saveRole(@RequestBody SysPost sysPost){
        boolean isSuccess = sysPostService.save(sysPost);
        if(isSuccess){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }


    @ApiOperation("获取角色接口")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable Long id) {
        SysPost post = sysPostService.getById(id);
        return Result.ok(post);
    }


    @ApiOperation("修改操作接口")
    @PutMapping("/update")
    public Result updateRole(@RequestBody SysPost sysPost){
        boolean isSuccess = sysPostService.updateById(sysPost);
        if(isSuccess){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }


    @ApiOperation("批量删除接口")
    @DeleteMapping("/batchRemove")
    public Result removeByIds(@RequestBody List<Long> ids){
        sysPostService.removeByIds(ids);
        return Result.ok();
    }


}

