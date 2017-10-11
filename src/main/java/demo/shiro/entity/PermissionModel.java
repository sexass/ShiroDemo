package demo.shiro.entity;

import javax.persistence.*;

/**
 * permission
 *
 * @author 郭友文
 * @create 2017-10-11 10:13
 **/
@Table(name = "shiro_permission")
@Entity
public class PermissionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "permission_name")
    private String permissionName;
    @Column(name = "role_id")
    private Long roleId;

    public PermissionModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
