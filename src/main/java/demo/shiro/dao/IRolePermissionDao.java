package demo.shiro.dao;

import demo.shiro.entity.RoleModel;

import java.util.List;
import java.util.Set;

/**
 * @author 郭友文
 * @create 2017-10-12 9:27
 **/
public interface IRolePermissionDao {
    List<String> getPermissionByRoles(Set<String> roles);
}
