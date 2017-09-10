package north.tpop.core.wait;

import north.tpop.core.Element;
import north.tpop.core.WaitPredicate;
import org.openqa.selenium.WebDriver;

public class IsVisible implements WaitPredicate {

    @Override
    public boolean apply(Element element, WebDriver driver) {
        return element.isDisplayed();
    }

    @Override
    public String toString() {
        return "IsVisible";
    }

}
