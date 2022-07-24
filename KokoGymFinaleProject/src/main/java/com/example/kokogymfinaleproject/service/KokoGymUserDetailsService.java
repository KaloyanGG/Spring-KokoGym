package com.example.kokogymfinaleproject.service;

import com.example.kokogymfinaleproject.model.KokoGymUserDetails;
import com.example.kokogymfinaleproject.model.entity.RoleEntity;
import com.example.kokogymfinaleproject.model.entity.UserEntity;
import com.example.kokogymfinaleproject.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class KokoGymUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public KokoGymUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.
                findByUsername(username).
                map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found!"));

        return userDetails;
    }

    private UserDetails map(UserEntity userEntity) {

        return new KokoGymUserDetails(
                userEntity.getId(),
                userEntity.getPassword(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getBirthDate(),
                userEntity.getImageUrl(),
                userEntity.
                        getRoles().
                        stream().
                        map(this::map).
                        toList()
        );
    }

    private GrantedAuthority map(RoleEntity userRole) {
        return new SimpleGrantedAuthority("ROLE_" +
                userRole.
                        getRole().name());
    }
}
