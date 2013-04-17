package org.springweb.context.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springweb.aop.aspectj.NextService;
import org.springweb.aop.aspectj.UserService;

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
 * 
 * 13. aspectj annotation
 * @author weijian.zhongwj
 *
 */

public class AspectjSpringContextAOPMain {

	public static void main(String[] args) {

		
		//proxyFactoryBean FactoryBean��proxy����ʵ��  xml����AOP
		ApplicationContext context = new ClassPathXmlApplicationContext("aopbeanaspectJ.xml");
		UserService service =(UserService) context.getBean("userService");  
        service.print();  
		
        NextService NextService =(NextService) context.getBean("NextService");  
        NextService.print();  
	}
}

