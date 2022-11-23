package com.atguigu.system.controller;
import com.atguigu.common.result.Result;
import com.atguigu.common.utils.JwtHelper;
import com.atguigu.common.utils.MD5;
import com.atguigu.model.system.SysUser;
import com.atguigu.model.vo.LoginVo;
import com.atguigu.system.exception.GuiguException;
import com.atguigu.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "后台登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {
    @Autowired
    private SysUserService sysUserService;
    /**
     * 登录
     * @return
     */
    //login
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo){
        SysUser sysUser = sysUserService.getByUserName(loginVo.getUsername());
        //空用户
        if(sysUser == null){
            throw new GuiguException(20001,"用户不存在");
        }
        //密码错误
        if(!MD5.encrypt(loginVo.getPassword()).equals(sysUser.getPassword())){
            throw new GuiguException(20001,"用户密码错误");
        }
        //状态异常
        if(sysUser.getStatus().intValue() == 0){
            throw new GuiguException(20001,"状态错误");
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("token",JwtHelper.createToken(sysUser.getId(),sysUser.getUsername()));
        return Result.ok(hashMap);
    }
    /**
     * 获取用户信息
     * @return
     */
    //info
    @GetMapping("/info")
    public Result info(HttpServletRequest request){
        //获取token字符串
        String token = request.getHeader("token");
        //获取用户名
        String username = JwtHelper.getUsername(token);
        //根据用户名获取用户信息(基本信息 和 菜单权限 和 按钮权限数据)
        HashMap<String,Object> hashMap = sysUserService.getUserInfo(username);
        return Result.ok(hashMap);
    }
    /**
     * 退出
     * @return
     */
    @PostMapping("/logout")
    public Result logout(){
        return Result.ok();
    }
}
