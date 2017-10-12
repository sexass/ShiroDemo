package demo.shiro.service;

import demo.shiro.dao.IUserDao;
import demo.shiro.dao.IUserRoleDao;
import demo.shiro.entity.RoleModel;
import demo.shiro.entity.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 郭友文
 * @create 2017-10-11 15:28
 **/
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IUserRoleDao userRoleDao;

    public UserModel findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    public List<RoleModel> getRolesByUserName(String userName) {
        return userRoleDao.getRolesByUserName(userName);
    }
}
