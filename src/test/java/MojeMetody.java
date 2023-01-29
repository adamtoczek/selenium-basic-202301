import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MojeMetody {

    @Test
    public void test1(){
        System.out.println("Test1");
        assertEquals(true, false, "czarne nie jest białe");
    }

    @Test
    public void test2(){
        System.out.println("Test2");
        assertEquals("false", "false");
        assertTrue(false, "czarne nie jest biale");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("uruchamiana raz przed wszystkimi metodami danej klasy");
    }

    @BeforeMethod
    public void beforeEach() {
        System.out.println("metoda uruchamiana przed kazdym testem");
    }

    @AfterMethod
    public void afterEach() {
        System.out.println("metoda uruchamiana po kazdym tescie");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("metoda uruchamiana raz po wszystkich metodach testowych, na sam koniec");
    }
}
