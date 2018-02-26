package org.seckill.service.impl;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dao.cache.RedisDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
@Service
public class SeckillServiceImpl implements SeckillService {
    private  Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;
    private final String slat="askjfnasj*()kfa@34893749817$dasopisfkCASOKV$%^@!";
    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.getAll();
    }

    @Override
    public Seckill getById(long seckillid) {
        return seckillDao.queryById(seckillid);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
         Seckill seckill=redisDao.getSeckill(seckillId);
         if(seckill==null)
         {
             seckill=seckillDao.queryById(seckillId);
             if(seckill==null)
             {
                 return new Exposer(false,seckillId);
             }
             else{
                 redisDao.putSeckill(seckill);
             }
         }


        Date startTime=seckill.getStartTime();
        Date endTime=seckill.getEndTime();
        Date nowTime=new Date();
        if(nowTime.getTime()<startTime.getTime()||nowTime.getTime()>endTime.getTime())
        {
            return  new Exposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }
        //转化特定字符串
        String md5=getMD5(seckillId);
        return new Exposer(true,md5,seckillId);
    }
    private String getMD5(long seckillId)
    {
        String base=seckillId+"/"+slat;
        String md5= DigestUtils.md5DigestAsHex(base.getBytes());


        return md5;
    }
    @Override
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
        if(md5==null||!md5.equals(getMD5(seckillId)))
        {
            throw new SeckillException("seckill data rewrite"+"md5:"+md5+"seckillID::"+seckillId);
        }
        //秒杀业务，减库存,秒杀成功记录
        Date nowTime=new Date();
        try {
            int updateCount=seckillDao.reduceNumber(seckillId,nowTime);
            //秒杀数量=0
            if(updateCount<=0)
            {
                throw new SeckillCloseException("秒杀结束！！");
            }
            else{
                //成功记录购买行为
                int insert=successKilledDao.insertSuccessKilled(seckillId,userPhone);
                //重复秒杀
                if(insert<=0)
                {
                    throw new RepeatKillException("秒杀重复");
                }
                else{
                    SuccessKilled successKilled=successKilledDao.queryByIdWithSeckill(seckillId);
                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS,successKilled);
                }
            }
        }
        catch (SeckillCloseException e1)
        {
            throw e1;
        }
        catch (RepeatKillException e2)
        {
            throw e2;
        }

        catch (Exception e)
        {
            logger.error(e.getMessage());
            //所有编译异常，转为运行异常
            throw new SeckillException("秒杀错误"+e.getMessage());
        }
    }
}
