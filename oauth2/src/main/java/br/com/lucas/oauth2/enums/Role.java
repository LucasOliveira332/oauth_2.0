package br.com.lucas.oauth2.enums;

public enum Role {
    ADMIN(1L),
    BASIC(2L);

    long roleId;

    Role(long roleId){
        this.roleId = roleId;
    }



}
