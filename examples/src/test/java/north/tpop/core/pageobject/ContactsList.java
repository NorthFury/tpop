package north.tpop.core.pageobject;

import north.tpop.core.annotation.Selectable;
import north.tpop.core.annotation.SelectorType;
import north.tpop.core.Element;
import north.tpop.core.RepeatableElement;

public class ContactsList extends Element {

    @Selectable(by = SelectorType.className, value = "title")
    public Element title;

    @Selectable(by = SelectorType.tagName, value = "li")
    public RepeatableElement<Contact> contacts;
}
