package north.tpop.core;

import org.openqa.selenium.WebDriver;

public interface WaitPredicate {

    boolean apply(Element element, WebDriver driver);
}
