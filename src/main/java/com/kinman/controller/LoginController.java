package com.kinman.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kinman.pojo.Result;
import com.kinman.pojo.User;
import com.kinman.service.AdUserService;
import com.kinman.utils.JsonMerge;
import com.kinman.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AdUserService adUserService;

    @PostMapping
    public Result login(@RequestBody User user){
        log.info("用户登录: {}", user);
        User e = adUserService.login(user);
        //登录成功,生成令牌,下发令牌
        if (e != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateJwt(claims); //jwt包含了当前登录的员工信息
            Map<String, Object> map = new HashMap<>();
            map.put("token", jwt);
            map.put("user", e);
            //user字段与token并列
            return Result.success(map);
            //这种方式不会有user字段，user内部的字段直接与token并列
            //JSONObject jsonObject = JsonMerge.merge((JSONObject) JSON.toJSON(map), (JSONObject) JSON.toJSON(e));
        }

        //登录失败, 返回错误信息
        return Result.error("用户名或密码错误");
    }

}
