package com.example.kokogymfinaleproject.model;

import com.example.kokogymfinaleproject.model.entity.ShoppingCartEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

public class KokoGymUserDetails implements UserDetails {

    private Long id;
    private String password;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String imageUrl;

    private ShoppingCartEntity shoppingCart;
    private Collection<GrantedAuthority> authorities;

    public KokoGymUserDetails() {
    }

    public KokoGymUserDetails(Long id, String password, String username, String email, String firstName, String lastName, LocalDate birthDate, String imageUrl, ShoppingCartEntity shoppingCart, Collection<GrantedAuthority> authorities) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.email= email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.imageUrl = imageUrl;
        this.shoppingCart = shoppingCart;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public String getFullName(){
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Long getId() {
        return id;
    }

    public KokoGymUserDetails setId(Long id) {
        this.id = id;
        return this;
    }

    public KokoGymUserDetails setPassword(String password) {
        this.password = password;
        return this;
    }

    public KokoGymUserDetails setUsername(String username) {
        this.username = username;
        return this;
    }

    public KokoGymUserDetails setEmail(String email) {
        this.email = email;
        return this;
    }

    public KokoGymUserDetails setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public KokoGymUserDetails setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public KokoGymUserDetails setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public KokoGymUserDetails setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public KokoGymUserDetails setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
        return this;
    }

    public ShoppingCartEntity getShoppingCart() {
        return shoppingCart;
    }

    public KokoGymUserDetails setShoppingCart(ShoppingCartEntity shoppingCart) {
        this.shoppingCart = shoppingCart;
        return this;
    }
}
