package com.yinrong.tools.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 邮件发送工具类
 * @author songzhiqiang
 * @version 1.0 2015年4月2日 下午5:15:24
 */
public class EmailUtil {
	private MimeMessage mineMessage; // MIME邮件对象
	private Session session; // 邮件会话对象
	private Properties props; // 系统属性
	private String userName; // smtp认证用户名和密码
	private String password;
	private Multipart multipart; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象

	/**
	 * 设置邮件SMTP主机
	 * 
	 * @param hostName
	 */
	public void setSmtpHost(String hostName) {
		if (props == null) {
			props = System.getProperties(); // 获得系统属性对象
		}
		props.put("mail.smtp.host", hostName); // 设置SMTP主机
	}

	/**
	 * 创建MimeMessage
	 */
	public void createMimeMessage() {
		try {
			session = Session.getDefaultInstance(props, null); // 获得邮件会话对象
		} catch (Exception e) {
		}
		try {
			mineMessage = new MimeMessage(session); // 创建MIME邮件对象
			multipart = new MimeMultipart();
		} catch (Exception e) {
		}

	}

	/**
	 * 设置是否需要验证.
	 * 
	 * @param need
	 */

	public void setNeedAuth(boolean need) {
		if (props == null) {
			props = System.getProperties();
		}
		if (need) {
			props.put("mail.smtp.auth", "true");
		} else {
			props.put("mail.smtp.auth", "false");
		}

	}

	/**
	 * 设置邮件的主题
	 * 
	 * @param mailSubject
	 */

	public void setSubject(String mailSubject) {
		try {
			mineMessage.setSubject(mailSubject);
		} catch (Exception e) {
		}

	}

	/**
	 * 设置邮件的内容
	 * 
	 * @param mailBody
	 */

	public void setBody(String mailBody) {
		try {
			BodyPart bp = new MimeBodyPart();
			bp.setContent("" + mailBody, "text/html;charset=GB2312");
			multipart.addBodyPart(bp);
		} catch (Exception e) {
			System.err.println("设置邮件正文时发生错误！" + e);
		}

	}

	/**
	 * 设置邮件发送人
	 * 
	 * @param name
	 */
	public void setFrom(String from) {
		try {
			mineMessage.setFrom(new InternetAddress(from)); // 设置发信人
		} catch (Exception e) {
		}

	}

	/**
	 * 发送e_mail
	 * 
	 * @return int 当返回值为0时，说明邮件发送成功 ;当返回值为3时，说明邮件发送失败
	 */

	public int sendMail() throws IOException, MessagingException {
		mineMessage.setContent(multipart);
		mineMessage.saveChanges();
		Session mailSession = Session.getInstance(props, null);
		Transport transport = mailSession.getTransport("smtp");
		try {
			try {
				transport.connect((String) props.get("mail.smtp.host"), userName, password);
			} catch (Exception e) {
				System.out.println("连接邮件服务器错误：");
				return 3;
			}
			transport.sendMessage(mineMessage, mineMessage.getRecipients(Message.RecipientType.TO));
			transport.close();
		} catch (MessagingException mex) {
			System.out.println("发送邮件失败：");
			mex.printStackTrace();
			Exception ex = null;
			if ((ex = mex.getNextException()) != null) {
				System.out.println(ex.toString());
				ex.printStackTrace();
			}
			return 3;
		} finally {
			try {
				if (transport != null && transport.isConnected()) {
					transport.close();
				}

			} catch (Exception e) {
				System.out.println(e.toString());
			}

		}
		return 0;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Multipart getMultipart() {
		return multipart;
	}

	public void setMultipart(Multipart multipart) {
		this.multipart = multipart;
	}

	/**
	 * 
	 * @param name
	 *            String
	 * 
	 * @param pass
	 *            String
	 */

	public boolean setTo(String to) {
		if (to == null)
			return false;

		try {
			mineMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * 
	 * Just do it as this
	 */

	public static void main(String[] args) throws Exception {
		String str = "Host:" + InetAddress.getLocalHost() + "<br>";
		Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
		Enumeration<InetAddress> addresses;
		while (en.hasMoreElements()) {
			NetworkInterface networkinterface = en.nextElement();
			str += networkinterface.getName() + "<br>";
			addresses = networkinterface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				str += addresses.nextElement().getHostAddress() + "<br>";
			}
		}
		String mailbody = str;
		EmailUtil themail = new EmailUtil();
		themail.createMimeMessage();
		themail.setNeedAuth(true);
		themail.setSmtpHost("smtp.qq.com");
		themail.setSubject("测试");
		themail.setBody(mailbody);
		themail.setTo("253142031@qq.com");
		themail.setFrom("253142031@qq.com");
		themail.setUserName("253142031@qq.com");
		themail.setPassword("");
		System.out.println(themail.sendMail() + "===============");

	}
}
