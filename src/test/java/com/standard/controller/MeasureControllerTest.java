package com.standard.controller;

import com.standard.domain.Measure;
import com.standard.service.measure.MeasureService;
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
@WebMvcTest(controllers = {MeasureController.class})
class MeasureControllerTest extends AbstractRestControllerTest {

    private static final String BASE_URL = "/private/api/v1/measure";
    
    @MockitoBean
    MeasureService service;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();

        setUpMeasure();
    }

    @Test
    void testFindAll() throws Exception {
        Measure measure2 = new Measure();
        measure2.setId(2L);
        measure2.setName("bob");

        List<Measure> measures = Arrays.asList(measure, measure2);
        when(service.findAll()).thenReturn(measures);
        mockMvc.perform(get(BASE_URL)
                .with(jwt().jwt(jwt -> jwt.claim("user", "spring")).authorities(createJwtMeasureRoles()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testFindById() throws Exception {
        setUpMeasure();
        when(service.findById(measure.getId())).thenReturn(measure);
        mockMvc.perform(get(BASE_URL + "/1")
                .with(jwt().jwt(jwt -> jwt.claim("user", "spring")).authorities(createJwtMeasureRoles()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception {
        when(service.create(measure)).thenReturn(measure);
        mockMvc.perform(post(BASE_URL)
                .with(jwt().jwt(jwt -> jwt.claim("user", "spring")).authorities(createJwtMeasureRoles()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(measure)))
                .andExpect(status().isCreated());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/1")
                .with(jwt().jwt(jwt -> jwt.claim("user", "spring")).authorities(createJwtMeasureRoles()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testUpdate() throws Exception {
        when(service.update(1L, measure)).thenReturn(measure);
        mockMvc.perform(put(BASE_URL+"/1")
                .with(jwt().jwt(jwt -> jwt.claim("user", "spring")).authorities(createJwtMeasureRoles()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(measure)))
                .andExpect(status().isOk());
    }
}
