package com.suleiman.TransactionEntitiyWithJWT.Utils;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

public enum ApplicationUserPermission {
    GET_PERMISSION("get_transaction"),
    PUT_PERMISSION("put_transaction"),
    DELETE_PERMISSION("delete_transaction"),
    POST_PERMISSION("post_transaction");

private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }


}
