package com.ltr.service;

import com.ltr.pojo.Account;
import com.ltr.pojo.PageBean;

import java.util.List;

public interface AccountService {

    /* 查询一个网站的所有账号（包括条件查询） */
    PageBean getAccount(Integer wid, String webAccount, Integer pageNum, Integer pageSize);

    /* 按aid查询账号 */
    Account getAccountByAid(Integer aid, String key);

    /* 添加账号 */
    int addAccount(Account account, String key);

    /* 按aid删除账号 */
    int deleteAccountByAid(Integer aid);

    /* 修改账号 */
    int modifyAccount(Account account);

}
