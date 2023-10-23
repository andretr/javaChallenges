package metropolisInterview;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Andre Ticona.
 * Date: 3/30/2023
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        String userDirectory = System.getProperty("user.dir");
        Path filePath = Paths.get(userDirectory + "/metropolis_visit_data_iso8601.csv");
        List<String> lines = Files.readAllLines(filePath);

        Map<Key, Double> siteWeekToPriceMap = lines.stream()
                .skip(1) // skip the header line
                .map(line -> line.split(","))
                .filter(parkingDetails -> "Payment Completed".equals(parkingDetails[4]))
                .filter(parkingDetails -> {
                    LocalDateTime entryTime = LocalDateTime.parse(parkingDetails[8], getDateTimeFormatter());
                    return entryTime.getDayOfWeek() != DayOfWeek.SATURDAY && entryTime.getDayOfWeek() != DayOfWeek.SUNDAY;
                })
                .collect(Collectors.groupingBy(parkingDetails -> new Key(Integer.parseInt(parkingDetails[0]),
                                LocalDateTime.parse(parkingDetails[8], getDateTimeFormatter()).get(WeekFields.of(Locale.getDefault()).weekOfYear())),
                        Collectors.summingDouble(parkingDetails -> {
                            if (parkingDetails.length > 9) {
                                return Double.parseDouble(parkingDetails[10]);
                            } else {
                                return 0.0;
                            }
                        })));

        for (Map.Entry<Key, Double> entry : siteWeekToPriceMap.entrySet())
            System.out.println("SiteID = " + entry.getKey() +
                    ", Price = " + entry.getValue());
    }

    private static DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
    }
}

