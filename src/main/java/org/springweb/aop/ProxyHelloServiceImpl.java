package org.springweb.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springweb.service.HelloService;

/**
 * ��̬����
 * ����ģʽʵ�֣������ڷ�������ǰ������Լ���ҵ���߼���������๦�ܣ�����Ӱ�����ĵ��á�
 * 
 * ��Ҫʵ�ָ�Ŀ�����ͬ���Ľӿڣ�����������нӿڵ�ʵ�ֵĴ����������ÿ��service��Ҫ��ô����������ܴ󣬲��ù���ά��
 * 
 * @author weijian.zhongwj
 *
 */
public class ProxyHelloServiceImpl implements HelloService {

	@Autowired
	private HelloService helloService;

	public ProxyHelloServiceImpl(HelloService helloService){
		this.helloService = helloService;
	}
	
	@Override
	public void sayHello() {
		System.out.println("before method sayhello call");
		helloService.sayHello();
		System.out.println("after method sayhello call");
	}


}
