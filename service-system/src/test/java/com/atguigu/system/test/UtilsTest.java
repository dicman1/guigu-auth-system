package com.atguigu.system.test;

import com.atguigu.common.utils.JwtHelper;
import com.atguigu.common.utils.MD5;
import com.atguigu.model.system.SysDept;
import com.atguigu.system.mapper.SysDeptMapper;
import com.atguigu.system.service.SysDeptService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.java2d.pipe.SpanIterator;

import java.util.List;

@SpringBootTest
public class UtilsTest {

    @Test
    public void test(){
        String admin = JwtHelper.createToken("1", "admin");
        System.out.println(admin);
    }
}
