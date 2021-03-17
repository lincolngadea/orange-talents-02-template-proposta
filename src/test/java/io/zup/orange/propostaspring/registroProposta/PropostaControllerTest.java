package io.zup.orange.propostaspring.registroProposta;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.zup.orange.propostaspring.build.MockBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureDataJpa
@AutoConfigureMockMvc
class PropostaControllerTest {

    static String URI_API = "/api/proposta";

    @Autowired
    private EntityManager manager;

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

        MockHttpServletRequestBuilder builder = MockBuilder.run(URI_API, json);
        MvcResult mvcResult = mvc.perform(builder).andExpect(status().isCreated()).andReturn();
        Proposta propostaConsultada = (Proposta) manager.createQuery(
                "select p from Proposta p where p.documento = '186.375.330-35'")
                .getSingleResult();

        String locationResponse = "http://localhost"+URI_API+"/"+propostaConsultada.getId();
        String location = mvcResult.getResponse().getHeader("Location");
        Assertions.assertNotNull(location);
        Assertions.assertEquals(location, locationResponse);
    }
}