package metropolisInterview;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Andre Ticona.
 * Date: 3/27/2023
 */
public class Exercise {

    /*
    * INSTRUCTIONS
    *
    *
    * 1. Read the file metropolis_visit_data_iso8601.csv it contains records for parking entries
    * 2. Return a list of SiteID's and the revenue collected for each site
    *
    * NOTE:
    * - SiteID consists of the Site Id (number) and the week of the year (number 1-52)
    * - Only the trips with 'Payment Completed' should be considered for revenue
    * - Trips on weekends shouldn't be considered for revenue
    *
    * */
    public static void main(String[] args) {

        try {
//            Map<Key, Integer> result = getParseCsvOldWay();
            Map<Key, Integer> result = parseCsv();

            for (Map.Entry<Key, Integer> entry : result.entrySet())
                System.out.println("SiteID = " + entry.getKey() +
                        ", Price = " + entry.getValue());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static Map<Key, Integer>  parseCsv() throws IOException {
        String userDirectory = System.getProperty("user.dir");
        Path path = Paths.get(userDirectory + "\\metropolis_visit_data_iso8601.csv");
        List<String> lines = Files.readAllLines(path).stream().skip(1).collect(Collectors.toList());

        return lines.stream()
                .map(line -> line.split(","))
                .filter(line -> line[4].equalsIgnoreCase("Payment Completed"))
                .filter(line -> {
                    LocalDateTime entryTime = LocalDateTime.parse(line[8], DateTimeFormatter.ISO_OFFSET_DATE_TIME );
                    boolean isWeekend = entryTime.getDayOfWeek().getValue() == 6 ||
                            entryTime.getDayOfWeek().getValue() == 7;
                    return !isWeekend;
                })
                .collect(Collectors
                        .groupingBy(line ->
                            new Key(Integer.valueOf(line[0]), getWeekofYear(line[8])),
                                Collectors.summingInt(line -> {
                                    if(line.length > 9)
                                        return Integer.parseInt(line[10]);
                                    else
                                        return 0;
                                } )


                        ));

    }

    private static int getWeekofYear(String dateStr) {
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ISO_OFFSET_DATE_TIME );
        return dateTime.get(WeekFields.of(Locale.getDefault()).weekOfYear());
    }



    private static Map<Key, Integer> getParseCsvOldWay() throws IOException {
        String userDirectory = System.getProperty("user.dir");
        Path path = Paths.get(userDirectory + "\\metropolis_visit_data_iso8601.csv");
        List<String> lines = Files.readAllLines(path).stream().skip(1).collect(Collectors.toList());
        Map<Key, Integer> result = new HashMap<>();
        for (String line : lines) {

            String[] info = line.split(",");
            String entryTime = info[8];
            LocalDate entryDate = LocalDate.parse(entryTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            Integer weekNumber = entryDate.get(weekFields.weekOfWeekBasedYear());

            if(entryDate.getDayOfWeek().getValue() == 6 || entryDate.getDayOfWeek().getValue() == 7)
                continue;

            Integer siteId = Integer.parseInt(info[0]);
            if (!info[4].equalsIgnoreCase("Payment Completed"))
                continue;

            int price;
            try {
                price = Integer.parseInt(info[10]);
            } catch (IndexOutOfBoundsException e) {
                price = 0;
            }

            Key compositeKey = new Key(siteId, weekNumber);

            if (!result.containsKey(compositeKey)) {
                result.put(compositeKey, price);
            } else {
                result.put(compositeKey, result.get(compositeKey) + price);
            }
        }
        return result;
    }


}



