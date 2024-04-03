package com.iumutdikbasan.tripSearch.model.concretes;

import com.iumutdikbasan.tripSearch.model.abstracts.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(generator = "User" ,strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "User", sequenceName = "USER_ID_SEQ")
    private Long id;

    @NotBlank
    @Size(min=2, max=100, message = "First name must be at least 2 characters")
    @Column(name = "FIRST_NAME", length = 100, nullable = false)
    private String firstname;

    @NotBlank
    @Size(min=2, max=100, message = "Last name must be at least 2 characters")
    @Column(name = "LAST_NAME", length = 100, nullable = false)
    private String lastname;

    @Email
    @Column(name = "EMAIL", length = 50, nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(min=6, max=400, message = "Password must be at least 2 characters")
    @Column(name = "PASSWORD", length = 400, nullable = false)
    private String password;

//    todo : enum role
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
     //TODO:return list.of.role
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
