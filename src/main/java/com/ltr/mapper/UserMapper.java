package com.ltr.mapper;

import com.ltr.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    /* 登录查询 */
    User getUserByPhoneAndPassword(User user);

    /* 查询所有用户（包括条件查询） */
    List<User> getUser(Integer uid, String username);

    /* 按uid查询用户 */
    User getUserByUid(Integer uid);

    /* 查询密钥是否存在 */
    int checkDesKey(String desKey);

    /* 添加用户 */
    int addUser(User user);

    /* 删除用户 */
    int deleteUser(Integer uid);

    /* 修改用户 */
    int modifyUser(User user);

    /* 修改密码 */
    int modifyPassword(Integer uid, String newPassword, String oldPassword);

}
