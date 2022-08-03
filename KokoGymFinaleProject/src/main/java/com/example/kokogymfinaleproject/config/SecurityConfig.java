package com.example.kokogymfinaleproject.config;

import com.example.kokogymfinaleproject.model.enums.RoleEnum;
import com.example.kokogymfinaleproject.repository.UserRepository;
import com.example.kokogymfinaleproject.service.KokoGymUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/users/login", "/users/register").anonymous()
                .antMatchers("/users/logout","/users/myProfile").authenticated()
                .antMatchers("/products/add").hasAnyRole(RoleEnum.BOSS.name(), RoleEnum.TRAINER.name())
                .antMatchers("/trainers/permission").not().hasAnyRole(RoleEnum.BOSS.name(), RoleEnum.TRAINER.name())
                .antMatchers("/groupWorkouts/add").hasRole(RoleEnum.BOSS.name())
                .antMatchers("/shoppingCart/**").authenticated()
                .antMatchers("/shop/addToCart").authenticated()
                .antMatchers("/products/**").authenticated()
                .antMatchers("/orders/**").authenticated()
                .antMatchers("/api/**").permitAll()

//                .anyRequest().permitAll()

                .and()
                .formLogin()
                .loginPage("/users/login")
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                .defaultSuccessUrl("/")
                .failureForwardUrl("/users/login-error")

                .and()
                .logout()
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        return http.build();

    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new KokoGymUserDetailsService(userRepository);
    }
}
