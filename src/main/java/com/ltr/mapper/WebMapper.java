package com.ltr.mapper;

import com.ltr.pojo.Web;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WebMapper {

    /* 查询所有网站（包含模糊查询） */
    List<Web> getWeb(Integer uid, String webName);

    /* 按wid查询网站 */
    Web getWebByWid(Integer wid);

    /* 添加网站 */
    int addWeb(Web web);

    /* 删除网站 */
    int deleteWeb(Integer wid);

    /* 按uid删除网站 */
    int deleteWebByUid(Integer uid);

    /* 修改网站 */
    int modifyWeb(Web web);

}
