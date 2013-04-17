package org.springweb.context.test;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springweb.data.Dataobject;
import org.springweb.data.JdbcTemplateTest;
import org.springweb.data.MyIbatisTest;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * 
 * 1.DAO Data acess object
 *  2. DataAccessException ���ݿ������η�װ�ײ�������ݿ���쳣��Ϣ
 * ͳһ���ݷ����쳣��ϵ 
 * 3. jdbc API JDBCTemplate����װ����jdbc���ʴ��룬ͳһ��ʽ��淶 SimpleJdbcOperations
 *   SimpleJdbcTemplate��NameParameterJdbcTemplate
 * 4. DataSource DriverManagerDataSource SimpleDriverDataSource c3p0 dbcp
 *  4. ���ģʽ��ģ�巽�� 
 *  5. ���ڲ�������ķ��ʷ�ʽ SqlQuery,SqlUpdate,SqlCall,SqlFunction
 * 
 *   RDBMSOperation
 * 
 * 
 * 
 * 6.ORM�� ibatis��hibernate  hibernateTemplate demo
 * 7. JDO java data object ����־û��淶  JPA java�־û�API
 * 8 ��չ FTPClientTemplate HttpClientTemplate
 * 
 * 
 * 
 * @author weijian.zhongwj
 * 
 */
public class SpringContextDAOMain {

	public static void main(String[] args) throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("jdbcbean.xml");
		JdbcTemplateTest jdbcTemplateTest = (JdbcTemplateTest) context.getBean("jdbctest");
//		jdbcTemplateTest.query();
//		jdbcTemplateTest.insert();
//		jdbcTemplateTest.delete();
//		jdbcTemplateTest.insertBatch();
//		jdbcTemplateTest.update();
//		jdbcTemplateTest.updataBatch();
//		jdbcTemplateTest.create_or_dropTable();
//		jdbcTemplateTest.callpreparecall();
		
		//ibatis
		//spring xml�������÷�ʽ
		MyIbatisTest myIbatisTest = (MyIbatisTest) context.getBean("myIbatisTest");
		myIbatisTest.query();
		//ibatis ���뷽ʽ
		SqlMapClient sqlmap = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader("ibatis/mysql-sqlmap-config.xml"));
		SqlMapClientTemplate sqlMapClientTemplate = new SqlMapClientTemplate((DataSource) context.getBean("dbcpDataSource"),sqlmap);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("a", 2);
		List<Dataobject> list = sqlMapClientTemplate.queryForList("test.query", param);
		System.out.println(list);
		
		
		//hibernate
		 //��Spring����������SessionFactory
		ApplicationContext hibernate = new ClassPathXmlApplicationContext("jdbchibernatebean.xml");
        SessionFactory factory =(SessionFactory)hibernate.getBean("sessionFactory");  
        Session session = factory.openSession();  
        //query
        Dataobject data = (Dataobject)session.get(Dataobject.class, 2);  
        System.out.println(data);
        //insert
        Dataobject data1 = new Dataobject();
        data1.setA(14);
        data1.setB("12bbb");
        Serializable s = session.save(data1);
        session.flush();
        System.out.println(s);
        Dataobject data3 = (Dataobject)session.get(Dataobject.class, 12);  
        System.out.println(data3);
        //update
        data1.setA(12);
        data1.setB("123bbb");
        session.update(data1);
        data3 = (Dataobject)session.get(Dataobject.class, 12);  
        System.out.println(data3);
        session.close();  
	}
}
