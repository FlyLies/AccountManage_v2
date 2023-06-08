package com.ltr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {  //分页查询结果封装类
    private Long total;  //总记录数
    private List rows;   //查询结果
}