package org.seckill.exception;
/*
ctrl+shift+i查看函数定义
重复秒杀异常
 */
public class RepeatKillException extends RuntimeException {
    public RepeatKillException(String message)
    {
        super(message);//相当于是指向当前对象的父类，这样就可以用super.xxx来引用父类的成员。
    }
    public RepeatKillException(String message,Throwable cause)
    {
        super(message,cause);
    }
}
