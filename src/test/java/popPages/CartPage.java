package popPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class CartPage extends BasePage{
    NavigationHeader nav = new NavigationHeader();
    List<CartItem> cartItems = new ArrayList<>();
    protected String cartItemsCSS = ".cart-item";

    public CartPage() {
        readItems();
    }

    private void readItems() {
        List<WebElement> cartItemsWE = driver.findElements(By.cssSelector(cartItemsCSS));
        for (WebElement item : cartItemsWE)
            cartItems.add(new CartItem(item));
    }


    public void verifyItemsInCart(int n) {
        assertEquals(getItemsCount(), n);
    }

    public int getItemsCount() {
        return driver.findElements(By.cssSelector(cartItemsCSS)).size();
    }

    public CartItem getItem(int i) {
        return cartItems.get(i);
    }
}
