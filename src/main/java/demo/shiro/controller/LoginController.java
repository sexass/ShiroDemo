package demo.shiro.controller;

import demo.shiro.entity.UserModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 郭友文
 * @create 2017-10-11 15:42
 **/
@RestController
public class LoginController {

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestBody UserModel user){
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(user.getUserName(),user.getPassword());
        Subject currentUser= SecurityUtils.getSubject();
        try {
            currentUser.login(usernamePasswordToken);
        } catch(UnknownAccountException uae){
            System.out.println("对用户[" + user.getUserName() + "]进行登录验证..验证未通过,未知账户");
        }catch(IncorrectCredentialsException ice){
            System.out.println("对用户[" + user.getUserName() + "]进行登录验证..验证未通过,错误的凭证");
        }catch(LockedAccountException lae){
            System.out.println("对用户[" + user.getUserName() + "]进行登录验证..验证未通过,账户已锁定");
        }catch(ExcessiveAttemptsException eae){
            System.out.println("对用户[" + user.getUserName() + "]进行登录验证..验证未通过,错误次数过多");
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            System.out.println("对用户[" + user.getUserName() + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
        }
        if (currentUser.isAuthenticated()){
            return "success";
        }
        return null;
    }

    /**
     * 测试角色权限（要求角色admin）
     */
    @RequestMapping(value = "/testRole",method = RequestMethod.GET)
    @RequiresRoles("admin")
    public String testRole(){
        return "test role success!";
    }

    /**
     * 测试详细权限（要求权限user:view）
     */
    @RequestMapping(value = "/testPermission",method = RequestMethod.GET)
    @RequiresPermissions("user:view")
    public String testPermission(){
        return "test permission success!";
    }

    /**
     * 登出
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "logout success!";
    }

    /**
     * 无权限链接
     */
    @RequestMapping("/403")
    public String unauthorizedRole(){
        return "没有访问权限";
    }

    /**
     * 不拦截链接
     */
    @RequestMapping("/helloWorld")
    public String helloWorld(){
        return "hello world!";
    }
}
