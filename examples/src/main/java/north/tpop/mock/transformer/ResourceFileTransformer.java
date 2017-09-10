package north.tpop.mock.transformer;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import spark.ResponseTransformer;

public class ResourceFileTransformer implements ResponseTransformer {

    private final String basePath;

    public ResourceFileTransformer(String basePath) {
        this.basePath = basePath;
    }

    @Override
    public String render(Object model) throws Exception {
        if (!(model instanceof String)) {
            throw new Exception("ResourceFileTranformer only accepts a String as a model");
        }

        String resourcePath = (String) model;
        URL resource = this.getClass().getResource(basePath + resourcePath);

        if (resource == null) {
            throw new Exception(resourcePath + " view was not found");
        }

        Path path = Paths.get(resource.toURI());
        List<String> allLines = Files.readAllLines(path);

        StringBuilder sb = new StringBuilder();
        for (String line : allLines) {
            sb.append(line);
        }

        return sb.toString();
    }

}
