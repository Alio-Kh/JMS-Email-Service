package irisi.gamehub.emailservice.ws;

import irisi.gamehub.emailservice.facade.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailRest {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send/{username}/{email}/{link}")
    public void sendMsg(@PathVariable String username, @PathVariable String email, @PathVariable String link) {
        emailService.sendMessage(username, email, link);
    }
}
