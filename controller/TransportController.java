package com.example.food.controller;

import com.example.food.common.Fabric;
import com.example.food.common.ReturnCodeUtil;
import com.example.food.dao.TraceDao;
import com.example.food.dao.TransportDao;
import com.example.food.entity.Sale;
import com.example.food.entity.Trace;
import com.example.food.entity.Transport;
import com.example.food.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restful/traceSport")
public class TransportController {

@Autowired
private TransportDao transportDao;
@Autowired
private Fabric fabric;


    //查询所有用户
    @RequestMapping("/queryAllUser")
    public ReturnCodeUtil queryAllUser(){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        List<Transport> transports = transportDao.queryAllUser();
        returnCodeUtil.setData(transports);
        returnCodeUtil.setResult(true);
        return returnCodeUtil;
    }

    //运输商登录
    @RequestMapping("/tranportLogin")
    public ReturnCodeUtil getAdminInfo(@RequestBody Transport transport){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        if(null == transport.getTaccount() || null == transport.getTpassword()){
            returnCodeUtil.setResult(false);
            return returnCodeUtil;
        }
        Transport transport1 = transportDao.queryTransportByAccount(transport);
        if(null != transport1 && transport.getTpassword().equals(transport1.getTpassword())){
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResult(false);
        }
        returnCodeUtil.setData(transport1);
        return returnCodeUtil;
    }

    //运输商更新一条数据
    @RequestMapping("/updateData")
    public ReturnCodeUtil update(@RequestBody Trace trace){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        int res = transportDao.updateTraceInfo(trace);
        if (res>0){
            fabric.sdk(trace,3);
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResult(false);
        }
        return returnCodeUtil;
    }

    //设置密码
    @RequestMapping("/updatePassword")
    public ReturnCodeUtil updatePassword(@RequestBody Transport user){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        int res = transportDao.updatePassword(user);
        if(res>0){
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResult(false);
        }
        return returnCodeUtil;
    }
    //管理员修改用户信息
    @RequestMapping("/updateUserInfo")
    public ReturnCodeUtil updateUser(@RequestBody Transport user){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        int res = transportDao.updateUserInfo(user);
        if(res>0){
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResult(false);
        }
        return returnCodeUtil;
    }
    //管理员删除一条用户
    @RequestMapping("/deleteOneUser")
    public ReturnCodeUtil delete(@RequestBody Transport user){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        int res = transportDao.deleteOneUser(user);
        if(res>0){
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResult(false);
        }
        return returnCodeUtil;
    }
    //管理员添加用户
    @RequestMapping("/insertOneUser")
    public ReturnCodeUtil insertOneUser(@RequestBody Transport user){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        int res = transportDao.insertOneUser(user);
        if(res>0){
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResult(false);
        }
        return returnCodeUtil;
    }

}
