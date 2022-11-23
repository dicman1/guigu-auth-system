package com.atguigu.system.service;

public interface AsyncLoginLogService{
    /**
     * 记录登录信息
     * @param username 用户名
     * @param status 状态
     * @param ipaddr ip
     * @param message 消息内容
     */
    void recordLoginLog(String username,Integer status,String ipaddr,String message);
}
