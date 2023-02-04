import org.testng.annotations.Test;
import popPages.HomePage;

import static org.testng.Assert.assertEquals;
import static popPages.BasePage.*;

public class MyStorePopTest extends TestPopBase{

    @Test
    public void myStoreHappyPath() {

        HomePage.open();
        homePage.openProductByName("THE ADVENTURE POSTER");

        productPage.changeVariant("Dimmensions", "60x90cm");
        productPage.changeQty(3);
        productPage.addToCart();
        cartPreview.clickContinueShopping();
        clickStoreLogo();

        homePage.previewProductByName("HUMMINGBIRD T-SHIRT");

        productPreview.changeVariant("Size", "L");
        productPreview.changeVariant("Color", "Black");
        productPreview.changeQty(2);
        productPreview.addToCart();

        cartPreview.clickProceedToCheckout();

        //assercje w metodach POP
        cartPage.verifyItemsInCart(2);
        //albo w tescie
        assertEquals(cartPage.getItemsCount(), 2);

    }
}
