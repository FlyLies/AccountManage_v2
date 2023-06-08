package com.ltr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer uid;
    private String username;
    private String phone;
    private String password;
    private String identity;
    private String desKey;
}
