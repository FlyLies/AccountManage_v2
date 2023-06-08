package com.ltr.mapper;

import com.ltr.pojo.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {

    /* 查询一个网站的所有账号（包括条件查询） */
    List<Account> getAccount(Integer wid, String webAccount);

    /* 按aid查询账号 */
    Account getAccountByAid(Integer aid);

    /* 添加账号 */
    int addAccount(Account account);

    /* 按aid删除账号 */
    int deleteAccountByAid(Integer aid);

    /* 按wid删除账号 */
    int deleteAccountByWid(Integer wid);

    /* 按uid删除账号 */
    int deleteAccountByUid(Integer uid);

    /* 修改账号 */
    int modifyAccount(Account account);

}
