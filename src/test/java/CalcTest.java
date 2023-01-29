import org.testng.annotations.Test;

import static org.example.Main.add;
import static org.example.Main.multiply;
import static org.testng.Assert.assertEquals;

public class CalcTest {

    @Test
    public void verifyAdd() {
        assertEquals(add(2,3), 5);
    }

    @Test
    public void verifyMultiply () {
        assertEquals(multiply(2,3), 10);
    }

}
