package north.tpop.core.pathresolver;

public class IndexedElementPathResolver implements PathResolver {

    private final PathResolver pathResolver;
    private final int index;

    public IndexedElementPathResolver(PathResolver pathResolver, int index) {
        this.pathResolver = pathResolver;
        this.index = index;
    }

    @Override
    public String getPath() {
        return pathResolver.getPath() + "[" + index + "]";
    }
}
