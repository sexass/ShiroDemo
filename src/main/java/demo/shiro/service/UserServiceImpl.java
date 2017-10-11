package demo.shiro.service;

import demo.shiro.dao.IUserDao;
import demo.shiro.entity.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 郭友文
 * @create 2017-10-11 15:28
 **/
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    public UserModel findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }
}
