package com.ltr.controller;

import com.ltr.pojo.Account;
import com.ltr.pojo.Result;
import com.ltr.pojo.User;
import com.ltr.service.AccountService;
import com.ltr.service.UserService;
import com.ltr.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class AccountController {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    /* 查询一个网站的所有账号（包括条件查询） */
    @GetMapping("/account")
    public Result getAccount(Integer wid, String webAccount,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("查询一个网站的所有账号：{},{},{},{}", wid, webAccount, pageNum, pageSize);
        return Result.success(accountService.getAccount(wid, webAccount, pageNum, pageSize));
    }

    @PostMapping("/account/key")
    public Result checkKey(String desKey, HttpServletRequest request) {
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJwt(jwt);
        User user = userService.getUserByUid((Integer) claims.get("uid"));
        if(desKey.equals(user.getDesKey())) {
            return Result.success();
        }
        else {
            return Result.error("密钥错误");
        }
    }

    /* 按aid查询账号 */
    @GetMapping("/account/{aid}")
    public Result getAccountByAid(@PathVariable Integer aid, HttpServletRequest request) {
        log.info("按aid查询账号：{}", aid);
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJwt(jwt);
        User user = userService.getUserByUid((Integer) claims.get("uid"));
        return Result.success(accountService.getAccountByAid(aid, user.getDesKey()));
    }

    /* 添加账号 */
    @PostMapping("/account")
    public Result addAccount(@RequestBody Account account, HttpServletRequest request) {
        log.info("添加账号：{}", account);
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJwt(jwt);
        User user = userService.getUserByUid((Integer) claims.get("uid"));
        if(accountService.addAccount(account, user.getDesKey())) {
            return Result.success();
        }
        else {
            return Result.error("添加失败");
        }
    }

    /* 按aid删除账号 */
    @DeleteMapping("/account/{aid}")
    public Result deleteAccountByAid(@PathVariable Integer aid) {
        log.info("按aid删除账号：{}", aid);
        if(accountService.deleteAccountByAid(aid)) {
            return Result.success();
        }
        else {
            return Result.error("删除失败");
        }
    }

    /* 修改账号 */
    @PutMapping("/account")
    public Result modifyAccount(@RequestBody Account account) {
        log.info("修改账号：{}", account);
        if(accountService.modifyAccount(account)) {
            return Result.success();
        }
        else {
            return Result.error("修改失败");
        }
    }

}
