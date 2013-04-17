package org.springweb.context.test;

import java.util.Date;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springweb.service.HelloService;
import org.springweb.service.RemoteReadService;

/**
 * spring��һ�ο�
 * 0.web����������eclipse j2ee,jetty �����tomcat���
 * spring���������POJO����
 * 1.IOC ������ã��ŵ� Donnot call me,we will call you.  beanfactory��applicationcontext���¼����������ʻ�֧�֣�
 * 2.ע�뷽�� setter��construtor������̫�࣬�ɱ�����б����ӿ�ע��  
 * 3.ע������ bean���������ͣ�map��list��
 * 4.ע�뷽ʽ byname��bytype��default
 * 5.annotation��autowired,resource,@Qualifier��
 * 6.bean���������� init-method,destroy-method,initiableBean, DisposableBean,lasyinit��singleton��prototype @PostConstruct @PreDestroy
 * 7.postProcessBean
 * 8.IOC Ӧ�ó�����datasouce���ã��̳߳����ã�
 * 9.���������� FactoryBean
 * 
 * @author weijian.zhongwj
 *
 */

public class SpringContextIOCMain {

	public static void main(String[] args) {
		//���뷽ʽ��xml���÷�ʽ��properties��ʽ
		//FileSystemXmlApplicationContext ClassPathXmlApplicationContext XmlWebApplicationContext
		ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
		HelloService helloSerice = (HelloService) context.getBean("helloService");
		helloSerice.sayHello();
		
		HelloService helloSerice1 = (HelloService) context.getBean("helloService");
		helloSerice1.sayHello();
		
		
		//remoteReadServicepostProcessor
//		RemoteReadService remotePostProcess = (RemoteReadService) context.getBean("remoteReadServicepostProcessor");
//		System.out.println(remotePostProcess.getCon());
//		
		//messageSource
//		MessageSource messageSource = (MessageSource) context.getBean("messageSource");
//		System.out.println(messageSource.getMessage("loginfail", new Object[]{"a","b"}, Locale.ENGLISH));
//		System.out.println(messageSource.getMessage("loginfail", new Object[]{new Date().toString()}, Locale.getDefault()));
	}
}
