package com.atyyx.spring.proxy;

import com.atyyx.spring.proxy.impl.CalculatorImpl;

/**
 * 静态代理是一对一的
 */
public class CalculatorStaticProxy implements Calculator{


    private CalculatorImpl target;

    public CalculatorImpl getCalculatorImpl() {
        return target;
    }

    public void setCalculatorImpl(CalculatorImpl calculatorImpl)
    {
        this.target = calculatorImpl;
    }

    public CalculatorStaticProxy(CalculatorImpl target) {
        this.target = target;
    }


    @Override
    public int add(int i, int j)
    {
        // 快捷键是ctrl+alt+t
        int result= 0;
        try {
            System.out.println("日志，方法：add，参数:"+i+","+j);  // AOP中的前置分支
            result = target.add(i,j);   // AOP中的返回分支
            System.out.println("方法内部：,result:"+result);// AOP中的后置分支
        } catch (Exception e) {  // AOP中的异常分支
            e.printStackTrace();
        }
        finally {
        }
        return result;
    }

    @Override
    public int sub(int i, int j) {
        System.out.println("日志，方法：sub，参数:"+i+","+j);
        int result=target.sub(i,j);
        System.out.println("方法内部：,result:"+result);
        return result;
    }

    @Override
    public int mul(int i, int j) {
        System.out.println("日志，方法：mul，参数:"+i+","+j);
        int result=target.mul(i,j);
        System.out.println("方法内部：,result:"+result);
        return result;
    }

    @Override
    public int div(int i, int j) {
        System.out.println("日志，方法：div，参数:"+i+","+j);
        int result=target.div(i,j);
        System.out.println("方法内部：,result:"+result);
        return result;
    }
}
