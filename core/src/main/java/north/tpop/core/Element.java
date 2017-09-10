package north.tpop.core;

import java.time.Duration;
import north.tpop.core.elementresolver.ElementResolver;
import north.tpop.core.pathresolver.PathResolver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Element {

    private final Logger logger = LoggerFactory.getLogger(Element.class);

    WebDriver driver;
    ElementResolver elementResolver;
    PathResolver pathResolver;
    WaitExecutor waitExecutor;

    public WebElement getWebElement() {
        logger.debug("resolving {}", pathResolver.getPath());
        return elementResolver.find();
    }

    public void execute(Command command) {
        logger.info("executing {} on {}", command.toString(), pathResolver.getPath());
        command.apply(this, driver);
    }

    public void wait(WaitPredicate predicate) {
        logWait(predicate);
        waitExecutor.apply(this, predicate);
    }

    public void wait(WaitPredicate predicate, Duration timeout) {
        logWait(predicate);
        waitExecutor.apply(this, predicate, timeout);
    }

    private void logWait(WaitPredicate predicate) {
        logger.info("waiting {} on {}", predicate.toString(), pathResolver.getPath());
    }

    public String getPath() {
        return pathResolver.getPath();
    }

    public String getText() {
        return getWebElement().getText();
    }

    public String getTagName() {
        return getWebElement().getTagName();
    }

    public String getAttribute(String attribute) {
        return getWebElement().getAttribute(attribute);
    }

    public String getCssValue(String propertyName) {
        return getWebElement().getCssValue(propertyName);
    }

    public boolean isDisplayed() {
        return getWebElement().isDisplayed();
    }
}
