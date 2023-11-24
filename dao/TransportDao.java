package com.example.food.dao;


import com.example.food.entity.Produce;
import com.example.food.entity.Sale;
import com.example.food.entity.Trace;
import com.example.food.entity.Transport;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper  // 告诉springboot这是一个mybatis的mapepr类
@Repository   // 交由spring容器管理
public interface TransportDao {


    //查询运输商
    public Transport queryTransportByAccount(Transport transport);
    //写入一条记录
    public int insertOneUser(Transport transport);
    //更新溯源记录
    public int updateTraceInfo(Trace trace);

    public int updatePassword(Transport Transport);
    //管理员修改用户信息
    public int updateUserInfo(Transport Transport);
    //管理员删除一个用户
    public int deleteOneUser(Transport transport);
    //查询所有用户
    public List<Transport> queryAllUser();

}
