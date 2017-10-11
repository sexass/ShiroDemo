package demo.shiro.entity;

import javax.persistence.*;

/**
 * role
 *
 * @author 郭友文
 * @create 2017-10-11 10:11
 **/
@Table(name = "shiro_role")
@Entity
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "role_name")
    private String roleName;

    public RoleModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
