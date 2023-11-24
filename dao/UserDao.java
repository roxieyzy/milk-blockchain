package com.example.food.dao;


import com.example.food.entity.Produce;
import com.example.food.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper  // 告诉springboot这是一个mybatis的mapepr类
@Repository   // 交由spring容器管理
public interface UserDao {

    //查询管理员
    public User queryUserByAccount(User user);
    //修改密码
    public int updatePassword(User user);
}
