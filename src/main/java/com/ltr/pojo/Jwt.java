package com.ltr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jwt {
    private Integer uid;
    private String identity;
    private String jwt;
}
