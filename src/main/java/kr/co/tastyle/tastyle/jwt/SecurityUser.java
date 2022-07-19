package kr.co.tastyle.tastyle.jwt;

import kr.co.tastyle.tastyle.src.user.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {
    private User user;

    public SecurityUser(User user) {
        this.user = user;
    }

    public static SecurityUser of(User user) {
        return new SecurityUser(user);
    }

    public Long getUserId() {
        return user.getId();
    }

    public User getUser() {
        return user;
    }

    public String getUserDeviceType() {
        return user.getDeviceType().getDescription();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole().getDescription()));
    }

    @Override
    public String getUsername() {
        return user.getSocialId();
    }

    @Override
    public String getPassword() {
        return null;
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
