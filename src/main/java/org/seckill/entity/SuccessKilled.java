package org.seckill.entity;

import java.util.Date;

public class SuccessKilled {
    private long seckillid;
    private long userPhone;
    private short state;
    private Date createTime;
    //变通
    //多对一
    private Seckill seckill;

    public long getSeckillId() {
        return seckillid;
    }

    public void setSeckillId(long seckillId) {
        this.seckillid = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date creatTime) {
        this.createTime = creatTime;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillid +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}
