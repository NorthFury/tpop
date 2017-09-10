package north.tpop.mock.transformer;

import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    private final ObjectMapper mapper;

    protected JsonTransformer() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public String render(Object model) {
        try {
            return mapper.writeValueAsString(model);
        } catch (IOException ex) {
            return "Serialization error";
        }
    }
}
