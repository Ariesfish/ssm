package xyz.ariesfish.factory;

import xyz.ariesfish.domain.Account;
import xyz.ariesfish.service.IAccountService;
import xyz.ariesfish.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BeanFactory {

    private IAccountService accountService;
    public final void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    private TransactionManager txManager;
    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    public IAccountService getAccountService() {
        IAccountService proxyAccountService = (IAccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    // add transaction support
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object retValue = null;
                        try {
                            // begin
                            txManager.beginTransaction();
                            // do
                            retValue = method.invoke(accountService, args);
                            // commit
                            txManager.commit();
                            // return
                            return retValue;
                        } catch (Exception e) {
                            // rollback
                            txManager.rollback();
                            throw new RuntimeException(e);
                        } finally {
                            txManager.release();
                        }
                    }
                });

        return proxyAccountService;
    }
}
