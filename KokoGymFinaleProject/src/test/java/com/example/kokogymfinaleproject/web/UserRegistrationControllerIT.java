package com.example.kokogymfinaleproject.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegistrationPageShown() throws Exception {

        mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));

    }

    @Test
    void testRegistration() throws Exception {

        ResultActions resultActions = mockMvc.perform(post("/users/register")
                        .param("username", "annaa")
                        .param("password", "topsecreta")
                        .param("confirmPassword", "topsecreta")
                        .param("email", "gosho@mail.com")
                        .param("firstName", "Gosho")
                        .param("lastName", "Goshov")
                        .param("birthDate", "2000-01-01")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        mockMvc.perform(post("/users/register")
                        .param("username", "a")
                        .param("password", "topsecret")
                        .param("confirmPassword", "topsecret")
                        .param("email1", "gosho1@mail.com")
                        .param("firstName", "Gosho")
                        .param("lastName", "Goshov")
                        .param("birthDate", "2000-01-01")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"));

        mockMvc.perform(post("/users/register")
                        .param("username", "anna")
                        .param("password", "topsecret")
                        .param("confirmPassword", "topsecret11")
                        .param("email", "gosho1@mail.com")
                        .param("firstName", "Gosho")
                        .param("lastName", "Goshov")
                        .param("birthDate", "2000-01-01")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"));

    }

    @Test
    void testRegistration1() throws Exception {

        mockMvc.perform(post("/users/register")
                        .param("username1", "a")
                        .param("password", "topsecret")
                        .param("confirmPassword", "topsecret")
                        .param("email1", "gosho@mail.com")
                        .param("firstName", "Gosho")
                        .param("lastName", "Goshov")
                        .param("birthDate", "2000-01-01")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"));


    }

    @Test
    void testRegistration2() throws Exception {


        mockMvc.perform(post("/users/register")
                        .param("username2", "anna")
                        .param("password", "topsecret")
                        .param("confirmPassword", "topsecret1")
                        .param("email2", "gosho@mail.com")
                        .param("firstName", "Gosho")
                        .param("lastName", "Goshov")
                        .param("birthDate", "2000-01-01")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"));

    }






}







