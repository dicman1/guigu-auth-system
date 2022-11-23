package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysDept;
import com.atguigu.model.system.SysMenu;
import com.atguigu.system.service.SysDeptService;
import com.atguigu.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 组织机构 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-11-13
 */
@Api(tags = "部门管理")
@RestController
@RequestMapping("/admin/system/sysDept")
public class SysDeptController {
    @Autowired
    private SysDeptService sysDeptService;

    @ApiOperation(value = "部门列表")
    @GetMapping("/findNodes")
    public Result findNodes(){
        List<SysDept> list = sysDeptService.findNodes();
        return Result.ok(list);
    }
    @ApiOperation(value = "添加部门")
    @PostMapping("/save")
    public Result save(@RequestBody SysDept sysDept) {
        sysDeptService.save(sysDept);
        return Result.ok();
    }

    @ApiOperation(value = "修改部门信息")
    @PutMapping("/update")
    public Result updateById(@RequestBody SysDept sysDept) {
        sysDeptService.updateById(sysDept);
        return Result.ok();
    }

    @ApiOperation(value = "根据id查询菜单")
    @GetMapping("/findNode/{id}")
    public Result findNode(@PathVariable String id) {
        SysDept sysDept = sysDeptService.getById(id);
        return Result.ok(sysDept);
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        System.out.println("删除成功");
        sysDeptService.removeById(id);
        return Result.ok();
    }
}

