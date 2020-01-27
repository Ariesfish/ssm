package xyz.ariesfish.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Client {
    public static void main(String[] args) {
        final Producer producer = new Producer();

        Producer cglibProducer = (Producer)Enhancer.create(Producer.class,
                new MethodInterceptor() {
                    /**
                     *
                     * @param proxy
                     * @param method
                     * @param args
                     * above three are same as InvocationHandler.invoke()
                     * @param methodProxy: proxy of current method
                     * @return
                     * @throws Throwable
                     */
                    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                        Object retValue = null;
                        Float money = (Float) args[0];
                        if ("saleProduct".equals(method.getName())) {
                            retValue = method.invoke(producer, money * 0.8f);
                        }
                        return retValue;
                    }
                });

        cglibProducer.saleProduct(10000f);
    }
}
