package popPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPreview extends ProductBase{

    public ProductPreview() {
        modalCSSprefix = ".modal-content ";
        _waitForModal();
    }

    private void _waitForModal() {
        WebElement addToCartBtn = driver.findElement(By.cssSelector(getModalCSS(addToCartCSS)));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
    }
}
