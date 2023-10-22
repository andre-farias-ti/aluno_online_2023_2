package com.br.alunoonline.dto;

import com.br.alunoonline.enuns.RoleName;

import java.util.List;

public class UserDTO {

    private String username;
    private String password;
    private List<RoleName> listRole;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleName> getListRole() {
        return listRole;
    }

    public void setListRole(List<RoleName> listRole) {
        this.listRole = listRole;
    }
}
