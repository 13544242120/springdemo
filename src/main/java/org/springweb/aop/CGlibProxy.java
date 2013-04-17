package org.springweb.aop;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * CGlib���� ����ҪĿ�����ʵ��ĳ���ӿڣ�ͨ�����෽ʽ��������ʵ��֯���߼����ٵ��ø��෽��
 * @author weijian.zhongwj
 *
 */
public class CGlibProxy implements MethodInterceptor {
	
	private Object target;
	
	public CGlibProxy(){
	}
	
	public CGlibProxy(Object obj){
		this.target = obj;
	}

    /** 
     * ����������� 
     *  
     * @param target 
     * @return 
     */  
    public Object getInstance() {  
        Enhancer enhancer = new Enhancer();  
        enhancer.setSuperclass(this.target.getClass());  
        enhancer.setCallback(this);
        // �����������  
        return enhancer.create();  
    }
 

    @Override  
    // �ص�����  
    public Object intercept(Object obj, Method method, Object[] args,  
            MethodProxy proxy) throws Throwable {  
        System.out.println("���￪ʼCGLIB");  
        proxy.invokeSuper(obj, args);  
        System.out.println("�������CGLIB");  
        return null;  
    }  
    
	public static void main(String[] args) {
		HelloServiceWithoutInterface helloService = new HelloServiceWithoutInterface();
		CGlibProxy cglibProxy = new CGlibProxy(helloService);
		HelloServiceWithoutInterface helloServiceProxy = (HelloServiceWithoutInterface) cglibProxy.getInstance();
		helloServiceProxy.sayHello();
	}
}
