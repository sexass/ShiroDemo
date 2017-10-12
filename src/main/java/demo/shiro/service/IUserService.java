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
    /**
     * 根据用户名寻找用户
     * @param userName
     * @return
     */
    UserModel findByUserName(String userName);

    /**
     * 根据用户名获取角色
     * @param userName
     * @return
     */
    List<RoleModel> getRolesByUserName(String userName);
}
