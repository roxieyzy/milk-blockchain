package com.example.food.controller;

import com.example.food.common.ReturnCodeUtil;
import com.example.food.dao.TraceDao;
import com.example.food.entity.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restful/trace")
public class TraceController {
    @Autowired
    private TraceDao traceDao;


    //获取admin信息
    @RequestMapping("/queryTrace")
    public ReturnCodeUtil getAdminInfo(@RequestBody Trace trace){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        List list = traceDao.queryAll();
        returnCodeUtil.setResult(true);
        returnCodeUtil.setData(list);
        return returnCodeUtil;
    }

}
