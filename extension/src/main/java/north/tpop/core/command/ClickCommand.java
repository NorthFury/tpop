package north.tpop.core.command;

import north.tpop.core.Command;
import north.tpop.core.Element;
import org.openqa.selenium.WebDriver;

public class ClickCommand implements Command {

    @Override
    public void apply(Element element, WebDriver driver) {
        element.getWebElement().click();
    }

    @Override
    public String toString() {
        return "Click";
    }
}
