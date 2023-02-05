package popPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartItem {
    WebElement itemElement;
    private String qtyCSS =".js-cart-line-product-quantity";
    private String totalPriceCSS = ".product-line-actions .product-price";

    public CartItem(WebElement itemElement) {
        this.itemElement = itemElement;
    }

    public int getQty() {
        return Integer.parseInt(itemElement.findElement(By.cssSelector(qtyCSS)).getAttribute("value"));
    }

    public String getTotalPrice() {
        return itemElement.findElement(By.cssSelector(totalPriceCSS)).getText();
    }
}
