package me.ryubato.sample;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(Lifecycle.PER_CLASS)
class SampleControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new SampleController()).alwaysDo(MockMvcResultHandlers.print()).build();
    }

    @Test
    void controllerTest_with_reqDto_underlineParam() throws Exception {
        SampleReqDto dto = new SampleReqDto();
        dto.set_id("123");
        dto.setName("test");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        Map<String, String> map = objectMapper.convertValue(dto, new TypeReference<Map<String, String>>() {});

        params.setAll(map);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/test/params").params(params));

        resultActions.andExpect(status().isOk());
    }
}