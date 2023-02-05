package popPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage{
    public NavigationHeader nav = new NavigationHeader();

    public static void open() {
        driver.get("http://146.59.32.4/index.php");
        homePage = new HomePage();
    }

    public void openProductByName(String title) {
        driver.findElement(By.linkText(title)).click();
        productPage = new ProductPage();
    }

    public void previewProductByName(String title) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        List<WebElement> articles = driver.findElements(By.cssSelector(".featured-products article"));
        for (WebElement article : articles) {
            WebElement prodTtitle = article.findElement(By.cssSelector("h3.product-title a"));
            WebElement quickViewA = article.findElement(By.cssSelector("a.quick-view"));
            if (prodTtitle.getText().equals(title)) {
                Actions actions = new Actions(driver);
                actions.moveToElement(prodTtitle).build().perform();
                wait.until(ExpectedConditions.elementToBeClickable(quickViewA));
                quickViewA.click();
                productPreview = new ProductPreview();
                break;
            }
        }

    }
}
