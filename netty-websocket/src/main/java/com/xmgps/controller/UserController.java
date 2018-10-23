package com.xmgps.controller;

import com.xmgps.service.UserService;
import com.xmgps.util.PageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/userController")
public class UserController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger("log");

    @Resource(name = "userService")
    private UserService userService;


}
