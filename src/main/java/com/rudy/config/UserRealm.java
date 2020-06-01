package com.rudy.config;


import com.rudy.entity.MyUserInfo;
import com.rudy.service.UserService;
import com.rudy.util.ShiroEncryption;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /*
          此处是授权代码
          授权内容还没写！！！！！！！！！
         */
        System.out.println("进入了授权处理");
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取传入的token
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        //通过用户名查数据库返回用户对象
        MyUserInfo user = userService.selectAllUserByName(userToken.getUsername());
        if (user == null){//如果为空则返回null，并且在控制层中捕捉异常
            return null;
        }
        if(user.getStatus() != '0'){
            throw new LockedAccountException();
        }

        if (user.getDelFlag() != '0'){
            throw new AuthenticationException();
        }
        //给认证后的用户添加会话，方便前端页面的动态展示
        Subject currentSubject = SecurityUtils.getSubject();//先获取当前对象
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",user);

        //验证密码的同时返回简单的认证信息，该信息可被SecurityUtils类获得
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
