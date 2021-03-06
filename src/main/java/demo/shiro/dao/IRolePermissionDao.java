package demo.shiro.dao;

import demo.shiro.entity.RoleModel;

import java.util.List;
import java.util.Set;

/**
 * @author 郭友文
 * @create 2017-10-12 9:27
 **/
public interface IRolePermissionDao {
    /**
     * 根据角色获取权限
     * @param roles
     * @return
     */
    List<String> getPermissionByRoles(Set<String> roles);
}
