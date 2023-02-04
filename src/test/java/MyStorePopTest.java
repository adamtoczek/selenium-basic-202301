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




        Thread.sleep(3000);
    }
}
