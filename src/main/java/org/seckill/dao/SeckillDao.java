package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;
/*定义抽象方法,查表用实体命名*/
public interface SeckillDao {
    /*
    减少库存
    java没有保存形参的记录
     */
    int reduceNumber(@Param("seckillId")long seckillId,@Param("killTime") Date killTime);
    /*
    根据Id查询秒杀对象
     */
    Seckill queryById(long seckillid);
    /*
    根据偏移量查看秒杀商品列表
     */

    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
    List<Seckill> getAll();
    /*
    根据Number查询
     */
    List<Seckill> seclet(long number);

}
