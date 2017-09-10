package north.tpop.core.test;

import java.time.Duration;
import java.util.List;
import north.tpop.core.pageobject.Contact;
import north.tpop.core.PageObjectFactory;
import north.tpop.core.WaitExecutor;
import north.tpop.core.pageobject.S1Page;
import north.tpop.core.command.ClickCommand;
import north.tpop.core.wait.IsVisible;
import north.tpop.core.wait.SeleniumWaitExecutor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class S1Test {

    private S1Page page;

    @Before
    public void setup() {
//        String exePath = "<path-to-driver>/chromedriver.exe";
//        System.setProperty("webdriver.chrome.driver", exePath);

        ChromeDriver driver = new ChromeDriver();
        WaitExecutor waitExecutor = new SeleniumWaitExecutor(driver, Duration.ofSeconds(5));

        PageObjectFactory pageObjectFactory = new PageObjectFactory(driver, waitExecutor);
        page = pageObjectFactory.newPageObject(S1Page.class);

        driver.get("http://localhost:8085/view/s1.html");
    }

    @Test
    public void wait_until_visible() throws InterruptedException {
        Assert.assertFalse("hiddenText should be hidden", page.hiddenText.isDisplayed());
        page.showButton.execute(new ClickCommand());
        page.hiddenText.wait(new IsVisible());
        Assert.assertTrue("hiddenText should now be visible", page.hiddenText.isDisplayed());
    }

    @Test
    public void contact_list_should_contain_items() {
        List<Contact> items = page.contactsList.contacts.getAll();
        Assert.assertEquals("contacts items should contain 3 elements", 3, items.size());

        final Contact firstItem = items.get(0);
        Assert.assertEquals("first contact item should have name First", "First", firstItem.name.getText());
        Assert.assertEquals("first contact item should have email first@first.com", "first@first.com", firstItem.email.getText());
    }

    @Test
    public void first_item_from_contact_list_should_have_name_andemai() {
        final Contact firstItem = page.contactsList.contacts.get(0);
        Assert.assertEquals("first contact item should have name First", "First", firstItem.name.getText());
        Assert.assertEquals("first contact item should have email first@first.com", "first@first.com", firstItem.email.getText());
    }

}
