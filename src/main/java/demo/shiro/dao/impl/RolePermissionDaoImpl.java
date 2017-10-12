package demo.shiro.dao.impl;

import demo.shiro.dao.IRolePermissionDao;
import demo.shiro.entity.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

/**
 * @author 郭友文
 * @create 2017-10-12 9:40
 **/
@Repository
public class RolePermissionDaoImpl implements IRolePermissionDao {
    @Autowired
    EntityManager entityManager;

    public List<String> getPermissionByRoles(Set<String> roles) {
        String sql="select p.permission_name permissionName from shiro_role r,shiro_permission p where r.id=p.role_id and r.role_name in (:roles)";
        Query query=entityManager.createNativeQuery(sql);
        query.setParameter("roles",roles);
        return query.getResultList();
    }
}
