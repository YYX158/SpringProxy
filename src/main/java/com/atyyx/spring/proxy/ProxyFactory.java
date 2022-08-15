package com.atyyx.spring.proxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ProxyFactory
{
    private Object traget;

    public Object getProxy()
    {
        // ClassLoader loader, Class[] interfaces, InvocationHandler h
        // 利用的是反射
        // ClassLoader 叫做类加载器   指定加载动态生成的代理类的类加载器
        //类加载器有三种：根加载器(用C实现，一般是实现核心内库)、扩展类加载器(加载的是我们的扩展内库)、
        // 应用类加载器(自己写的类或者说是第三方jar包引入的类都是由应用类加载器来加载的)
        // 获取当前类的应用类加载器
        ClassLoader classLoader = this.getClass().getClassLoader();
        // Class[] interfaces：获取目标对象实现的所有接口的class对象的数组
        // 声明所实现的接口，只有目标类跟代理类实现的都是同一个接口才能行（就类似于静态代理）
        Class<?>[] interfaces = this.traget.getClass().getInterfaces();
        //InvocationHandler 处理方法的执行   设置代理类中的抽象方法如何重写
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
            {
                Object result = null;
                try {
                    System.out.println("日志，方法"+method.getName()+",参数"+ Arrays.toString(args));
                    result = method.invoke(traget, args);
                    System.out.println("日志，方法："+method.getName()+"结果："+result);
                } catch (Exception e) {
                   e.printStackTrace();
                    System.out.println("日志，方法"+method.getName()+",异常"+e);
                }
                finally {
                    System.out.println("日志，方法："+method.getName()+"执行完毕");
                }
                return result;
            }
        };

        return Proxy.newProxyInstance(classLoader,interfaces,handler);
    }
}
