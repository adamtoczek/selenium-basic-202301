import com.fasterxml.jackson.databind.ser.Serializers;
import org.testng.annotations.Test;
import popPages.BasePage;
import popPages.HomePage;

public class MyStorePopTest extends TestPopBase{

    @Test
    public void myStoreHappyPath() throws InterruptedException {

        HomePage.open();
        BasePage.homePage.openProductByName("THE ADVENTURE POSTER");

        BasePage.productPage.changeVariant("Dimmensions", "60x90cm");
        BasePage.productPage.changeQty(3);
        BasePage.productPage.addToCart();
        BasePage.cartPreview.clickContinueShopping();
        BasePage.productPage.clickStoreLogo();

        BasePage.homePage.previewProductByName("HUMMINGBIRD T-SHIRT");
        BasePage.productPreview.changeVariant("Size", "L");
        BasePage.productPreview.changeVariant("Color", "Black");
        BasePage.productPreview.changeQTY(2);
        BasePage.productPreview.addToCart();
        BasePage.cartPreview.clickProceedToCheckout();

        BasePage.cartPage.verifyItemsInCart(2);





        Thread.sleep(3000);
    }
}
