package popPages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage extends BasePage{
    public void changeQty(int n) {
        WebElement qty = driver.findElement(By.cssSelector("#quantity_wanted"));
        qty.clear();
        qty.sendKeys(""+n);
    }

    public void changeVariant(String variantName, String value) {
        String currPrice = driver.findElement(By.cssSelector(".current-price")).getText();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));

        List<WebElement> variants = driver.findElements(By.cssSelector(".product-variants>div"));
        for (WebElement variant : variants) {
            if (variant.findElement(By.cssSelector("span.control-label")).getText().equals(variantName)) {
                WebElement tmp = variant.findElement(By.cssSelector("select, input"));
                if (tmp.getTagName().equals("select"))
                    _changeSelectVariant(variant, value);
                else
                // obsluga inputow
                {}
                try {
                    wait.until(c -> !driver.findElement(By.cssSelector(".current-price")).getText().equals(currPrice));
                }
                catch (TimeoutException ignored) {}
                break;
            }
        }
    }

    private void _changeSelectVariant(WebElement variant, String value) {
        Select dropDown = new Select(variant.findElement(By.cssSelector("select")));
        dropDown.selectByVisibleText(value);
    }
}
