package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
//Spring与junit整合
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit Spring配置文件的位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {
    //注入DAO实现类依赖
    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void name() {
    }

    @Test
    public void insertSuccessKilled() {
     long seckillId=1004;
     long phone=13368364394L;
     int successkilled=successKilledDao.insertSuccessKilled(seckillId,phone);
     /*
     第一次为1
     第二次为0
      */
     System.out.println(successkilled);
    }

    @Test
    public void queryByIdWithSeckill() {
        long seckillId=1000;
        SuccessKilled s=successKilledDao.queryByIdWithSeckill(seckillId);
        System.out.println(s);
        System.out.println(s.getSeckill());
 }
}