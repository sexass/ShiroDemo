package demo.shiro.shiro;

import demo.shiro.entity.RoleModel;
import demo.shiro.entity.UserModel;
import demo.shiro.service.IPermissionService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import demo.shiro.service.IUserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * realm
 *
 * @author 郭友文
 * @create 2017-10-11 15:04
 **/
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;
    @Autowired
    private IPermissionService permissionService;

    /**
     * 权限验证
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserModel landingUser= (UserModel) principalCollection.getPrimaryPrincipal();
        if(landingUser!=null){
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            List<RoleModel> roleList=userService.getRolesByUserName(landingUser.getUserName());
            Set<String> roles=new HashSet<String>();
            for(RoleModel r:roleList){
                roles.add(r.getRoleName());
            }
            if(roles.size()!=0) {
                info.setRoles(roles);
                List<String> permissions = permissionService.getPermissionByRole(roles);
                Set<String> permissionSet = new HashSet<String>();
                permissionSet.addAll(permissions);
                if (permissionSet.size() != 0) {
                    info.setStringPermissions(permissionSet);
                }
            }
            return info;
        }
        return null;
    }

    /**
     * 身份验证
     * @param authenticationToken
     * @return
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName= (String) authenticationToken.getPrincipal();
        UserModel user=userService.findByUserName(userName);
        if(user==null){
            return null;
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
    }
}
