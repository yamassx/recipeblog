package jp.co.aivick.app.service;

import java.util.List;
import jp.co.aivick.app.dao.UserDao;
import jp.co.aivick.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService
{
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findBy(String id) {
        return userDao.find(id);
    }

    public List<User> findAll() {
        return this.userDao.findALl();
    }

    @Transactional
    public User create(User user) {
        User newUser = new User();
        newUser.setLoginId(user.getLoginId());
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.insert(newUser);
        return user;
    }

    @Transactional
    public User update(User user) {
        userDao.update(user);
        return user;
    }
}
