package de.allianz.project.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {

    ADMIN(Set.of(
            Permission.TODO_READ,
            Permission.TODO_READ_ALL,
            Permission.TODO_CREATE,
            Permission.TODO_UPDATE,
            Permission.TODO_DELETE
    )),
    MEMBER(Set.of(
            Permission.TODO_READ,
            Permission.TODO_READ_ALL,
            Permission.TODO_CREATE,
            Permission.TODO_UPDATE
    )),

    ANALYST(Set.of(
            Permission.TODO_READ,
            Permission.TODO_READ_ALL
    ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        return getPermissions().stream().map(permission -> new
                SimpleGrantedAuthority("ROLE_" + permission.name())).collect(Collectors.toSet());
    }
}