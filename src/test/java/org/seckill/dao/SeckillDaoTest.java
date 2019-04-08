package org.seckill.dao;
/*
单元测试
 */
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
//Spring与junit整合
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit Spring配置文件的位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //注入DAO实现类依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void queryById() throws Exception {
   //   Seckill queryById(long seckillId);
        long id=1000;
        Seckill seckill=seckillDao.queryById(id);
        System.out.println(seckill.getId());
        System.out.println(seckill);

    }
@Test
    public void queryAll() throws Exception {
     List<Seckill> length=seckillDao.queryAll(1,2);
     for (Seckill seckill:length) {
           System.out.println(seckill);
       }
        }

@Test
    public void reduceNumber() throws Exception {
        Date killtime=new Date();
        int seckill=seckillDao.reduceNumber(1003,killtime);
        System.out.println(seckill);
    }
@Test
    public void seclet() throws Exception {
        long number=200;
        List<Seckill> seckill=seckillDao.seclet(number);
//        命名复写
        for (Seckill s:seckill) {
            System.out.println(s);
        }
    }
    }

