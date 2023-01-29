import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.openqa.selenium.*;

import java.util.List;

public class FormTest extends TestBase{
    @Test
    public void shouldSendFormWithSuccess() throws InterruptedException {
        driver.get("http://51.75.61.161:9102/form.php");

        WebElement firstName = driver.findElement(By.id("inputFirstName3"));

        firstName.sendKeys("Adam");

        List<WebElement> gender = driver.findElements(By.name("gridRadiosSex"));

        for(WebElement element : gender) {
            if (element.getAttribute("value").equals("other")) {
                element.click();
                break;
            }
        }

        Thread.sleep(5000);
    }
}
