package north.tpop.core.elementresolver;

import java.util.List;
import north.tpop.core.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RelativeElementResolver implements ElementResolver {

    private final Element parent;
    private final By selector;

    public RelativeElementResolver(Element parent, By selector) {
        this.parent = parent;
        this.selector = selector;
    }

    @Override
    public WebElement find() {
        return parent.getWebElement().findElement(selector);
    }

    @Override
    public List<WebElement> findAll() {
        return parent.getWebElement().findElements(selector);
    }
}
