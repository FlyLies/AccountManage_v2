package com.ltr.service;

import com.ltr.pojo.PageBean;
import com.ltr.pojo.Web;

public interface WebService {

    /* 查询所有网站（包含模糊查询） */
    PageBean getWeb(Integer uid, String webName, Integer pageNum, Integer pageSize);

    /* 按wid查询网站 */
    Web getWebByWid(Integer wid);

    /* 添加网站 */
    boolean addWeb(Web web);

    /* 删除网站 */
    boolean deleteWeb(Integer wid);

    /* 修改网站 */
    boolean modifyWeb(Web web);

}
