package com.mzy.controller;


import com.mzy.entity.User;
import com.mzy.mapper.UserMapper;
//import com.mzy.service.impl.UserServiceImpl;
import com.mzy.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mzy
 * @since 2020-05-22
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/successLogin")
    public String successLogin() { return "successLogin"; }

    @RequestMapping("/successRegister")
    public String successRegister() { return "successRegister"; }

    @ResponseBody
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public String select(@RequestBody User user){
        System.out.println(user.getUserName());
        String result = userMapper.selectUserName(user.getUserName());
        System.out.println(result);
        if(result==null){
            return "0";
        }else return "1";
    }

    @ResponseBody
    @RequestMapping(value = "/selectUserName", method = RequestMethod.POST)
    public String selectUserName(@RequestBody User user){
        String userName = user.getUserName();
        String userPassword = user.getUserPassword();
        System.out.println(userName+userPassword);

        String result = "-1";

        String passwordMD5 = passwordMD5(userName,userPassword);

        if(userMapper.selectUserName(userName)==null){
            result = "0";
            return result;
        }else if(!userMapper.selectUserPassword(userName).equals(passwordMD5)){
            result = "1";
            return result;
        }else if(userMapper.selectUserPassword(userName).equals(passwordMD5)&&
                userMapper.selectUserAuthoity(userName).equals("正式会员")){
            result = "2";
            return result;
        }else if(userMapper.selectUserPassword(userName).equals(passwordMD5)&&
                userMapper.selectUserAuthoity(userName).equals("管理员")){
            result = "3";
            return result;
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value="/addUser",method = RequestMethod.POST)
    public String addUser(@RequestBody User user){
        String userName = user.getUserName();
        String userPassword = user.getUserPassword();
        String phone_number = user.getPhone_number();
        System.out.println(userName+"***"+userPassword);
        String passwordMD5 = passwordMD5(userName,userPassword);
        userMapper.addUser(userName,passwordMD5,"正式会员",phone_number);
        return "1";
    }

    public String passwordMD5(String userName,String userPassword){
        String src = userName + userPassword;
        try{
            //加密对象，指定加密方式
            MessageDigest md5 = MessageDigest.getInstance("md5");
            //准备要加密的数据
            byte[] b = src.getBytes();
            byte[] digest = md5.digest();
            char[] chars = new char[]{'0', '1', '2', '3', '4', '5',
                    '6', '7', 'A', 'B', 'C', 'd', 'o', '*', '#', '/'};
            StringBuffer sb = new StringBuffer();
            //处理成16进制的字符串
            //遍历加密后的密码，将每个元素右移四位，然后与15进行位运算
            for(byte bb:digest){
                sb.append(chars[(bb>>4)&15]);
                sb.append(chars[bb&15]);
            }
            System.out.println(sb);
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}

