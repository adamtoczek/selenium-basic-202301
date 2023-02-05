import com.opencsv.bean.CsvBindByPosition;

public class Mountain {
    @CsvBindByPosition(position = 0)
    String name;
    @CsvBindByPosition(position = 1)
    String range;
    @CsvBindByPosition(position = 2)
    String country;
    @CsvBindByPosition(position = 3)
    int height;

    public int getHeight() {
        return height;
    }
}
