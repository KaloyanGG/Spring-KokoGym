package com.example.kokogymfinaleproject.service;

import com.example.kokogymfinaleproject.model.KokoGymUserDetails;
import com.example.kokogymfinaleproject.model.entity.RoleEntity;
import com.example.kokogymfinaleproject.model.entity.ShoppingCartEntity;
import com.example.kokogymfinaleproject.model.entity.UserEntity;
import com.example.kokogymfinaleproject.model.enums.RoleEnum;
import com.example.kokogymfinaleproject.repository.ShoppingCartRepository;
import com.example.kokogymfinaleproject.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class KokoGymUserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepo;
    @Mock
    private ShoppingCartRepository mockShoppingCartRepo;

    private KokoGymUserDetailsService toTest;

    @BeforeEach
    void setUp() {
        toTest = new KokoGymUserDetailsService(mockUserRepo);
    }

    @Test
    void testLoadUserByUsername_UserExists() {

        UserEntity user = new UserEntity()
                .setFirstName("Marko")
                .setLastName("Sharkov")
                .setEmail("marko@mail.com")
                .setUsername("marko")
                .setImageUrl("http://image.com/image")
                .setPassword("pass")
                .setShoppingCart(new ShoppingCartEntity())
                .setRoles(List.of(
                        new RoleEntity().setRole(RoleEnum.TRAINER),
                        new RoleEntity().setRole(RoleEnum.BOSS),
                        new RoleEntity().setRole(RoleEnum.CUSTOMER)

                ))
                .setBirthDate(LocalDate.of(2001, 1, 15));

        when(mockUserRepo.findByUsername(user.getUsername()))
                .thenReturn(Optional.of(user));

        KokoGymUserDetails userDetails = (KokoGymUserDetails) toTest.loadUserByUsername(user.getUsername());

        Assertions.assertEquals(user.getUsername(), userDetails.getUsername());
        Assertions.assertEquals(user.getFirstName(), userDetails.getFirstName());
        Assertions.assertEquals(user.getLastName(), userDetails.getLastName());
        Assertions.assertEquals(user.getEmail(), userDetails.getEmail());
        Assertions.assertEquals(user.getImageUrl(), userDetails.getImageUrl());
        Assertions.assertEquals(user.getBirthDate(), userDetails.getBirthDate());
        Assertions.assertEquals(user.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(user.getShoppingCart(), userDetails.getShoppingCart());
        Assertions.assertEquals(user.getRoles().size(), userDetails.getAuthorities().size());


    }

    @Test
    void testLoadByUsername_UserDoesNotExists(){
        Assertions.assertThrows(UsernameNotFoundException.class,()->{
            toTest.loadUserByUsername("marko");
        });
    }


}
