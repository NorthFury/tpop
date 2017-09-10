package north.tpop.core.elementresolver;

import java.util.List;
import org.openqa.selenium.WebElement;

public class IndexedElementResolver implements ElementResolver {

    private final ElementResolver parent;
    private final int index;

    public IndexedElementResolver(ElementResolver parent, int index) {
        this.parent = parent;
        this.index = index;
    }

    @Override
    public WebElement find() {
        return parent.findAll().get(index);
    }

    @Override
    public List<WebElement> findAll() {
        throw new IllegalStateException("IndexedElementResolver is used for Element types that do not use findAll");
    }
}
