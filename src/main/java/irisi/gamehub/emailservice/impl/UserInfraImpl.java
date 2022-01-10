package irisi.gamehub.emailservice.impl;

import irisi.gamehub.emailservice.dao.UserDao;
import irisi.gamehub.emailservice.entity.User;
import irisi.gamehub.emailservice.facade.UserInfra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfraImpl implements UserInfra {

    @Autowired
    UserDao userDao;

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
