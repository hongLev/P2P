package com.nongye.p2p.service.Impl;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nongye.p2p.domain.Mailverify;
import com.nongye.p2p.mapper.MailVerifyMapper;
import com.nongye.p2p.service.MailVerifyService;

@Service
public class MailVerifyServiceImpl implements MailVerifyService {
	
	@Autowired
	private MailVerifyMapper mailVerifyMapper;
	
	@Value("${mail.smtp}")
	private String smtp;
	@Value("${mail.host}")
	private String mailHost;
	@Value("${mail.Address}")
	private String mailAddress;
	@Value("${mail.account}")
	private String mailAccount;
	@Value("${mail.pwd}")
	private String mailPwd;

	
	@Override
	public void addMailVerify(Mailverify mv) {
		// TODO Auto-generated method stub
		//添加
		mailVerifyMapper.insert(mv);
	}

	@Override
	public void sendEmail(String email, String Subject, String content) {
		// TODO Auto-generated method stub
		try {
			//1.设置一些必要的协议，远程邮件服务器地址
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", smtp);
			props.setProperty("mail.host", mailHost);
			//2.根据第一步设置的相关邮件发送的信息，得到一个Session对象（好比连数据Connection）
			Session session = Session.getInstance(props);
			session.setDebug(true);//用于查看底层发送的邮件的相关代码
			//3.得到一个Message对象，Message对象就是要发送的消息
			MimeMessage msg = new MimeMessage(session);
			//3.1发送者
			msg.setFrom(new InternetAddress(mailAddress));
			//3.2接收者 :发送TO  抄送CC，密送BCC  
			msg.setRecipients(RecipientType.TO, email);
			//3.3主题
			msg.setSubject(Subject);
			//3.4正文
			msg.setText(content);
			//4.创建一个邮件发送对象
			Transport tp = session.getTransport();  //(火箭)
			//4.1邮件发送对象（设置登录远程邮件服务器的账号，授权码）
			tp.connect(mailAccount, mailPwd);
			//4.2发送
			tp.sendMessage(msg, msg.getAllRecipients());
			//4.3邮件发送对象进行关闭
			tp.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("发送邮件失败!");
		}		

	}

}
