package demo.shiro.conf;

import demo.shiro.shiro.MyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * shiro配置
 *
 * @author 郭友文
 * @create 2017-10-11 10:30
 **/
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager,ShiroFileterConfig shiroFileterConfig){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //配置前端登录页
        shiroFilterFactoryBean.setLoginUrl(shiroFileterConfig.getLoginUrl());

        //没有访问权限
        shiroFilterFactoryBean.setUnauthorizedUrl(shiroFileterConfig.getUnauthorizedUrl());
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();

        String filterString=shiroFileterConfig.getFilterString();
        //在配置文件中配置过滤器，方便后期修改
        for(String keyValue:filterString.split("\\;")){
            String keyTemp=keyValue.split("\\=")[0];
            String valueTemp=keyValue.split("\\=")[1];
            filterChainDefinitionMap.put(keyTemp,valueTemp);
        }
//        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
//        filterChainDefinitionMap.put("/logout", "logout");
//
//        //过滤链定义，从上向下顺序执行，一般将 /**放在最为下边;
//        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
//        filterChainDefinitionMap.put("/403","anon");
//        filterChainDefinitionMap.put("/helloWorld","anon");
//        filterChainDefinitionMap.put("/**", "authc");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(MyRealm myRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm);
        return manager;
    }

    @Bean
    public MyRealm myRealm(){
        return new MyRealm();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 使用shiro注解需要的bean
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 使用shiro注解需要的bean
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 处理无访问权限时无法跳转403
     */
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(ShiroFileterConfig shiroFileterConfig){
        SimpleMappingExceptionResolver simpleMappingExceptionResolver=new SimpleMappingExceptionResolver();
        Properties properties=new Properties();
        properties.setProperty("org.apache.shiro.authz.UnauthorizedException",shiroFileterConfig.getUnauthorizedUrl());
        simpleMappingExceptionResolver.setExceptionMappings(properties);
        return simpleMappingExceptionResolver;
    }
}
