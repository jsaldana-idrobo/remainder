package technical.test.remainder.controller.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import technical.test.remainder.util.ResponseSerializer;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ResponseSerializerTests {


    @Test
    public void testSerializeResultXml() {
        String result = ResponseSerializer.serializeResult(10, "application/xml");
        assertEquals("<Integer>10</Integer>", result);
    }

    @Test
    public void testSerializeResultJson() {
        String result = ResponseSerializer.serializeResult(10, "application/json");
        assertEquals("10", result);
    }

    @Test
    public void testSerializeResultInvalidAcceptHeader() {
        String result = ResponseSerializer.serializeResult(10, "text/html");
        assertEquals("Response no accepted", result);
    }

    @Test
    public void testSerializeResultCatchBlock() {
        XmlMapper xmlMapper = new XmlMapper() {
            @Override
            public String writeValueAsString(Object value) throws JsonProcessingException {
                throw new JsonProcessingException("Forced exception") {};
            }
        };
        ResponseSerializer serializer = new ResponseSerializer(xmlMapper);

        String result = serializer.serializeResult(0, "application/xml");

        assertTrue(result.contains("Error during serialization"));
    }
}
