package demo.shiro.service;

import demo.shiro.dao.IRolePermissionDao;
import demo.shiro.entity.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author 郭友文
 * @create 2017-10-12 10:23
 **/
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IRolePermissionDao rolePermissionDao;

    /**
     * 根据角色获取权限
     * @param roles
     * @return
     */
    public List<String> getPermissionByRole(Set<String> roles) {
        return rolePermissionDao.getPermissionByRoles(roles);
    }
}
