package com.example.kokogymfinaleproject.web;

import com.example.kokogymfinaleproject.service.TrainerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.*;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TrainersControllerIT {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testTrainersPageShown() throws Exception {

        mockMvc.perform(get("/trainers"))
                .andExpect(status().isOk())
                .andExpect(view().name("trainers"));
    }

    @Test
    void testTrainerDetailsShown() throws Exception {

        mockMvc.perform(get("/trainers/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("trainerDetails"));

        mockMvc.perform(get("/trainers/10"))
                .andExpect(status().is4xxClientError());

    }

    @Test
    void testStartTrainer() throws Exception {
        mockMvc.perform(get("/trainers/permission"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"));
    }

    @Test
    @WithUserDetails(value = "customer1", userDetailsServiceBeanName = "userDetailsService")
    void testStartTrainerWithUser() throws Exception {
        mockMvc.perform(get("/trainers/permission"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/myProfile"));
    }

    @Test
    void testStartTrainerWithoutUser() throws Exception {
        mockMvc.perform(get("/trainers/permission"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"));
    }




}
