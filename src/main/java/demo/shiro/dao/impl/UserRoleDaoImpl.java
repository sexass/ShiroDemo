package demo.shiro.dao.impl;

import demo.shiro.dao.IUserRoleDao;
import demo.shiro.entity.RoleModel;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author 郭友文
 * @create 2017-10-12 9:41
 **/
@Repository
public class UserRoleDaoImpl implements IUserRoleDao {
    @Autowired
    EntityManager entityManager;

    /**
     * 根据用户名获取角色
     * @param userName
     * @return
     */
    public List<RoleModel> getRolesByUserName(String userName) {
        String sql="select r.id id,r.role_name roleName from shiro_user u,shiro_role r,shiro_user_role ur " +
                "where u.id=ur.user_id and r.id=ur.role_id and u.user_name=:userName";
        Query query=entityManager.createNativeQuery(sql);
        query.setParameter("userName",userName);
        query.unwrap(SQLQuery.class)
                .addScalar("id", StandardBasicTypes.LONG)
                .addScalar("roleName",StandardBasicTypes.STRING)
                .setResultTransformer(Transformers.aliasToBean(RoleModel.class));
        return query.getResultList();
    }
}
