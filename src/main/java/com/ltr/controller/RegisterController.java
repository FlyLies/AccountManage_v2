package com.ltr.controller;

import com.ltr.pojo.Result;
import com.ltr.pojo.User;
import com.ltr.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        log.info("注册：{}", user);
        int row = userService.addUser(user);
        if(row > 0) {
            return Result.success();
        }
        else {
            return Result.error("注册失败");
        }
    }

}
