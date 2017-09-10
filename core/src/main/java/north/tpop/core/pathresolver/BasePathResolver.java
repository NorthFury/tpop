package north.tpop.core.pathresolver;

public class BasePathResolver implements PathResolver {

    private final String path;

    public BasePathResolver(String basePath, String field) {
        this.path = basePath + "." + field;
    }

    @Override
    public String getPath() {
        return path;
    }
}
