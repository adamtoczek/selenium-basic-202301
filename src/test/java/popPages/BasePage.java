package popPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
    public static WebDriver driver;
    public static HomePage homePage;
    public static ProductPage productPage;
    public static CartPreview cartPreview;
    public static ProductPreview productPreview;
    public static CartPage cartPage;

    public static void clickStoreLogo() {
        driver.findElement(By.cssSelector("#_desktop_logo a")).click();
        homePage = new HomePage();
    }
}
