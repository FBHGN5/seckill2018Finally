package org.seckill.service;
/*
集成测试
 */
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.security.provider.MD5;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
   private SeckillService seckillService;
    @Test
    public void getSeckillList() {
        List<Seckill> list=seckillService.getSeckillList();
        logger.info("list={}",list);
    }

    @Test
    public void getById() {
        long id=1000;
        Seckill seckill=seckillService.getById(id);
        logger.info("seckill={}",seckill);

    }

    @Test
    public void exportSeckillUrl() {
        long id=1000;
        Exposer exposer=seckillService.exportSeckillUrl(id);
        logger.info("exposer{}",exposer);
        String md5=exposer.getMd5();
        System.out.println(md5);
    }

    @Test
    public void executeSeckill() {
        long id=1000;
        long phone=200;
        String md5="63f8ed06f21c01b191104a6ced1165f9";
        try{
            SeckillExecution execution=seckillService.executeSeckill(id,phone,md5);
            logger.info("result{}",execution);
        }catch(RepeatKillException e){
          logger.error(e.getMessage());
        }
        catch(SeckillCloseException e){
            logger.error(e.getMessage());
        }

    }
    @Test
    public void testSeckill() throws  Exception{
        long id=1000;
        Exposer exposer=seckillService.exportSeckillUrl(id);
        if(exposer.isExposed())
        {
            logger.info("exposer{}",exposer);
             long phone=200;
            String md5=exposer.getMd5();
            System.out.println(md5);
            try{
                SeckillExecution execution=seckillService.executeSeckill(id,phone,md5);
                logger.info("result{}",execution);
            }catch(RepeatKillException e){
                logger.error(e.getMessage());
            }
            catch(SeckillCloseException e){
                logger.error(e.getMessage());
            }

        }else{
            //秒杀为开启
            logger.warn("exposer={}",exposer);
        }

    }
}