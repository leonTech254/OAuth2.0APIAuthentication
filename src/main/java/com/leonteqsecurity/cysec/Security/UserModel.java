//package com.leonteqsecurity.cysec.Security;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.time.LocalDate;
//import java.util.Collection;
//import java.util.Set;
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
////public class UserModel implements UserDetails {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String userId;
//    private String username;
//    private String fullName;
//    private String email;
//    private String apiKey;
//    private String address;
//    private String country;
//    private boolean accSuspendedUser;
//    private boolean accSuspendedAdmin;
//    private String otpPassword;
//    private LocalDate otpExpiry;
//    private String password;
//    private LocalDate dateRegistered;
//    private boolean isMail;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "user_role",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
//    private Set<Role> authorities;
//
//    public UserModel(String username, String password, Set<Role> authorities) {
//        this.username = username;
//        this.password = password;
//        this.authorities = authorities;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        // Implement logic for account expiration here.
//        return true; // For now, assuming accounts don't expire.
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        // Implement logic for account locking here.
//        return true; // For now, assuming accounts are not locked.
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        // Implement logic for credentials expiration here.
//        return true; // For now, assuming credentials don't expire.
//    }
//
//    @Override
//    public boolean isEnabled() {
//        // Implement logic for account enabling/disabling here.
//        return true; // For now, assuming accounts are enabled.
//    }
//}
