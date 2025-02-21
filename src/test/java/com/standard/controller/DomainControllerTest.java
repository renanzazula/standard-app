package com.standard.controller;

import com.standard.domain.Domain;
import com.standard.service.domain.DomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {DomainController.class})
class DomainControllerTest extends AbstractRestControllerTest {

    public static final String BASE_URL = "/private/api/v1/domain";
    
    @MockitoBean
    DomainService service;

    @Autowired
    MockMvc mockMvc;

    private Domain obj = null;

    @BeforeEach
    public void setUp() {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
        

        obj = new Domain();
        obj.setId(1L);
        obj.setName(NAME);
        obj.setDescription(DESCRIPTION);
        obj.setChecked(true);
    }

    @Test
    void testFindAll() throws Exception {
        Domain domain2 = new Domain();
        domain2.setId(2L);
        domain2.setName("bob");

        List<Domain> domains = Arrays.asList(obj, domain2);
        when(service.findAll()).thenReturn(domains);
        mockMvc.perform(get(BASE_URL)
                .with(jwt().jwt(jwt -> jwt.claim("user", "spring")).authorities(createJwtDomainRoles()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testFindById() throws Exception {
        when(service.findById(obj.getId())).thenReturn(obj);
        mockMvc.perform(get(BASE_URL + "/1")
                .with(jwt().jwt(jwt -> jwt.claim("user", "spring")).authorities(createJwtDomainRoles()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.description", equalTo(DESCRIPTION)));
    }

    @Test
    void testCreate() throws Exception {
        when(service.create(obj)).thenReturn(obj);
        mockMvc.perform(post(BASE_URL)
                .with(jwt().jwt(jwt -> jwt.claim("user", "spring")).authorities(createJwtDomainRoles()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(obj)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.description", equalTo(DESCRIPTION)));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/1")
                .with(jwt().jwt(jwt -> jwt.claim("user", "spring")).authorities(createJwtDomainRoles()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testUpdate() throws Exception {
        when(service.update(1L,obj)).thenReturn(obj);
        mockMvc.perform(put(BASE_URL+"/1")
                .with(jwt().jwt(jwt -> jwt.claim("user", "spring")).authorities(createJwtDomainRoles()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(obj)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.description", equalTo(DESCRIPTION)));
    }
}
