package north.tpop.core;

import org.openqa.selenium.WebDriver;

public interface Command {

    void apply(Element element, WebDriver driver);
}
