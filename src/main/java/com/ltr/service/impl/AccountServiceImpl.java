package com.ltr.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ltr.mapper.AccountMapper;
import com.ltr.mapper.UserMapper;
import com.ltr.pojo.Account;
import com.ltr.pojo.PageBean;
import com.ltr.pojo.User;
import com.ltr.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    /* 查询一个网站的所有账号（包括条件查询） */
    @Override
    public PageBean getAccount(Integer wid, String webAccount, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Account> accountList = accountMapper.getAccount(wid,webAccount);
        Page<Account> accountPage = (Page<Account>) accountList;
        return new PageBean(accountPage.getTotal(), accountPage.getResult());
    }

    /* 按aid查询账号 */
    @Override
    public Account getAccountByAid(Integer aid, String key) {
        Account account = accountMapper.getAccountByAid(aid);
        try {
            account.decryptAccount(key);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解密失败");
        }
        return accountMapper.getAccountByAid(aid);
    }

    /* 添加账号 */
    @Override
    public boolean addAccount(Account account, String key) {
        try {
            account.encryptAccount(key);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("加密失败");
        }
        return accountMapper.addAccount(account) > 0;
    }

    /* 按aid删除账号 */
    @Override
    public boolean deleteAccountByAid(Integer aid) {
        return accountMapper.deleteAccountByAid(aid) > 0;
    }

    /* 修改账号 */
    @Override
    public boolean modifyAccount(Account account) {
        return accountMapper.modifyAccount(account) > 0;
    }

}
