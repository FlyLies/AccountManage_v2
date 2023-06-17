package com.ltr.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ltr.mapper.AccountMapper;
import com.ltr.mapper.UserMapper;
import com.ltr.mapper.WebMapper;
import com.ltr.pojo.PageBean;
import com.ltr.pojo.User;
import com.ltr.pojo.Web;
import com.ltr.service.UserService;
import com.ltr.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WebMapper webMapper;
    @Autowired
    private AccountMapper accountMapper;

    /* 登录查询 */
    @Override
    public User getUserByPhoneAndPassword(User user) {
        return userMapper.getUserByPhoneAndPassword(user);
    }

    /* 查询所有用户（包括条件查询） */
    @Override
    public PageBean getUser(Integer uid, String username, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.getUser(uid, username);
        Page<User> userPage = (Page<User>) userList;
        return new PageBean(userPage.getTotal(), userPage.getResult());
    }

    /* 按uid查询用户 */
    @Override
    public User getUserByUid(Integer uid) {
        return userMapper.getUserByUid(uid);
    }

    /* 添加用户 */
    @Override
    public boolean addUser(User user) {
        // 生成密钥
        String desKey;
        do {
            desKey = "";
            for (int i = 0; i < 8; i++) {
                String randChar = String.valueOf(Math.round(Math.random() * 9));
                desKey = desKey.concat(randChar);
            }
        }while (userMapper.checkDesKey(desKey) > 0);
        user.setDesKey(desKey);
        return userMapper.addUser(user) > 0;
    }

    /* 删除用户 */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteUser(Integer uid) {
        return userMapper.deleteUser(uid) > 0 && webMapper.deleteWebByUid(uid) > 0 && accountMapper.deleteAccountByUid(uid) > 0;
    }

    /* 修改用户 */
    @Override
    public boolean modifyUser(User user) {
        return userMapper.modifyUser(user) > 0;
    }

    /* 修改密码 */
    @Override
    public boolean modifyPassword(Integer uid, String newPassword, String oldPassword) {
        return userMapper.modifyPassword(uid, newPassword, oldPassword) > 0;
    }

}
