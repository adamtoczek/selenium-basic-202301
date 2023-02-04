package popPages;

import org.openqa.selenium.By;

import static org.testng.Assert.assertEquals;

public class CartPage extends BasePage{
    public void verifyItemsInCart(int n) {
        assertEquals(getItemsCount(), n);
    }

    public int getItemsCount() {
        return driver.findElements(By.cssSelector(".cart-item")).size();
    }
}
