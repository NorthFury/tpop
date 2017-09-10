package north.tpop.core.pageobject;

import north.tpop.core.annotation.Selectable;
import north.tpop.core.annotation.SelectorType;
import north.tpop.core.Element;

public class Contact extends Element {

    @Selectable(by = SelectorType.className, value = "name")
    public Element name;

    @Selectable(by = SelectorType.className, value = "email")
    public Element email;

}
