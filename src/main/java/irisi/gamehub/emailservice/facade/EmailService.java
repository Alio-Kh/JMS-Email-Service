package irisi.gamehub.emailservice.facade;

import irisi.gamehub.emailservice.entity.User;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {

    public void sendMessage(String username, String email, String link);

    public String sendHtmlEmail(String username, String email,String link) throws MessagingException, IOException;
}
