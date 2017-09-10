package north.tpop.core;

import java.time.Duration;
import org.openqa.selenium.WebDriver;

public interface Until {

    void apply(Element element, WebDriver driver, Duration timeout);
}
