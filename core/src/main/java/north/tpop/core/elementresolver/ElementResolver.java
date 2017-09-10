package north.tpop.core.elementresolver;

import java.util.List;
import org.openqa.selenium.WebElement;

public interface ElementResolver {

    WebElement find();

    List<WebElement> findAll();
}
