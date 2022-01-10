package irisi.gamehub.emailservice.listner;


import irisi.gamehub.emailservice.EmailServiceApplication;
import irisi.gamehub.emailservice.facade.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

@Component
public class Listener {

    private static final Logger log = LogManager.getLogger(Listener.class);

    @Autowired
    EmailService emailService;

    /**
     * This method is invoked whenever any new message is put in the queue.
     * See {@link EmailServiceApplication} for more details
     *
     * @param message
     */
    @JmsListener(destination = EmailServiceApplication.EMAIL_QUEUE, containerFactory = "jmsFactory")
    public void receiveMessage(Map<String, String> message) throws MessagingException, IOException {
        log.info("Received <" + message + ">");
        String username = message.get("username");
        String link = message.get("link");
        String email = message.get("email");

        System.out.println("Username2: " + username);
        System.out.println("Link2: " + link);
        System.out.println("email: " + email);
        log.info("Message processed...");

        // Send email
        emailService.sendHtmlEmail(username, email, link);
        log.info("Email sent!...");
    }
}
