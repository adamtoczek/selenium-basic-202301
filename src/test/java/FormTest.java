import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import static org.testng.Assert.assertEquals;

public class FormTest extends TestBase{
    @Test
    public void shouldSendFormWithSuccess() throws InterruptedException {
        driver.get("http://51.75.61.161:9102/form.php");

        WebElement firstName = driver.findElement(By.id("inputFirstName3"));
        firstName.sendKeys("Adam");

        driver.findElement(By.id("inputLastName3")).sendKeys("Kwiatkowski");
        driver.findElement(By.id("inputEmail3")).sendKeys("test@sii.pl");
        driver.findElement(By.id("inputAge3")).sendKeys("12");
        driver.findElement(By.cssSelector("*[name=\"gridRadiosExperience\"][value=\"7\"]")).click();

        List<String> proffesions = Arrays.asList("automation tester", "manual tester");
        List<WebElement> proffesionsLabels = driver.findElements(By.cssSelector("*[name=\"gridCheckboxProfession\"]+label"));
        for (WebElement label : proffesionsLabels) {
            if (proffesions.contains(label.getText().trim().toLowerCase()))
                label.click();
        }

        List<WebElement> gender = driver.findElements(By.name("gridRadiosSex"));

        for(WebElement element : gender) {
            if (element.getAttribute("value").equals("other")) {
                element.click();
                break;
            }
        }

        Select country = new Select(driver.findElement(By.id("selectContinents")));
        country.selectByValue("europe");

        Select sCommands = new Select(driver.findElement(By.id("selectSeleniumCommands")));


        List<String> commands = Arrays.asList("navigation-commands", "wait-commands");
        for (String val : commands)
            sCommands.selectByValue(val);

        File plik = new File("src//main//resources//file.txt");
        driver.findElement(By.id("chooseFile")).sendKeys(plik.getAbsolutePath());


        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        assertEquals(driver.findElement(By.id("validator-message")).getText().trim(), "Form send with success");

    }
}
