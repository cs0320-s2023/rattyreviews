package edu.brown.cs.student.main.APIHandlers;

import edu.brown.cs.student.main.server.MapSerializer;
import okio.Buffer;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static edu.brown.cs.student.main.webscraping.ScrapeDiningMenu.getAllMenus;

import edu.brown.cs.student.main.Utils.Food;

public class ProvideMenu implements Route {

    public final int NUM_OF_HOURS_FOR_EVICT = 0;
    public final int NUM_OF_MINS_FOR_EVICT = 10;

    public final String REL_FILE_PATH = "src\\main\\java\\edu\\brown\\cs\\student\\main\\menuData\\";
    /**
     * provides the full menu for the sharpe refectory for the given date. will return the current day if the date isn't specified
     * Might fail for future dates, but seems to work for past dates arbitrarily
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    //TODO: add in cache eviction
    //TODO: add script to run this every 6 hours
    @Override
    public Object handle(Request request, Response response) throws Exception {
        /**must be in format YYYY-MM-DD*/
        LocalDate asked = LocalDate.parse(request.queryParamOrDefault("date", LocalDate.now(ZoneId.of("UTC-04:00")).toString()));
        return GetMenuForDate(asked);
    }

    //public since this getting the menu is helpful for other systems
    public String GetMenuForDate(LocalDate asked) {
        boolean deleteFile = false;
        Path targetPath = Paths.get(REL_FILE_PATH + asked.toString() + ".json");
        try {
            String someSaved = new String(Files.readAllBytes(targetPath));
            Food.FullMenuResponse someRes = MapSerializer.fromJsonGeneric(new Buffer().writeString(someSaved, Charset.defaultCharset()), Food.FullMenuResponse.class);
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC-04:00"));
            Calendar compCal = Calendar.getInstance(TimeZone.getTimeZone("UTC-04:00"));
            Date someDate = sdf.parse(someRes.expire());
            compCal.setTime(someDate);
            if(now.compareTo(compCal) > 0) {
                deleteFile = true;
                throw new IOException();
            }
            return someSaved;
        } catch (IOException e) {
            try {
                System.out.println("now scraping");
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC-04:00"));
                cal.add(Calendar.HOUR, NUM_OF_HOURS_FOR_EVICT);
                cal.add(Calendar.MINUTE, NUM_OF_MINS_FOR_EVICT);
                Map<String, Food.Menu> menuCollection = getAllMenus(asked);
                String moreMenus = MapSerializer.toJsonTotalGeneric(new Food.FullMenuResponse("success", cal.getTime().toString(), menuCollection), Food.FullMenuResponse.class);
                if(deleteFile) { Files.delete(targetPath); }
                Files.writeString(targetPath, moreMenus);
                return moreMenus;
            } catch (IOException ex2) {
                System.err.println(ex2.toString());
                return MapSerializer.simpleFailureResponse("file_failure", "file creation failed on the backend. retry request!");
            }
        } catch (Exception e) {
            System.err.println(e);
            return MapSerializer.exceptionalFailureResponse(e);
        }
    }

}
