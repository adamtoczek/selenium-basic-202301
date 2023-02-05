import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class CSVTest {

    @Test
    public void verifyCSVread() {
        MountainReader.readFile();
        assertEquals(MountainReader.mountainList.get(0).name, "Mont Blanc");
        assertEquals(MountainReader.mountainList.get(0).height, 4807);
    }

    @Test
    public void verifyFileRead() throws IOException {
        MountainReader.readSimpleFile();
        assertEquals(MountainReader.mountainsStrings.get(1).split(",")[0],"Mont Blanc");
    }

    @Test
    public void verifySortedCSVRead() {
        MountainReader.readFile();

        List<Mountain> sortedList = MountainReader.mountainList.stream()
                .sorted(Comparator.comparingInt(Mountain::getHeight)).toList();
        assertEquals(sortedList.get(0).name, "Dom Alps");


    }
}
