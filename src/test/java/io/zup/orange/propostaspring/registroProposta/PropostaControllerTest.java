package io.zup.orange.propostaspring.registroProposta;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureDataJpa
@AutoConfigureMockMvc
class PropostaControllerTest {

    static String URI_API = "/api/proposta";

    @Autowired
    MockMvc mvc;

    @Test
    @Transactional
    @DisplayName("Deve criar uma proposta e retornar código 201 com URI criada no Header")
    void criaPropostaTest() throws Exception{

        Proposta proposta = new Proposta(
                "Usuario",
                "186.375.330-35",
                "teste@teste.com.br",
                new BigDecimal(1000),
                "Rua do teste");

        String json = new ObjectMapper().writeValueAsString(proposta);

        MockHttpServletRequestBuilder content = MockMvcRequestBuilders
                .post(URI_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(content)
                .andExpect(status().isCreated());
    }
}