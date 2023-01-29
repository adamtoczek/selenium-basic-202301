import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AdvancedSelenium extends TestBase{

    @Test
    public void verifySliderWithArrowsKeys() throws InterruptedException {
        /*
        50 30 30 20
         */

        driver.get("http://51.75.61.161:9102/slider.php");

        WebElement slider = driver.findElement(By.id("custom-handle"));
        slideTo(slider, 50);
        assertEquals(slider.getText(), "50");
        slideTo(slider, 30);
        assertEquals(slider.getText(), "30");
        slideTo(slider, 30);
        assertEquals(slider.getText(), "30");
        slideTo(slider, 20);
        assertEquals(slider.getText(), "20");
    }

    private void slideTo(WebElement slider, int posTo) {
        int pos = Integer.parseInt(slider.getText());
        if (pos == posTo)
            return;
        Keys key;
        if (pos < posTo) {
            key = Keys.ARROW_RIGHT;
        }
        else {
            key = Keys.ARROW_LEFT;
        }

        while (Integer.parseInt(slider.getText()) != posTo)
            slider.sendKeys(key);
    }


    @Test
    public void dragAndDrop(){
        driver.get("http://51.75.61.161:9102/droppable.php");
        WebElement drag = driver.findElement(By.id("draggable"));
        WebElement drop = driver.findElement(By.id("droppable"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(drag).moveToElement(drop).release().perform();
        assertEquals(drop.getText(), "Dropped!");
    }

    @Test
    public void dragAndDrop2(){
        driver.get("http://51.75.61.161:9102/droppable.php");
        WebElement drag = driver.findElement(By.id("draggable"));
        WebElement drop = driver.findElement(By.id("droppable"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(drag, drop).perform();
        assertEquals(drop.getText(), "Dropped!");
    }

    @Test
    public void dragAndDrop3() {
        driver.get("http://51.75.61.161:9102/droppable.php");
        WebElement drag = driver.findElement(By.id("draggable"));
        WebElement drop = driver.findElement(By.id("droppable"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(drag).moveByOffset(drop.getLocation().getX() - drag.getLocation().getX(), 0).release().perform();
        assertEquals(drop.getText(), "Dropped!");
    }
}
