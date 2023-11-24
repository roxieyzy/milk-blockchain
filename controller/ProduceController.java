package com.example.food.controller;

import com.example.food.common.Fabric;
import com.example.food.common.ReturnCodeUtil;
import com.example.food.dao.ProduceDao;
import com.example.food.entity.Produce;
import com.example.food.entity.Sale;
import com.example.food.entity.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restful/produce")
public class ProduceController {


@Autowired
private ProduceDao produceDao;

@Autowired
private Fabric fabric;

    //查询所有用户
    @RequestMapping("/queryAllUser")
    public ReturnCodeUtil queryAllUser(){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        List<Produce> produces = produceDao.queryAllUser();
        returnCodeUtil.setData(produces);
        returnCodeUtil.setResult(true);
        return returnCodeUtil;
    }


    //生产商登录
    @RequestMapping("/produceLogin")
    public ReturnCodeUtil getAdminInfo(@RequestBody Produce produce){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        Produce sale1 = produceDao.queryProduceByAccount(produce);
        if(sale1.getPpassword().equals(sale1.getPpassword())){
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResult(false);
        }
        returnCodeUtil.setData(sale1);
        return returnCodeUtil;
    }

    //生产商写入一条数据
    @RequestMapping("/insertData")
    public ReturnCodeUtil insertOne(@RequestBody Trace trace){
        String UUID = java.util.UUID.randomUUID().toString().replace("-","");
        trace.setOnlykey(UUID);
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        int res = produceDao.insertOneData(trace);

        if (res>0){
            fabric.sdk(trace,2);
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResult(false);
        }
        return returnCodeUtil;
    }

    //生产商更新一条数据
    @RequestMapping("/updateData")
    public ReturnCodeUtil update(@RequestBody Trace trace){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        int res = produceDao.updateTraceInfo(trace);
        if (res>0){
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResult(false);
        }
        return returnCodeUtil;
    }

    @RequestMapping("/deleteOne")
    public ReturnCodeUtil deleteOne(@RequestBody Trace trace){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        int res = produceDao.deleteOneById(trace);
        if (res>0){
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResult(false);
        }
        return returnCodeUtil;
    }

    //设置密码
    @RequestMapping("/updatePassword")
    public ReturnCodeUtil updatePassword(@RequestBody Produce user){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        int res = produceDao.updatePassword(user);
        if(res>0){
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResult(false);
        }
        return returnCodeUtil;
    }

    //管理员修改用户信息
    @RequestMapping("/updateUserInfo")
    public ReturnCodeUtil updateUser(@RequestBody Produce user){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        int res = produceDao.updateUserInfo(user);
        if(res>0){
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResult(false);
        }
        return returnCodeUtil;
    }
    //管理员删除一条用户
    @RequestMapping("/deleteOneUser")
    public ReturnCodeUtil delete(@RequestBody Produce user){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        int res = produceDao.deleteOneUser(user);
        if(res>0){
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResult(false);
        }
        return returnCodeUtil;
    }
    //管理员添加用户
    @RequestMapping("/insertOneUser")
    public ReturnCodeUtil insertOneUser(@RequestBody Produce user){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        int res = produceDao.insertOneUser(user);
        if(res>0){
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResult(false);
        }
        return returnCodeUtil;
    }
}
