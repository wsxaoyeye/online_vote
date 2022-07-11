package com.two.vote.controller;


import com.two.vote.entity.User;
import com.two.vote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class mysecurityController {

    @Autowired
    private UserService userService;

    /**
     * 欢迎页面
     * @return
     */
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("hasUser")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response){
        String username = (String) request.getSession().getAttribute("username");
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        if (username != null && userid != null){
            User user = userService.queryByName(username);
            Cookie userNameCookie = new Cookie("username",username);
            Cookie userIdCookie = new Cookie("userid",user.getId().toString());
            response.addCookie(userIdCookie);
            response.addCookie(userNameCookie);
            userNameCookie.setPath("/");
            userIdCookie.setPath("/");
            request.getSession().setAttribute("username",username);
            request.getSession().setAttribute("userid",userid);
            request.getSession().setAttribute("user",user);
            model.addAttribute("username",username);
            model.addAttribute("userid",userid);
            return "index";
        }else {
            return "redirect:/";
        }
    }

    /**
     * 登录
     * @return
     */
    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("byUsernameLogin")
    public String byusername(){
        return "byusername";
    }

    /**
     * 注册
     * @return
     */
    @GetMapping("register")
    public String register(){
        return "register";
    }

    @GetMapping("welcome")
    public String welcome(){
        return "welcome";
    }
}
