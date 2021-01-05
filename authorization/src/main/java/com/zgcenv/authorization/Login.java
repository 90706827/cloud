package com.zgcenv.authorization;

import lombok.Data;

@Data
public class Login {
    private String username;
    private String password;
    private boolean autoLogin;
    private String type;
}
