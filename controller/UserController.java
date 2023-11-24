package com.example.food.controller;

import com.example.food.common.ReturnCodeUtil;
import com.example.food.dao.UserDao;
import com.example.food.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restful/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    //管理员登录
        @RequestMapping("/userLogin")
    public ReturnCodeUtil getAdminInfo(@RequestBody User user){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
            User user1 = userDao.queryUserByAccount(user);
            if(user1 != null && user1.getPassword() != null &&  user1.getPassword().equals(user.getPassword())){
                returnCodeUtil.setResult(true);
            }else {
                returnCodeUtil.setResult(false);
            }
            returnCodeUtil.setData(user1);
        return returnCodeUtil;
    }

    //设置密码
    @RequestMapping("/updatePassword")
    public ReturnCodeUtil updatePassword(@RequestBody User user){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        int res = userDao.updatePassword(user);
        if(res>0){
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResult(false);
        }
        return returnCodeUtil;
    }





}
