import org.testng.annotations.Test;
import popPages.HomePage;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.testng.Assert.assertEquals;
import static popPages.BasePage.*;

public class MyStorePopTest extends TestPopBase{

    @Test
    public void myStoreHappyPath() {
        List<Float> itemPrice = new ArrayList<>();
        List<Integer> itemQty = new ArrayList<>();

        HomePage.open();
        homePage.openProductByName("THE ADVENTURE POSTER");
        assertEquals(productPage.getProductTitle(), "THE ADVENTURE POSTER");
        productPage.changeVariant("Dimmensions", "60x90cm");
        productPage.changeQty(3);
        itemQty.add(3);
        itemPrice.add(productPage.getPrice());
        productPage.addToCart();
        cartPreview.clickContinueShopping();
        productPage.nav.clickStoreLogo();

        homePage.previewProductByName("HUMMINGBIRD T-SHIRT");
        assertEquals(productPreview.getProductTitle(), "HUMMINGBIRD T-SHIRT");
        productPreview.changeVariant("Size", "L");
        productPreview.changeVariant("Color", "Black");
        productPreview.changeQty(2);
        itemQty.add(2);
        itemPrice.add(productPage.getPrice());
        productPreview.addToCart();

        cartPreview.clickProceedToCheckout();

        //assercje w metodach POP
        cartPage.verifyItemsInCart(2);
        //albo w tescie
        assertEquals(cartPage.getItemsCount(), 2);




        for (int i =0; i < itemQty.size(); i++) {
            assertEquals(cartPage.getItem(i).getQty(), itemQty.get(i));
            assertEquals(cartPage.getItem(i).getTotalPrice(), formatPrice(itemQty.get(i)*itemPrice.get(i)));
        }
    }

    private String formatPrice(Float price) {
        Locale currentLocale = Locale.getDefault();
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat formatter = new DecimalFormat("0.00", otherSymbols);
        return "$"+formatter.format(price);
    }
}
