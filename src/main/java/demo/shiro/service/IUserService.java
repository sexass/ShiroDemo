package demo.shiro.service;

import demo.shiro.entity.RoleModel;
import demo.shiro.entity.UserModel;

import java.util.List;

/**
 * UserService
 *
 * @author 郭友文
 * @create 2017-10-11 15:27
 **/
public interface IUserService {
    UserModel findByUserName(String userName);

    List<RoleModel> getRolesByUserName(String userName);
}
