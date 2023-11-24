package com.example.food.dao;


import com.example.food.entity.Produce;
import com.example.food.entity.Sale;
import com.example.food.entity.Trace;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper  // 告诉springboot这是一个mybatis的mapepr类
@Repository   // 交由spring容器管理
public interface ProduceDao {
    //查询生产商
    public Produce queryProduceByAccount(Produce produce);

    //管理员写入一个用户
    public int insertOneUser(Produce produce);
    //
    //管理员修改用户信息
    public int updateUserInfo(Produce produce);

    //修改密码
    public int updatePassword(Produce produce);
    //更新溯源记录
    public int updateTraceInfo(Trace trace);
    //管理员删除一个用户
    public int deleteOneUser(Produce produce);

    public int insertOneData(Trace trace);

    public int deleteOneById(Trace trace);

    //查询所有用户
    public List<Produce> queryAllUser();




}
