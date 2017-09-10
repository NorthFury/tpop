package north.tpop.core.wait;

import north.tpop.core.Element;
import north.tpop.core.WaitPredicate;
import org.openqa.selenium.WebDriver;

public class Not implements WaitPredicate {

    private final WaitPredicate predicate;

    public Not(WaitPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean apply(Element element, WebDriver driver) {
        return ! predicate.apply(element, driver);
    }

    @Override
    public String toString() {
        return "Not(" + predicate + ')';
    }

}
