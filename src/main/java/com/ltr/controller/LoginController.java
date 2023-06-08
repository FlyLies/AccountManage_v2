package com.ltr.controller;

import com.ltr.pojo.Jwt;
import com.ltr.pojo.Result;
import com.ltr.pojo.User;
import com.ltr.service.UserService;
import com.ltr.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("登录:{}", user);
        User check = userService.getUserByPhoneAndPassword(user);
        if(check == null) {
            return Result.error("账号不存在");
        }
        else if(check.getPassword().equals(user.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("uid", check.getUid());
            claims.put("phone", check.getPhone());
            claims.put("identity", check.getIdentity());
            String jwt = JwtUtils.generateJwt(claims);
            Jwt jjwt = new Jwt(check.getUid(), check.getIdentity(), jwt);
            log.info("令牌实体:{}", jjwt);
            return Result.success(jjwt);
        }
        else return Result.error("密码错误");
    }

}
