package com.ltr.controller;

import com.ltr.pojo.Result;
import com.ltr.pojo.Web;
import com.ltr.service.WebService;
import com.ltr.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class WebController {

    @Autowired
    private WebService webService;

    /* 查询所有网站 */
    @GetMapping("/web")
    public Result getWeb(Integer uid, String webName,
                         @RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "7") Integer pageSize,
                         HttpServletRequest request) {
        if (uid == null || uid == 0) {
            Claims claims = JwtUtils.parseJwt(request.getHeader("token"));
            uid = (Integer) claims.get("uid");
        }
        log.info("查询网站：{},{},{},{}", uid, webName, pageNum, pageSize);
        return Result.success(webService.getWeb(uid, webName, pageNum, pageSize));
    }

    /* 查询一个网站 */
    @GetMapping("/web/{wid}")
    public Result getWebByWid(@PathVariable Integer wid) {
        log.info("查询一个网站：{}", wid);
        return Result.success(webService.getWebByWid(wid));
    }

    /* 添加网站 */
    @PostMapping("/web")
    public Result addWeb(@RequestBody Web web, HttpServletRequest request) {
        log.info("添加网站：{}", web);
        Claims claims = JwtUtils.parseJwt(request.getHeader("token"));
        web.setUid((Integer) claims.get("uid"));
        if(webService.addWeb(web) > 0) {
            return Result.success();
        }
        else {
            return Result.error("添加失败");
        }
    }

    /* 删除网站 */
    @DeleteMapping("/web/{wid}")
    public Result deleteWeb(@PathVariable Integer wid) {
        log.info("删除网站：{}", wid);
        if(webService.deleteWeb(wid) > 0) {
            return Result.success();
        }
        else {
            return Result.error("删除失败");
        }
    }

    /* 修改网站 */
    @PutMapping("/web")
    public Result modifyWeb(@RequestBody Web web) {
        log.info("修改网站：{}", web);
        if(webService.modifyWeb(web) > 0) {
            return Result.success();
        }
        else {
            return Result.error("修改失败");
        }
    }

}
