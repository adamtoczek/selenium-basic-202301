import org.testng.annotations.Test;

public class SeleniumTest extends TestBase{

    @Test
    public void test1() throws InterruptedException {
        driver.get("http://51.75.61.161:9102/form.php");

        Thread.sleep(5000);
    }

}
