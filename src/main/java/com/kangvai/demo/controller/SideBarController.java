package com.kangvai.demo.controller;

import com.kangvai.demo.model.UserDto;
import com.kangvai.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SideBarController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserService userService;

    /**
     * 侧边栏-dashboard
     * 网站导航
     */
    @GetMapping({"user/main"})
    public String index() {
        return "user/main";
    }

    /**
     * 侧边栏-news
     * 版权新闻
     */
    @GetMapping({"user/news"})
    public String news(HttpServletRequest request) {
        request.setAttribute("path", "news");
        return "user/news";
    }

    /**
     * 侧边栏-record
     * 个人作品认证
     */
    @GetMapping({"user/record"})
    public String record(HttpServletRequest request) {
        request.setAttribute("path", "record");
        return "user/record";
    }

    /**
     * 侧边栏-trade
     * 版权交易
     */
    @GetMapping({"user/trade"})
    public String trade(HttpServletRequest request) {
        Integer uid = (Integer) request.getSession().getAttribute("uid");
        UserDto userDto = new UserDto();
        userDto.setId(uid);
        userDto = userService.selectOne(userDto);
        request.setAttribute("path", "trade");
        request.setAttribute("uid",uid);
        request.setAttribute("username", userDto.getUsername());
        request.setAttribute("publicKey", userDto.getPublicKey());
        return "user/trade";
    }

    /**
     * 侧边栏-block
     * 显示区块链数据
     */
    @GetMapping({"user/block"})
    public String block(HttpServletRequest request) {
        request.setAttribute("path", "block");
        return "user/block";
    }

    /***
     * 购买记录
     * @params [request]
     * @return java.lang.String
     */
    @GetMapping({"user/work"})
    public String work(HttpServletRequest request) {
        request.setAttribute("path", "work");
        return "user/work";
    }

    /**
     * 侧边栏-profile
     * 修改信息
     */
    @GetMapping({"user/profile"})
    public String profile(HttpServletRequest request) {
        Integer uid = (Integer) request.getSession().getAttribute("uid");
        UserDto userDto = new UserDto();
        userDto.setId(uid);
        userDto = userService.selectOne(userDto);
        if (userDto == null) {
            return "user/login";
        }
        request.setAttribute("path", "profile");
        request.setAttribute("username", userDto.getUsername());
        request.setAttribute("publicKey", userDto.getPublicKey());
        return "user/profile";
    }

    /**
     * 侧边栏-logout
     * 退出登录
     */
    @GetMapping({"user/logout"})
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("uid");
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("publicKey");
        return "user/login";
    }
}
