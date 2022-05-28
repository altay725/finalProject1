package com.example.demo.security;
import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.security.Permission.*;


public enum Role {
    GUEST(Sets.newHashSet(DATA_READ)),
    ADMIN(Sets.newHashSet(DATA_READ, DATA_WRITE));

    private final Set<Permission> permissionSet;


    Role(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Set<Permission> getPermissionSet() {
        return permissionSet;
    }

    public Set<SimpleGrantedAuthority> getSimpleGrantedAuthority(){
        Set<SimpleGrantedAuthority>
                simpleGrantedAuthorities =
                getPermissionSet().stream()
                        .map(accountPermission -> new SimpleGrantedAuthority(accountPermission.getPermission_info()))
                        .collect(Collectors.toSet());

        for(Permission accountPermission : getPermissionSet()){
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(accountPermission.getPermission_info()));
        }
        return simpleGrantedAuthorities;
    }
}