import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;

public class CalcTest {

    @Test
    public void verifyAdd() {
        assertEquals(Main.add(2,3), 5);
    }

    @Test
    public void verifyMultiply () {
        assertEquals(Main.multiply(2,3), 10);
    }

}
