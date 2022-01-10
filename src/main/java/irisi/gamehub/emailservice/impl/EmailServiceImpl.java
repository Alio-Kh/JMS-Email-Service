package irisi.gamehub.emailservice.impl;

import irisi.gamehub.emailservice.EmailServiceApplication;
import irisi.gamehub.emailservice.facade.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    TemplateEngine templateEngine;

    public void sendMessage(String username, String email, String link) {
        Map<String, String> actionmap = new HashMap<>();
        actionmap.put("username", username);
        actionmap.put("link", link);
        actionmap.put("email", email);
        System.out.println("actionmap: " + actionmap);
        jmsTemplate.convertAndSend(EmailServiceApplication.EMAIL_QUEUE, actionmap);
    }

    public String sendHtmlEmail(String username, String email, String link) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");


        final Context ctx = new Context();
        ctx.setVariable("link", link);
        ctx.setVariable("username", username);

        final String htmlContent = this.templateEngine.process("classpath:/templates/reset-email.html", ctx);

        message.setContent(htmlContent, "text/html");

        helper.setTo(email);

        helper.setSubject("[GAME HUB] Reset password link");

        this.emailSender.send(message);

        return "Email Sent!";
    }

}
