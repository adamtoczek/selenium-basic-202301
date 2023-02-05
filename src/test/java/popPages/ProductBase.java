package popPages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductBase extends BasePage {
    public NavigationHeader nav = new NavigationHeader();
    protected final String currentPriceCSS = ".current-price";
    protected final String qtyFieldCSS = "#quantity_wanted";
    protected final String productVariantsCSS = ".product-variants>div";
    protected final String variantNameCSS = "span.control-label";
    protected final String addToCartCSS = "button.add-to-cart";
    protected final String productPriceCSS = "span[itemprop=\"price\"]";
    protected String modalCSSprefix = "";
    protected String productTitleCSS = "h1";

    private String _getCSSforInputVariant(String value) {
        return "input[title=\""+value+"\"]";
    }

    protected String getModalCSS(String css) {
        return modalCSSprefix+css;
    }

    public void changeQty(int n) {
        WebElement qty = driver.findElement(By.cssSelector(getModalCSS(qtyFieldCSS)));
        qty.clear();
        qty.sendKeys(""+n);
    }

    public void changeVariant(String variantName, String value) {
        String currPrice = driver.findElement(By.cssSelector(getModalCSS(currentPriceCSS))).getText();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));

        List<WebElement> variants = driver.findElements(By.cssSelector(getModalCSS(productVariantsCSS)));
        for (WebElement variant : variants) {
            if (variant.findElement(By.cssSelector(getModalCSS(variantNameCSS))).getText().equals(variantName)) {
                WebElement tmp = variant.findElement(By.cssSelector("select, input"));
                if (tmp.getTagName().equals("select"))
                    _changeSelectVariant(variant, value);
                else
                    _changeInputVariant(variant, value);
                try {
                    wait.until(c -> !driver.findElement(By.cssSelector(getModalCSS(currentPriceCSS))).getText().equals(currPrice));
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

    private void _changeInputVariant(WebElement variant, String value) {
        variant.findElement(By.cssSelector(getModalCSS(_getCSSforInputVariant(value)))).click();
    }

    public void addToCart() {
        driver.findElement(By.cssSelector(getModalCSS(addToCartCSS))).click();
        cartPreview = new CartPreview();
    }

    public String getProductTitle () {
        return driver.findElement(By.cssSelector(getModalCSS(productTitleCSS))).getText();
    }

    public Float getPrice() {
        return Float.valueOf(driver.findElement(By.cssSelector(getModalCSS(productPriceCSS))).getAttribute("content"));
    }
}
