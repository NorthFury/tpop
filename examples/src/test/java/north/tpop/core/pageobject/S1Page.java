package north.tpop.core.pageobject;

import north.tpop.core.annotation.Selectable;
import north.tpop.core.annotation.SelectorType;
import north.tpop.core.Element;

public class S1Page {

    @Selectable(by = SelectorType.id, value = "showButton")
    public Element showButton;

    @Selectable(by = SelectorType.id, value = "hiddenText")
    public Element hiddenText;

    @Selectable(by = SelectorType.id, value = "contacts")
    public ContactsList contactsList;
}
