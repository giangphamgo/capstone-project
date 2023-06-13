package vn.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vn.shop.dto.MailInfo;
import vn.shop.service.SendMailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DongTHD
 *
 */
@Service
public class SendMailServiceImplement implements SendMailService {
	@Autowired
	JavaMailSender sender;



	List<MailInfo> list = new ArrayList<>();



	@Override
	public void sendNoAttachment(String to,String subject,String body) throws MessagingException {
		// Tạo message
		MimeMessage message = sender.createMimeMessage();
		// Sử dụng Helper để thiết lập các thông tin cần thiết cho message
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		String mailFrom="thanhtung3122000@gmail.com";
		helper.setFrom(mailFrom);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body, true);
		helper.setReplyTo(mailFrom);
//		helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));


		// Gửi message đến SMTP server
		sender.send(message);

	}


	@Override
	public void sendAttachments(String to,String subject,String body , String attachments) throws MessagingException {
		// Tạo message
		MimeMessage message = sender.createMimeMessage();
		// Sử dụng Helper để thiết lập các thông tin cần thiết cho message
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		String mailFrom="thanhtung3122000@gmail.com";
		helper.setFrom(mailFrom);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body, true);
		helper.setReplyTo(mailFrom);
//		helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

		FileSystemResource file = new FileSystemResource(new File(attachments));
		helper.addAttachment( attachments, file);

		// Gửi message đến SMTP server
		sender.send(message);

	}



	@Override
	public void queue(MailInfo mail) {
		list.add(mail);
	}



	@Override
	public void queue(String to, String subject, String body) {
		queue(new MailInfo(to, subject, body));
	}

	@Override
	@Scheduled(fixedDelay = 5000)
	public void run() throws MessagingException {
//		while (!list.isEmpty()) {
//			MailInfo mail = list.remove(0);
//
//				this.send(mail);
//
//		}
	}
}
