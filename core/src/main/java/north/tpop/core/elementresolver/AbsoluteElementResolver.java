package north.tpop.core.elementresolver;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AbsoluteElementResolver implements ElementResolver {

    private final WebDriver driver;
    private final By selector;

    public AbsoluteElementResolver(WebDriver driver, By selector) {
        this.driver = driver;
        this.selector = selector;
    }

    @Override
    public WebElement find() {
        return driver.findElement(selector);
    }

    @Override
    public List<WebElement> findAll() {
        return driver.findElements(selector);
    }
}
