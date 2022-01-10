package irisi.gamehub.emailservice.facade;

import irisi.gamehub.emailservice.entity.User;

public interface UserInfra {
    User findByUsername(String username);
}
