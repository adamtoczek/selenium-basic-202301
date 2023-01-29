import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class MyStoreSimpleTest extends TestBase{

    @Test
    public void emptyCartShouldDisplayText(){
        driver.get("http://146.59.32.4/index.php?id_product=6&rewrite=mug-the-best-is-yet-to-come&controller=product&id_lang=2");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.findElement(By.cssSelector("#add-to-cart-or-refresh button.add-to-cart")).click();

        WebElement proceed = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.cart-content-btn a")));
        proceed.click();
        WebElement removeBtn = driver.findElement(By.cssSelector(".remove-from-cart"));
        removeBtn.click();
        wait.until(ExpectedConditions.stalenessOf(removeBtn));
        assertEquals(driver.findElement(By.cssSelector("div.cart-overview>span")).getText(), "There are no more items in your cart");
    }
}
