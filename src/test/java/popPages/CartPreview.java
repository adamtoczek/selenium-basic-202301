package popPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPreview extends BasePage{

    public CartPreview() {
        _waitForModal();
    }

    private void _waitForModal(){
        WebElement proceedBtn = driver.findElement(By.cssSelector(".cart-content-btn a"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.elementToBeClickable(proceedBtn));
    }

    public void clickContinueShopping() {
        driver.findElement(By.cssSelector(".cart-content-btn button")).click();
    }

    public void clickProceedToCheckout() {
        WebElement proceedBtn = driver.findElement(By.cssSelector(".cart-content-btn a"));
        proceedBtn.click();
        cartPage = new CartPage();
    }
}
