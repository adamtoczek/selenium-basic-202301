package popPages;

import org.openqa.selenium.By;

public class HomePage extends BasePage{


    public static void open() {
        driver.get("http://146.59.32.4/index.php");
        homePage = new HomePage();
    }

    public void openProductByName(String title) {
        driver.findElement(By.linkText(title)).click();
        productPage = new ProductPage();
    }
}
