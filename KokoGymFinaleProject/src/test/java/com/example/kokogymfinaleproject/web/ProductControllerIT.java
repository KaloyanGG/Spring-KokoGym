package com.example.kokogymfinaleproject.web;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails(value = "shefa", userDetailsServiceBeanName = "userDetailsService")
    void testProductsAddPageShown() throws Exception {

        mockMvc.perform(get("/products/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("addProduct"));
    }

    @Test
    @WithUserDetails(value = "shefa", userDetailsServiceBeanName = "userDetailsService")
    void testAddProductConfirm() throws Exception {

        mockMvc.perform(post("/products/add")
                        .param("name", "TestProduct")
                        .param("stockQuantity", "10")
                        .param("imageUrl", "https://www.google.com/img")
                        .param("price", "10.99")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/shop"));

        mockMvc.perform(post("/products/add")
                        .param("name", "TestProduct")
                        .param("stockQuantity", "10")
                        .param("imageUrl", "https://www.google.com/img")
                        .param("price", "-10.99")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products/add"));




    }
}
