package north.tpop.core.command;

import north.tpop.core.Command;
import north.tpop.core.Element;
import org.openqa.selenium.WebDriver;

public class SendKeysCommand implements Command {

    private final CharSequence keys;

    public SendKeysCommand(CharSequence keys) {
        this.keys = keys;
    }

    @Override
    public void apply(Element element, WebDriver driver) {
        element.getWebElement().sendKeys(keys);
    }

    @Override
    public String toString() {
        return "SendKeys(" + keys + ")";
    }
}
