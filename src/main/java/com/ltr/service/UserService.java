package com.ltr.service;

import com.ltr.pojo.PageBean;
import com.ltr.pojo.User;

import java.util.List;

public interface UserService {

    /* 登录查询 */
    User getUserByPhoneAndPassword(User user);

    /* 查询所有用户（包括条件查询） */
    PageBean getUser(Integer uid, String username, Integer pageNum, Integer pageSize);

    /* 按uid查询用户 */
    User getUserByUid(Integer uid);

    /* 添加用户 */
    int addUser(User user);

    /* 删除用户 */
    int deleteUser(Integer uid);

    /* 修改用户 */
    int modifyUser(User user);

    /* 修改密码 */
    int modifyPassword(Integer uid, String newPassword, String oldPassword);

}
