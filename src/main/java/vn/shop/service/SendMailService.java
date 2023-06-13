package vn.shop.service;

import org.springframework.stereotype.Service;
import vn.shop.dto.MailInfo;

import javax.mail.MessagingException;

/**
 * @author DongTHD
 *
 */
@Service
public interface SendMailService {
	void run() throws MessagingException;

	void queue(String to, String subject, String body);

	void queue(MailInfo mail);


	void sendNoAttachment(String to,String subject,String body) throws MessagingException;


	void sendAttachments(String email, String đăng_kí_tài_khoản, String body,String attachments) throws MessagingException;
}
