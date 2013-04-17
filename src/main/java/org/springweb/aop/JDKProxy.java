package org.springweb.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springweb.service.HelloService;

/**
 * jdk��̬����ֻҪ֯���߼�һ����������ʹ��ͬһ��������󣬽ӿ��еķ���������ʱ������������invoke����
 * ����ʵ�ֽӿڣ�
 * @author weijian.zhongwj
 *
 */
public class JDKProxy implements InvocationHandler{

	private Object target;
	
	public JDKProxy(Object obj){
		this.target = obj;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("jdk��̬��������ִ��ǰ");
		Object obj = null;
		try {
			obj =  method.invoke(target, args);
		} catch (Exception e) {
			System.out.println("jdk��̬�����׳��쳣��������");
			throw e;
		}
		System.out.println("jdk��̬��������ִ�к�");
		return obj;
	}
	
	public static void main(String[] args) {
		 HelloService helloService = new HelloServiceImpl();
		 JDKProxy helloServiceProxy = new JDKProxy(helloService);
		 HelloService helloProxyService = (HelloService) Proxy.newProxyInstance(helloService.getClass().getClassLoader(),  helloService.getClass().getInterfaces(), helloServiceProxy);   //Ҫ�󶨽ӿ�(����һ��ȱ�ݣ�cglib�ֲ�����һȱ��)
		 helloProxyService.sayHello();
	}

}
