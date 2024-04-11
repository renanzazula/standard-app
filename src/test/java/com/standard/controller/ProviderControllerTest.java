package com.standard.controller;

import com.standard.domain.Provider;
import com.standard.service.provider.ProviderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {ProviderController.class})
public class ProviderControllerTest extends AbstractRestControllerTest {

    @MockBean
    ProviderService service;

    @Autowired
    MockMvc mockMvc;
 
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
        
        
        setUpFornecedor();
    }

    @Test
    public void testConsultar() throws Exception {
        Provider provider2 = new Provider();
        provider2.setId(2L);
        provider2.setName("bob");

        List<Provider> providers = Arrays.asList(provider, provider2);
        when(service.findAll()).thenReturn(providers);
        mockMvc.perform(get(ProviderController.BASE_URL)
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testConsultarByCodigo() throws Exception {
        when(service.findById(provider.getId())).thenReturn(provider);
        mockMvc.perform(get(ProviderController.BASE_URL + "/1")
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testIncluir() throws Exception {
        when(service.create(provider)).thenReturn(provider);
        mockMvc.perform(post(ProviderController.BASE_URL)
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(provider)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(ProviderController.BASE_URL + "/1")
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testAlterar() throws Exception {
        when(service.update(1L, provider)).thenReturn(provider);
        mockMvc.perform(put(ProviderController.BASE_URL+"/1")
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(provider)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }
}
