package xyz.ariesfish.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("txManager")
@Aspect
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* xyz.ariesfish.service.*.*(..))")
    private void pt(){}

    //@Before("pt()")
    public void beginTransaction() {
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@AfterReturning("pt()")
    public void commit() {
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@AfterThrowing("pt()")
    public void rollback() {
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@After("pt()")
    public void release() {
        try {
            // return connection back to connection pool
            connectionUtils.getThreadConnection().close();
            // unbind connection from thread
            connectionUtils.removeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Around("pt()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) {
        Object retValue = null;
        try {
            Object[] args = pjp.getArgs();
            this.beginTransaction();
            retValue = pjp.proceed(args);
            this.commit();

            return retValue;
        } catch (Throwable t) {
            this.release();
            throw new RuntimeException(t);
        } finally {
            this.release();
        }
    }
}
