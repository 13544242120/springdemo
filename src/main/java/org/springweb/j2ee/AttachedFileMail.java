package org.springweb.j2ee;


import java.io.File;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class AttachedFileMail
{
	/**
	 * ������Ե��ǹ����ʼ��д��и���������
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

		// �趨mail server
		senderImpl.setHost("smtp.163.com");
		// �����ʼ���Ϣ,���ͼ��ʼ���html�ʼ�������
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		// ע�������boolean,�������ʱ�����Ƕ��ͼƬ���ڹ���MimeMessageHelperʱ����������ֵ��true��ʾ���ã�
		// multipartģʽ Ϊtrueʱ���͸��� ��������html��ʽ
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,
				true, "utf-8");

		// �����ռ��ˣ��ļ���
		messageHelper.setTo("toMail@sina.com");
		messageHelper.setFrom("username@163.com");
		messageHelper.setSubject("�����ʼ����ϴ�����!��");
		// true ��ʾ����HTML��ʽ���ʼ�
		messageHelper.setText(
				"<html><head></head><body><h1>��ã���������ѧϰ���ϣ�</h1></body></html>",
				true);

		FileSystemResource file = new FileSystemResource(
				new File("g:/test.rar"));
		// ����ķ������úͲ���ͼƬ�ǲ�ͬ�ġ�
		messageHelper.addAttachment("test.rar", file);

		senderImpl.setUsername("username"); // �����Լ������,����username
		senderImpl.setPassword("password"); // �����Լ������, ����password
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true"); // �����������Ϊtrue���÷�����������֤,��֤�û����������Ƿ���ȷ
		prop.put("mail.smtp.timeout", "25000");
		senderImpl.setJavaMailProperties(prop);
		// �����ʼ�
		senderImpl.send(mailMessage);

		System.out.println("�ʼ����ͳɹ�..");
	}
}