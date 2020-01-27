package xyz.ariesfish.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {

    public static void main(String[] args) {
        final IProducer producer = new Producer();

        IProducer proxyProducer = (IProducer)Proxy.newProxyInstance(Producer.class.getClassLoader(),
                Producer.class.getInterfaces(),
                new InvocationHandler() {
                    /**
                     * run every time when any methods be called
                     * @param proxy
                     * @param method: current executing method
                     * @param args: args of current method
                     * @return: same return value as proxy method
                     * @throws Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object retValue = null;
                        Float money = (Float) args[0];
                        if ("saleProduct".equals(method.getName())) {
                            retValue = method.invoke(producer, money * 0.8f);
                        }
                        return retValue;
                    }
                });

        proxyProducer.saleProduct(10000f);
    }
}
