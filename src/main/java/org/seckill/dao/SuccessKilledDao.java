package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
/*
创建测试文件ctrl+shift+t
 */
public interface SuccessKilledDao {
    /*
    插入购买明细，过滤重复
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);
    /*
    根据查看成功秒杀列表，携带秒杀产品对象实体
     */
    SuccessKilled queryByIdWithSeckill(long seckillId);
    SuccessKilled selectByPrimaryKey(int seckillId);

}

