package popPages;

import org.openqa.selenium.By;

public class NavigationHeader extends BasePage{
    public void clickStoreLogo() {
        driver.findElement(By.cssSelector("#_desktop_logo a")).click();
        homePage = new HomePage();
    }
}
