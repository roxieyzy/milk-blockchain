package com.example.food.controller;

import com.example.food.common.Fabric;
import com.example.food.common.ReturnCodeUtil;
import com.example.food.dao.SaleDao;
import com.example.food.entity.Produce;
import com.example.food.entity.Sale;
import com.example.food.entity.Trace;
import com.example.food.entity.Transport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restful/sale")
public class SaleController {

    @Autowired
    private SaleDao saleDao;
    @Autowired
    private Fabric fabric;

    //查询所有用户
    @RequestMapping("/queryAllUser")
    public ReturnCodeUtil queryAllUser(){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        List<Sale> sales = saleDao.queryAllUser();
        returnCodeUtil.setData(sales);
        returnCodeUtil.setResult(true);
        return returnCodeUtil;
    }

    //销售商登录
    @RequestMapping("/saleLogin")
    public ReturnCodeUtil getAdminInfo(@RequestBody Sale sale){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        Sale sale1 = saleDao.querySaleByAccount(sale);
        if(sale1.getSpassword().equals(sale1.getSpassword())){
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResult(false);
        }
        returnCodeUtil.setData(sale1);
        return returnCodeUtil;
    }


    //销售商更新一条数据
    @RequestMapping("/updateData")
    public ReturnCodeUtil update(@RequestBody Trace trace){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        int res = saleDao.updateTraceInfo(trace);
        if (res>0){
            fabric.sdk(trace,4);
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResult(false);
        }
        return returnCodeUtil;
    }


    //设置密码
    @RequestMapping("/updatePassword")
    public ReturnCodeUtil updatePassword(@RequestBody Sale user){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        int res = saleDao.updatePassword(user);
        if(res>0){
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResult(false);
        }
        return returnCodeUtil;
    }
    //管理员修改用户信息
    @RequestMapping("/updateUserInfo")
    public ReturnCodeUtil updateUser(@RequestBody Sale user){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        int res = saleDao.updateUserInfo(user);
        if(res>0){
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResult(false);
        }
        return returnCodeUtil;
    }
    //管理员删除一条用户
    @RequestMapping("/deleteOneUser")
    public ReturnCodeUtil delete(@RequestBody Sale user){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        int res = saleDao.deleteOneUser(user);
        if(res>0){
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResult(false);
        }
        return returnCodeUtil;
    }
    //管理员添加用户
    @RequestMapping("/insertOneUser")
    public ReturnCodeUtil insertOneUser(@RequestBody Sale user){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        int res = saleDao.insertOneUser(user);
        if(res>0){
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResult(false);
        }
        return returnCodeUtil;
    }

}
