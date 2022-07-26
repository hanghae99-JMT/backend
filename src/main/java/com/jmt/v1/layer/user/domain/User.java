package com.jmt.v1.layer.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jmt.v1.layer.like.domain.Likes;
import com.jmt.v1.layer.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @Column(unique = true)
    private String email;
    private String name;
    private String password;
    private boolean enabled;
    private GrantedAuthority authorityList;

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", authorityList=" + authorityList +
                '}';
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Review> reviews;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Likes> likes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(authorityList);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

}
