package com.xmgps.config.shiro;

import com.xmgps.entity.User;
import com.xmgps.service.UserService;
import com.xmgps.util.PageData;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class MyShiroRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger("log");

    private static final String USER_NAME = "admin";
    private static final String PASSWORD = "123456";

    @Resource(name = "userService")
    private UserService userService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //用户授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        //查询用户角色以及权限
        List<Map<String,Object>> menuList = null;
        try {
            menuList = userService.queryMenuAuthByUserName(user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }


//        // 授权
//        if(list!=null){
//            for (AuthFunction authFunction : list) {
//                info.addStringPermission(authFunction.getCode());
//            }
//        }
        return info;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取基于用户名和密码的令牌
        //实际上这个authenticationToken是从LoginController里面currentUser.login(token)传过来的
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String user_name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        PageData pdParam = new PageData();
        pdParam.put("username",user_name);
        pdParam.put("password",password);

        PageData pdResult = null;
        try {
            pdResult = userService.checkUser(pdParam);
        } catch (Exception e) {
            logger.error("错误：" + e);
            e.printStackTrace();
        }

        if(pdResult != null){
            return new SimpleAuthenticationInfo(user_name, password, this.getName());
        }else {
            logger.error("用户名或密码错误！");
            throw new AuthenticationException("用户名或密码错误！");
        }
    }
}
