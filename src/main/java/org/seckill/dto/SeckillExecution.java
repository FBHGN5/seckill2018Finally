package org.seckill.dto;

import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;

public class SeckillExecution {
    private long seckillId;
    private int state;
    /*
    状态表示
     */
    private String stateInfo;
    /*
成功对象
     */



    private SuccessKilled seccessKilled;

    public SeckillExecution(long seckillId, SeckillStateEnum stateEnum, SuccessKilled seccessKilled) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.seccessKilled = seccessKilled;
    }
/*
失败
 */
    public SeckillExecution(long seckillId, SeckillStateEnum stateEnum) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
    public SuccessKilled getSeccessKilled() {
        return seccessKilled;
    }

    public void setSeccessKilled(SuccessKilled seccessKilled) {
        this.seccessKilled = seccessKilled;
    }
}
