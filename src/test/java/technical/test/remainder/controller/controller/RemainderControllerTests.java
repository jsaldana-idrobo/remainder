package technical.test.remainder.controller.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import technical.test.remainder.controller.RemainderController;
import technical.test.remainder.model.TestCase;
import technical.test.remainder.service.RemainderService;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RemainderController.class)
public class RemainderControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RemainderService remainderService;

    @Test
    public void testHelloEndpoint() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello to Remainder technical test app"));
    }

    @Test
    public void testGetRemaindersEndpoint() throws Exception {
        when(remainderService.calculateRemainder(7, 5, 12345)).thenReturn(12339);

        mockMvc.perform(get("/remainder")
                        .param("x", "7")
                        .param("y", "5")
                        .param("n", "12345")
                        .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json("12339"));
    }

    @Test
    public void testGetRemaindersEndpointInvalid() throws Exception {
        when(remainderService.calculateRemainder(2, 3, 10)).thenReturn(0);

        mockMvc.perform(get("/remainder")
                        .param("x", "2")
                        .param("y", "3")
                        .param("n", "10")
                        .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json("0"));
    }

    @Test
    public void testPostRemaindersEndpoint() throws Exception {
        when(remainderService.calculateRemainder(7, 5, 12345)).thenReturn(12339);
        mockMvc.perform(post("/remainder")
                        .content(asJsonString(new TestCase(7, 5, 12345)))
                        .header("Accept", "application/json")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json("12339"));
    }

    @Test
    public void testPostRemaindersEndpointInvalid() throws Exception {
        when(remainderService.calculateRemainder(2, 3, 10)).thenReturn(0);

        mockMvc.perform(post("/remainder")
                        .content(asJsonString(new TestCase(2, 3, 10)))
                        .header("Accept", "application/json")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json("0"));
    }

    @Test
    public void testPostRemaindersEndpointXml() throws Exception {
        when(remainderService.calculateRemainder(7, 5, 12345)).thenReturn(12339);
        mockMvc.perform(post("/remainder")
                        .content("<TestCase><x>7</x><y>5</y><n>12345</n></TestCase>")
                        .header("Accept", "application/xml")
                        .contentType("application/xml"))
                .andExpect(status().isOk())
                .andExpect(content().xml("<Integer>12339</Integer>"));
    }

    @Test
    public void testPostRemaindersEndpointInvalidXml() throws Exception {
        when(remainderService.calculateRemainder(2, 3, 10)).thenReturn(0);
        mockMvc.perform(post("/remainder")
                        .content("<TestCase><x>2</x><y>3</y><n>10</n></TestCase>")
                        .header("Accept", "application/xml")
                        .contentType("application/xml"))
                .andExpect(status().isOk())
                .andExpect(content().xml("<Integer>0</Integer>"));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

