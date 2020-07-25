package machura.przemyslaw.informationmanagerdomain.domain.informationsources.web.videoportal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class VideoPortalDeserializer extends StdDeserializer<WebVideoPortal> {

    private final String INFORMATION_SOURCE_STRING_FIELD = "informationSource";
   // private final String WEB_

    public VideoPortalDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public WebVideoPortal deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode mainNode = jsonParser.getCodec().readTree(jsonParser);
       // mainNode.get
        return null;
    }
}
