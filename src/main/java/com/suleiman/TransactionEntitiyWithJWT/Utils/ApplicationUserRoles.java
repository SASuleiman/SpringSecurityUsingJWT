package com.suleiman.TransactionEntitiyWithJWT.Utils;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRoles {
    CUSTOMER(Sets.newHashSet(ApplicationUserPermission.POST_PERMISSION)),
    DEVELOPER(Sets.newHashSet(ApplicationUserPermission.POST_PERMISSION,ApplicationUserPermission.PUT_PERMISSION,ApplicationUserPermission.GET_PERMISSION)),
    SENIOR_DEVELOPER(Sets.newHashSet(ApplicationUserPermission.POST_PERMISSION,ApplicationUserPermission.GET_PERMISSION,ApplicationUserPermission.DELETE_PERMISSION,ApplicationUserPermission.PUT_PERMISSION));

    private  final Set<ApplicationUserPermission> permissions;

    ApplicationUserRoles(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority() {
        Set<SimpleGrantedAuthority> grantedAuthority = getPermissions().stream().map((permission) -> {
            return new SimpleGrantedAuthority(permission.getPermission());
        }).collect(Collectors.toSet());
        grantedAuthority.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return grantedAuthority;
    }

}
