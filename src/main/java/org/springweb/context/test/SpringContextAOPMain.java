package org.springweb.context.test;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springweb.aop.HelloServiceImpl;
import org.springweb.aop.HelloServiceWithoutInterface;
import org.springweb.aop.ProxyFactoryProxyAdvisor;
import org.springweb.aop.SpringHelloServiceImpl;
import org.springweb.service.HelloService;

/**
 * spring�ڶ��ο�
 * 0.web����������eclipse j2ee,jetty �����tomcat���
 * spring���������POJO����
 * 1.AOP���Ӧ�ó�������ȫ��飬��־��¼����أ��������
 * 2.ʵ�ַ�ʽ����̬��������ʵ�ֺ��й�ע����߼�����ʵ��InvokationHandler�ӿڣ�����̬�ֽ�����ǿ���������࣬�������߼��ӵ������У�����Ҫʵ�ֽӿڣ����Ǹ��಻��Ϊfinal����
 *          spring Ĭ���ö�̬�������������ֽ��루ASM��CGLIB����ǿ
 * 3. joinpoint(�����),pointcut���������ʽ�����򣬷������ȣ���advice��before advice��after advice��after return advice��
 *     after throwing advice, after finally advice,����around advice(filter)����spring AOP ʹ��proxyfactory���֯�빤����
 * 4. ʵ��ԭ������ģʽ�� ��̬����ʾ����cglibʾ��
 * 5. PointCut��JdkRegexpMethodPointcut��NameMatchMethodPointcut
 * 6. advice AfterAdvice BeforeAdvice AspectJAroundAdvice ThrowsAdvice AfterReturningAdvice(��������״̬�������ݿ⣩
 * 7. advisor Advisor DefaultPointcutAdvisor order
 * 9. ���� AspjectJ ajc������������ʱ֯�룻JBoss AOPʹ���Զ����classloader��spring aopʹ��ProxyFactory  ProxyFactory������Ĵ���  AOPProxy AOPProxyFactory
 
 * 10. proxyFactoryBean
 * 11. �Զ����� ����BeanPostProcessor�� BeanNameAutoProxyCreator��DefaultAdvisorAutoProxyCreator�����advisor���Զ�ɨ��xml�����е�advisor�������ɴ�����������Զ�����ʵ��	
 * 12. TargetSource HotSwappableTargetSource��ʵ������Դ�滻���ȱ������� PrototypeTargetSource SingletonTargetSource
 * 
 * 13. aspectj annotation
 * @author weijian.zhongwj
 *
 */

public class SpringContextAOPMain {

	public static void main(String[] args) {
		//���뷽ʽ��xml���÷�ʽ��properties��ʽ
		//FileSystemXmlApplicationContext ClassPathXmlApplicationContext XmlWebApplicationContext
//		ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
//		HelloService helloSerice = (HelloService) context.getBean("helloService");
//		helloSerice.sayHello();
//		
		
		//����proxyFactory �Ļ��ڽӿڴ���ʵ��
		ProxyFactory weaver = new ProxyFactory(new SpringHelloServiceImpl());  
//		weaver.setInterfaces(interfaces);
//		weaver.setExposeProxy(true);
		NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();  
		advisor.setMappedName("sayHello");  
		advisor.setAdvice(new ProxyFactoryProxyAdvisor());  
		weaver.addAdvisor(advisor);  
		HelloService proxyObject =(HelloService)weaver.getProxy();  
		proxyObject.sayHello();
		System.out.println(proxyObject.getClass()); 
		
		//����proxyFactory�Ļ�����Ĵ���ʵ��
		ProxyFactory weaver1 = new ProxyFactory(new HelloServiceWithoutInterface());  
//		weaver.setInterfaces(interfaces);
		NameMatchMethodPointcutAdvisor advisor1 = new NameMatchMethodPointcutAdvisor();  
		advisor1.setMappedName("sayHello");  
		advisor1.setAdvice(new ProxyFactoryProxyAdvisor());  
		weaver1.addAdvisor(advisor1);  
		HelloServiceWithoutInterface proxyObject1 =(HelloServiceWithoutInterface)weaver1.getProxy();  
		proxyObject1.sayHello();
		System.out.println(proxyObject1.getClass()); 
		
		//Ĭ�ϻ��ڽӿڵĴ�������ͨ��������������ǿ�ƻ�����Ĵ���
		weaver.setProxyTargetClass(true);
		SpringHelloServiceImpl proxyObject2 =(SpringHelloServiceImpl)weaver.getProxy();  
		proxyObject2.sayHello();
		System.out.println(proxyObject2.getClass()); 
		
		//proxyFactoryBean FactoryBean��proxy����ʵ��  xml����AOP
		ApplicationContext context = new ClassPathXmlApplicationContext("aopbean.xml");
		HelloService helloService = (HelloService) context.getBean("myproxefactorybean");
		helloService.sayHello();
		
		HelloServiceImpl HelloServiceImpl = (HelloServiceImpl) context.getBean("myproxefactorybean");
		HelloServiceImpl.sayHello();
		
		//�ԄӴ��팍�F
		HelloService autoProxyHelloService = (HelloService) context.getBean("autoproxyhelloService");
		autoProxyHelloService.sayHello();
		
	}
}

