package com.rudy.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //获取过滤器工厂ShiroFilterFactory
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        Map<String,String> filterMap = new LinkedHashMap<>();
        /*
           设置Shiro中内置的过滤器：
           anon：无需认证就访问
           authc：必须要认证才能访问
           user：必须拥有记住我功能才能访问
           perms：拥有某个资源的权限才能访问
           role：拥有某个角色权限才能访问
         */
        filterMap.put("/insert","authc");
        filterMap.put("/","anon");
        filterMap.put("/login","anon");
        filterMap.put("/toLogin","anon");
        filterMap.put("/toIndex","anon");
        filterMap.put("/*","user");      //注意这里，这里的意思是除了上面的页面，其他页面只要是认证了或“记住我”了都可以访问

        //注入过滤器
        bean.setFilterChainDefinitionMap(filterMap);
        //设置登录请求
        bean.setLoginUrl("/toLogin");
        return bean;
    }

    //获取默认安全管理者DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm realm){
        return new DefaultWebSecurityManager(realm);
    }

    //创建Realm对象，需要自定义
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
