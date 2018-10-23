package com.xmgps.controller;

import com.xmgps.util.PageData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger("log");

    @Value("${ws.address}")
    private String wsAddress;

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/loginSuccess")
    public ModelAndView loginSuccess(){
        ModelAndView mv = this.getModelAndView();
        String username = (String)SecurityUtils.getSubject().getPrincipal();
        mv.setViewName("main");
        mv.addObject("username",username);
        mv.addObject("wsAddress",wsAddress);
        return mv;
    }

    @RequestMapping("/doLogin")
    public String doLogin(){
        PageData pd = this.getPageData();
        String username = pd.getString("username");
        String password = pd.getString("password");
        try{
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);
            return "redirect:/loginSuccess";
        }catch (AuthenticationException e){
            return "index";
        }
    }

}
