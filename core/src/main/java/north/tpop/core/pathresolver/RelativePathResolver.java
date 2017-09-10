package north.tpop.core.pathresolver;

import north.tpop.core.Element;

public class RelativePathResolver implements PathResolver {

    private final Element element;
    private final String field;

    public RelativePathResolver(Element element, String field) {
        this.element = element;
        this.field = field;
    }

    @Override
    public String getPath() {
        return element.getPath() + "." + field;
    }
}
