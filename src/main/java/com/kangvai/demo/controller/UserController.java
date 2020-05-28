package com.kangvai.demo.controller;

import com.kangvai.demo.anno.MailAnno;
import com.kangvai.demo.model.UserDto;
import com.kangvai.demo.service.UserService;
import com.kangvai.demo.util.MD5Util;
import com.kangvai.demo.util.Result;
import com.kangvai.demo.util.ResultGenerator;
import com.kangvai.demo.util.VeDate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 用户
 * @author kangvai
 * @date 2020/4/30 14:48
 */
@Controller
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping({"/","user/index"})
    public String index() {
        return "user/index";
    }

    @GetMapping({"user/login"})
    public String login() {return "user/login"; }

    @GetMapping({"user/register"})
    public String register() {return "user/register"; }

    @PostMapping(value = "user/profile/password")
    @ResponseBody
    public Result passwordUpdate(HttpServletRequest request, @RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword) {
        String passwordMD5 = MD5Util.encrypt(newPassword);
        Integer uid = (Integer) request.getSession().getAttribute("uid");
        UserDto userDto = new UserDto();
        userDto.setId(uid);
        userDto = userService.selectOne(userDto);
        if(userDto == null) {
            return ResultGenerator.genFailResult("修改失败");
        }
        if(userDto.getPassword().equals(passwordMD5)) {
            return ResultGenerator.genFailResult("修改失败");
        } else {
            userDto.setPassword(passwordMD5);
            if(userService.updateByPrimaryKeySelective(userDto) > 0) {
                request.getSession().removeAttribute("uid");
                request.getSession().removeAttribute("username");
                request.getSession().removeAttribute("publicKey");
                return ResultGenerator.genSuccessResult("修改成功");
            } else {
                return ResultGenerator.genFailResult("修改失败");
            }
        }
    }

    /**
     * 修改公钥
     * @params [request, newPublicKey]
     * @return java.lang.String
     */
    @PostMapping(value = "user/profile/public-key")
    @ResponseBody
    public Result personalUpdate(HttpServletRequest request, @RequestParam("newPublicKey") String newPublicKey) {
        Integer uid = (int) request.getSession().getAttribute("uid");
        UserDto userDto = new UserDto();
        userDto.setId(uid);
        if(userService.selectOne(userDto) == null) {
            return ResultGenerator.genFailResult("修改失败");
        }
        userDto.setPublicKey(newPublicKey);
        if (userService.updateByPrimaryKeySelective(userDto) > 0) {
            return ResultGenerator.genSuccessResult("修改成功");
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

    /**
     * 用户名修改
     * @params [request, newUserName]
     * @return java.lang.String
     */
    @PostMapping(value = "user/profile/name")
    @ResponseBody
    public Result basicUpdate(HttpServletRequest request, @RequestParam("newUserName") String newUserName) {
        Integer uid = (Integer) request.getSession().getAttribute("uid");
        UserDto userDto = new UserDto();
        userDto.setId(uid);
        if(userService.selectOne(userDto) == null) {
            return ResultGenerator.genFailResult("修改失败");
            //return "修改失败";
        }
        userDto.setUsername(newUserName);
        if (userService.updateByPrimaryKeySelective(userDto) > 0) {
            return ResultGenerator.genSuccessResult("修改成功");
            //return "success";
        } else {
            return ResultGenerator.genFailResult("修改失败");
            //return "修改失败";
        }
    }

    /**
     * 用户登录
     * @params [userName, password]
     * @return java.lang.String
     */
    @PostMapping(value = "user/login")
    @ResponseBody
    public Result login(@RequestParam("account") String account,
                        @RequestParam("password") String password,
                        HttpSession session) {
        String passwordMD5 = MD5Util.encrypt(password);
        UserDto userDtoTmp = new UserDto();
        userDtoTmp.setAccount(account);
        userDtoTmp = userService.selectOne(userDtoTmp);
        if (userDtoTmp == null) {
            return ResultGenerator.genFailResult("登录失败");
        }
        if (userDtoTmp.getPassword().equals(passwordMD5)){
            session.setAttribute("uid",userDtoTmp.getId());
            session.setAttribute("username",userDtoTmp.getUsername());
            session.setAttribute("publicKey",userDtoTmp.getPublicKey());
            return ResultGenerator.genSuccessResult("登录成功");
        }else {
            return ResultGenerator.genFailResult("登录失败");
        }
    }

    /**
     * 用户注册
     * @params [account, password, username, publicKey]
     * @return java.lang.String
     */
    @MailAnno(value = "发送邮件")
    @PostMapping(value = "user/register")
    @ResponseBody
    public Result register(@RequestParam("account") String account,
                           @RequestParam("password") String password,
                           @RequestParam("username") String username,
                           @RequestParam("publicKey") String publicKey) {
        String passwordMD5 = MD5Util.encrypt(password);
        UserDto userDtoTmp = new UserDto();
        userDtoTmp.setAccount(account);
        userDtoTmp = userService.selectOne(userDtoTmp);
        if(userDtoTmp != null) {
            return ResultGenerator.genFailResult("注册失败");
        }
        UserDto userDto = new UserDto();
        String timeStamp = VeDate.getStringDate();
        Date currentDate = VeDate.strToDateLong(timeStamp);
        userDto.setAccount(account);
        userDto.setUsername(username);
        userDto.setPassword(passwordMD5);
        userDto.setPublicKey(publicKey);
        userDto.setRegTime(currentDate);
        if(userService.insert(userDto) > 0) {
            return ResultGenerator.genSuccessResult("注册成功");
        }else {
            return ResultGenerator.genFailResult("注册失败");
        }
    }

}
