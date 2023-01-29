import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AlertTest extends TestBase{
    @BeforeMethod
    public void setupPage() {
        driver.get("http://51.75.61.161:9102/alerts.php");
    }

    @Test
    public void shouldAcceptAlert() {
        driver.findElement(By.id("simple-alert")).click();
        driver.switchTo().alert().accept();
        assertEquals(driver.findElement(By.id("simple-alert-label")).getText(), "OK button pressed");
    }

    @Test
    public void shouldFillPromptAlert() {
        driver.findElement(By.id("prompt-alert")).click();
        driver.switchTo().alert().sendKeys("Jan");
        driver.switchTo().alert().accept();
        assertEquals(driver.findElement(By.id("prompt-label")).getText(), "Hello Jan! How are you today?");
    }

    @Test
    public void shouldDismissAlert() {
        driver.findElement(By.id("confirm-alert")).click();
        driver.switchTo().alert().dismiss();
        assertEquals(driver.findElement(By.id("confirm-label")).getText(), "You pressed Cancel!");
    }

    //popup przegladarkowy Basic Auth nie da sie obsluzyc metodami do alertow
    @Test
    public void shouldAcceptBasicAuth() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/");
        driver.findElement(By.linkText("Basic Auth")).click();
        driver.switchTo().alert().sendKeys("admin\tadmin");
        Thread.sleep(5000);
    }

    @Test
    public void iframes() {
        driver.get("https://demoqa.com/frames");
        driver.switchTo().frame("frame1");
        assertEquals(driver.findElement(By.id("sampleHeading")).getText(), "This is a sample page");

        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame2");
        assertEquals(driver.findElement(By.id("sampleHeading")).getText(), "This is a samplepage");

    }



}
