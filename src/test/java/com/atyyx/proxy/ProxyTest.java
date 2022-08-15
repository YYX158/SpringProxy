package com.atyyx.proxy;

import com.atyyx.spring.proxy.Calculator;
import com.atyyx.spring.proxy.CalculatorStaticProxy;
import com.atyyx.spring.proxy.ProxyFactory;
import com.atyyx.spring.proxy.impl.CalculatorImpl;
import org.junit.jupiter.api.Test;

public class ProxyTest
{
    /**
     * 动态代理有两种
     * 1.jdk动态代理，要求必须有接口，最终生成的代理类在com.sun.proxy包下，类名叫做$proxy+一个数字  最终实现的代理类和目标类实现相同的接口
     * 2.cglib动态代理：不需要接口，，最终生成的代理类会继承我们的目标类，并且和目标类在相同的包下
     */
    @Test
    public void testProxy()
    {
        // 静态代理
//        CalculatorStaticProxy proxy = new CalculatorStaticProxy(new CalculatorImpl());
//        proxy.add(1,2);
        ProxyFactory proxyFactory=new ProxyFactory(new CalculatorImpl());
        // 虽然说不知道这个代理类的类型，但是可以通过向上转型
        //因为跟实现类都是实现同一个接口
        Calculator proxy =(Calculator ) proxyFactory.getProxy();
        proxy.add(3,2);
    }
}
