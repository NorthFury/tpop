package north.tpop.core.wait;

import north.tpop.core.Element;
import north.tpop.core.WaitExecutor;
import north.tpop.core.WaitPredicate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumWaitExecutor implements WaitExecutor {

    private final WebDriver driver;
    private final Duration defaultTimeout;

    public SeleniumWaitExecutor(WebDriver driver, Duration defaultTimeout) {
        this.driver = driver;
        this.defaultTimeout = defaultTimeout;
    }

    @Override
    public void apply(Element element, WaitPredicate predicate) {
        apply(element, predicate, defaultTimeout);
    }

    @Override
    public void apply(final Element element, final WaitPredicate predicate, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout.getSeconds());
        wait.until(webDriver -> predicate.apply(element, webDriver));
    }

}
