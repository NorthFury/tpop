package north.tpop.core;

import java.util.ArrayList;
import java.util.List;
import north.tpop.core.elementresolver.ElementResolver;
import north.tpop.core.elementresolver.IndexedElementResolver;
import north.tpop.core.pathresolver.IndexedElementPathResolver;
import north.tpop.core.pathresolver.PathResolver;
import org.openqa.selenium.WebElement;

public class RepeatableElement<T extends Element> {

    ElementResolver elementResolver;
    PathResolver pathResolver;
    Class<T> clazz;
    PageObjectFactory pageObjectFactory;

    public String getPath() {
        return pathResolver.getPath();
    }

    public List<T> getAll() {
        List<WebElement> webElements = elementResolver.findAll();

        ArrayList<T> elements = new ArrayList<>(webElements.size());
        for (int i = 0; i < webElements.size(); i++) {
            elements.add(newElement(i));
        }

        return elements;
    }

    public T get(int index) {
        T newElement = newElement(index);

        return newElement;
    }

    private T newElement(int index) {
        T newElement = pageObjectFactory.newPageObject(clazz);
        newElement.elementResolver = new IndexedElementResolver(elementResolver, index);
        newElement.pathResolver = new IndexedElementPathResolver(pathResolver, 0);
        return newElement;
    }
}
