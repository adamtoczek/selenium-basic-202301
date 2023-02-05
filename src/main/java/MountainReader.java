import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MountainReader {
    public static List<Mountain> mountainList = new ArrayList<>();
    public static List<String> mountainsStrings = new ArrayList<>();
    private static String filename = "mountains.csv";

    public static void readFile() {
        mountainList = new CsvToBeanBuilder<Mountain>(
                new InputStreamReader(
                        new ReaderUtils().getFileFromResourceAsStream(filename)))
                .withType(Mountain.class)
                .withSkipLines(1)
                .build()
                .parse();
    }

    public static void readSimpleFile() throws IOException {
        InputStreamReader is = new InputStreamReader(new ReaderUtils().getFileFromResourceAsStream(filename));
        BufferedReader br = new BufferedReader(is);

        mountainsStrings = br.lines().collect(Collectors.toList());
    }




}
