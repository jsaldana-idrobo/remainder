package technical.test.remainder.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ResponseSerializer {
    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static XmlMapper xmlMapper = new XmlMapper();

    public ResponseSerializer(XmlMapper xmlMapper ) {
        ResponseSerializer.xmlMapper = xmlMapper;
    }

    @Autowired
    public static String serializeResult(Integer result, String acceptHeader) {
        try {
            if (acceptHeader.contains("xml")) {
                return xmlMapper.writeValueAsString(result);
            }
            if (acceptHeader.contains("json")) {
                return jsonMapper.writeValueAsString(result);
            }
            return "Response no accepted";
        } catch (JsonProcessingException e) {
            return "Error during serialization";
        }
    }
}