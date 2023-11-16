package com.seminario.undeadbank.model;

import com.seminario.undeadbank.utils.ClassificationEnum;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class UserInfoDetails implements UserDetails {

    private final String username;
    private final String password;
    private ClassificationEnum classification;

    public UserInfoDetails(User user) {
        username = user.getUsername();
        password = user.getPassword();
        classification = ClassificationEnum.getById(user.getUserClassification().getInternalId());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(classification.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
