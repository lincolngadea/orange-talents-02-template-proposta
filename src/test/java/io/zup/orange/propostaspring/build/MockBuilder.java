package io.zup.orange.propostaspring.build;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


public class MockBuilder {

   private MockMvc mockMvc;

        public static MockHttpServletRequestBuilder run(String ML_API, String json) throws Exception {

        return MockMvcRequestBuilders
                .post(ML_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
    }



}
