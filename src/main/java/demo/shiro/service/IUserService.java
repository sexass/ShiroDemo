package demo.shiro.service;

import demo.shiro.entity.UserModel;

/**
 * UserService
 *
 * @author 郭友文
 * @create 2017-10-11 15:27
 **/
public interface IUserService {
    UserModel findByUserName(String userName);
}
