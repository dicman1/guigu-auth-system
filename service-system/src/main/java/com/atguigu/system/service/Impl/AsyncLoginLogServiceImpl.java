package com.atguigu.system.service.Impl;
import com.atguigu.model.system.SysLoginLog;
import com.atguigu.system.mapper.SysLoginLogMapper;
import com.atguigu.system.service.AsyncLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsyncLoginLogServiceImpl implements AsyncLoginLogService {
    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public void recordLoginLog(String username, Integer status, String ipaddr, String message) {
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setStatus(status);
        sysLoginLog.setUsername(username);
        sysLoginLog.setIpaddr(ipaddr);
        sysLoginLog.setMsg(message);
        //添加日志
        sysLoginLogMapper.insert(sysLoginLog);
    }
}
