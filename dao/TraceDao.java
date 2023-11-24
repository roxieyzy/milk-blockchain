package com.example.food.dao;


import com.example.food.entity.Trace;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper  // 告诉springboot这是一个mybatis的mapepr类
@Repository   // 交由spring容器管理
public interface TraceDao {

    //查询所有记录
    public List queryAll();

    //写入一条记录
    public int insertOne(Trace trace);

    //根据traceid查询
    public Trace queryByOnlyKey(Trace trace);




}
