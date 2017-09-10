package north.tpop.core;

import north.tpop.core.annotation.Selectable;
import north.tpop.core.annotation.SelectorType;
import org.junit.Assert;
import org.junit.Test;

public class PageObjectFactoryTest {
    private final PageObjectFactory factory;

    public PageObjectFactoryTest() {
        this.factory = new PageObjectFactory(null, null);
    }

    @Test
    public void repeatableElementTest() throws Exception {
        PageObjectWithRepeatableElement pageObject = this.factory.newPageObject(PageObjectWithRepeatableElement.class);
        Assert.assertTrue(
                "repeatable element should be injected",
                pageObject.items != null
        );
    }

    static class PageObjectWithRepeatableElement {
        @Selectable(by = SelectorType.tagName, value = "li")
        public RepeatableElement<Element> items;
    }
}
