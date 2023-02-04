import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class MyStoreSimpleTest extends TestBase{

    @Test
    public void emptyCartShouldDisplayText(){
        driver.get("http://146.59.32.4/index.php?id_product=6&rewrite=mug-the-best-is-yet-to-come&controller=product&id_lang=2");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.findElement(By.cssSelector("#add-to-cart-or-refresh button.add-to-cart")).click();

        WebElement proceed = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.cart-content-btn a")));
        proceed.click();
        WebElement removeBtn = driver.findElement(By.cssSelector(".remove-from-cart"));
        removeBtn.click();
        wait.until(ExpectedConditions.stalenessOf(removeBtn));
        assertEquals(driver.findElement(By.cssSelector("div.cart-overview>span")).getText(), "There are no more items in your cart");
    }

    @Test
    public void happyPath() throws InterruptedException {
        driver.get("http://146.59.32.4");

        //kliknij w kafelek z produktem THE ADVENTURE POSTER
        driver.findElement(By.linkText("THE ADVENTURE POSTER")).click();

        String currPrice = driver.findElement(By.cssSelector(".current-price")).getText();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        //zmien rozmiar na inny
        List<WebElement> variants = driver.findElements(By.cssSelector(".product-variants>div"));
        for (WebElement variant : variants) {
            if (variant.findElement(By.cssSelector("span")).getText().equals("Dimension")) {
                WebElement dimensionWE = variant.findElement(By.cssSelector("select"));
                Select dimension = new Select(dimensionWE);
                dimension.selectByVisibleText("60x90cm");
                //poczekaj az pojawi sie nowa cena
//                try {
//                    wait.until(ExpectedConditions.stalenessOf(currPrice));
//                }
//                catch (TimeoutException e) {}
                try {
                    wait.until(c -> !driver.findElement(By.cssSelector(".current-price")).getText().equals(currPrice));
                }
                catch (TimeoutException ignored) {}
                break;
            }
        }

        //zmien ilosc na 3
        WebElement qty = driver.findElement(By.cssSelector("#quantity_wanted"));
        qty.clear();
        qty.sendKeys("3");

        //dodaj do koszyka
        driver.findElement(By.cssSelector("button.add-to-cart")).click();

        WebElement proceedBtn = driver.findElement(By.cssSelector(".cart-content-btn a"));
        wait.until(ExpectedConditions.elementToBeClickable(proceedBtn));

        int qtyFromModal = Integer.parseInt(driver.findElement(By.cssSelector(".modal-content .product-quantity>strong")).getText());
        assertEquals(qtyFromModal, 3);

        driver.findElement(By.cssSelector(".cart-content-btn button")).click();
        //powrot na strone glowna
        driver.findElement(By.cssSelector("#_desktop_logo a")).click();

        //kliknij quick view na hummingbird t-shirt
        List<WebElement> articles = driver.findElements(By.cssSelector(".featured-products article"));

        for (WebElement article : articles) {
            WebElement prodTtitle = article.findElement(By.cssSelector("h3.product-title a"));
            WebElement quickViewA = article.findElement(By.cssSelector("a.quick-view"));
            if (prodTtitle.getText().equals("HUMMINGBIRD T-SHIRT")) {
                Actions actions = new Actions(driver);
                actions.moveToElement(prodTtitle).build().perform();
                wait.until(ExpectedConditions.elementToBeClickable(quickViewA));
                quickViewA.click();

                //wait for modal
                WebElement addToCartBtn = driver.findElement(By.cssSelector(".modal-content button.add-to-cart"));
                wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));

                String currPriceModal = driver.findElement(By.cssSelector(".current-price")).getText();
                //zmienic kolr na czarny
                List<WebElement> variantsModal = driver.findElements(By.cssSelector(".modal-content .product-variants-item"));
                for (WebElement variant : variantsModal) {
                    if (variant.findElement(By.cssSelector("span.control-label")).getText().equals("Color")) {
                        variant.findElement(By.cssSelector("input[title=\"Black\"]")).click();
                        try {
                            wait.until(c -> !driver.findElement(By.cssSelector(".current-price")).getText().equals(currPriceModal));
                        }
                        catch (TimeoutException ignored) {}
                        break;
                    }
                }
                //qty na 2
                WebElement qtyModal = driver.findElement(By.cssSelector("#quantity_wanted"));
                qtyModal.clear();
                qtyModal.sendKeys("2");
                //add to cart
                addToCartBtn.click();
                wait.until(ExpectedConditions.stalenessOf(addToCartBtn));

                proceedBtn = driver.findElement(By.cssSelector(".cart-content-btn a"));
                wait.until(ExpectedConditions.elementToBeClickable(proceedBtn));
                qtyFromModal = Integer.parseInt(driver.findElement(By.cssSelector(".modal-content .product-quantity>strong")).getText());
                assertEquals(qtyFromModal, 2);
                //przejdz do koszyka
                proceedBtn.click();
                break;
            }
        }

        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart-item"));

        assertEquals(cartItems.size(), 2);

        WebElement cartItem = cartItems.get(1);
        String currItemPrice = cartItem.findElement(By.cssSelector(".product-price>strong")).getText();
        cartItem.findElement(By.cssSelector("button.js-increase-product-quantity")).click();


        wait.until(ExpectedConditions.stalenessOf(cartItem));
        cartItems = driver.findElements(By.cssSelector(".cart-item"));



        Thread.sleep(5000);

    }

    @Test
    public void myStorePop() {

//        productPage.changeVariant("Color", "Black");
//        productPage.changeVariant("Dimension", "40x60cm");
//        prodductPage.setQty(2);
//        productPage.clickAddToCart();
//
//        homePage.clickQuickView("hommigbird t-shirt");
//        productQuickView.changeVariant("Color", "Black").setQty(3).clickAddToCart();
    }

}
