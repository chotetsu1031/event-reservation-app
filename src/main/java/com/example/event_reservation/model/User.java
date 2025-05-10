// com.example.eventreservation.model.User.java
package com.example.event_reservation.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String role = "USER"; // USER：一般ユーザー　ADMIN：管理者
    
    private String mailaddress;
    
    // UserDetails の実装メソッド ↓↓↓

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> "ROLE_" + this.role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // 無効期限なし
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // ロックなし
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // パスワードの有効期限なし
    }

    @Override
    public boolean isEnabled() {
        return true;  // 常に有効
    }
}
