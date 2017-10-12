package demo.shiro.dao;

import demo.shiro.entity.RoleModel;

import java.util.List;

/**
 * @author 郭友文
 * @create 2017-10-12 9:33
 **/
public interface IUserRoleDao {
    /**
     * 根据用户名获取角色
     * @param userName
     * @return
     */
    List<RoleModel> getRolesByUserName(String userName);
}
