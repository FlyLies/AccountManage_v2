package com.ltr.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ltr.mapper.AccountMapper;
import com.ltr.mapper.WebMapper;
import com.ltr.pojo.PageBean;
import com.ltr.pojo.Web;
import com.ltr.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WebServiceImpl implements WebService {

    @Autowired
    private WebMapper webMapper;
    @Autowired
    private AccountMapper accountMapper;

    /* 查询所有网站（包含模糊查询） */
    @Override
    public PageBean getWeb(Integer uid, String webName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Web> webList = webMapper.getWeb(uid,webName);
        Page<Web> webPage = (Page<Web>) webList;
        return new PageBean(webPage.getTotal(),webPage.getResult());
    }

    /* 按wid查询网站 */
    @Override
    public Web getWebByWid(Integer wid) {
        return webMapper.getWebByWid(wid);
    }

    /* 添加网站 */
    @Override
    public boolean addWeb(Web web) {
        return webMapper.addWeb(web) > 0;
    }

    /* 删除网站 */
    @Transactional(rollbackFor = Exception.class)  //事务出现任何异常都会回滚
    @Override
    public boolean deleteWeb(Integer wid) {
        return webMapper.deleteWeb(wid) > 0 && accountMapper.deleteAccountByWid(wid) > 0;
    }

    /* 修改网站 */
    @Override
    public boolean modifyWeb(Web web) {
        return webMapper.modifyWeb(web) > 0;
    }

}
