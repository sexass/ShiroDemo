package demo.shiro.service;

import demo.shiro.entity.RoleModel;

import java.util.List;
import java.util.Set;

/**
 * @author 郭友文
 * @create 2017-10-12 10:22
 **/
public interface IPermissionService {
    /**
     * 根据角色获取权限
     * @param roles
     * @return
     */
    List<String> getPermissionByRole(Set<String> roles);
}
