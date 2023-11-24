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
public interface SaleDao {

    //查询销售商
    public Sale querySaleByAccount(Sale sale);

    public int insertOneUser(Sale sale);
    //管理员修改用户信息
    public int updateUserInfo(Sale Sale);
    //管理员删除一个用户
    public int deleteOneUser(Sale sale);
    //修改密码
    public int updatePassword(Sale sale);
    //更新溯源记录
    public int updateTraceInfo(Trace trace);
    //查询所有用户
    public List<Sale> queryAllUser();
}
