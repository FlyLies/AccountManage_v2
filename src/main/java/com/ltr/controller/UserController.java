package com.ltr.controller;

import com.ltr.pojo.Result;
import com.ltr.pojo.User;
import com.ltr.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /* 查询所有用户（包括条件查询） */
    @GetMapping("/user")
    public Result getUser(Integer uid, String username, Integer pageNum, Integer pageSize) {
        log.info("查询所有用户：{},{},{},{}", uid, username, pageNum, pageSize);
        return Result.success(userService.getUser(uid, username, pageNum, pageSize));
    }

    /* 按uid查询用户 */
    @GetMapping("/user/{uid}")
    public Result getUserByUid(@PathVariable Integer uid) {
        log.info("按uid查询：{}", uid);
        return Result.success(userService.getUserByUid(uid));
    }

    /* 添加用户 */
    @PostMapping("/user")
    public Result addUser(@RequestBody User user) {
        log.info("用户注册：{}", user);
        if(userService.addUser(user)) {
            return Result.success();
        }
        else {
            return Result.error("注册失败");
        }
    }

    /* 删除用户 */
    @DeleteMapping("/user/{uid}")
    public Result deleteUser(@PathVariable Integer uid) {
        log.info("用户注销：{}", uid);
        if(userService.deleteUser(uid)) {
            return Result.success();
        }
        else {
            return Result.error("注销失败");
        }
    }

    /* 修改用户 */
    @PutMapping("/user")
    public Result modifyUser(@RequestBody User user) {
        log.info("修改用户：{}", user);
        if(userService.modifyUser(user)) {
            return Result.success();
        }
        else {
            return Result.error("修改失败");
        }
    }

    /* 修改密码 */
    @PutMapping("/user/{uid}")
    public Result modifyPassword(@PathVariable Integer uid, String newPassword, String oldPassword) {
        log.info("修改密码：{},{},{}", uid, newPassword, oldPassword);
        if(userService.modifyPassword(uid, newPassword, oldPassword)) {
            return Result.success();
        }
        else {
            return Result.error("修改失败");
        }
    }

}
