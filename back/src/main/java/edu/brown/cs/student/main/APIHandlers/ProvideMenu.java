package edu.brown.cs.student.main.APIHandlers;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
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
import java.text.ParseException;
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
                if (deleteFile) {
                    Files.delete(targetPath);
                }
                Files.writeString(targetPath, moreMenus);
                return moreMenus;
            } catch (IOException ex2) {
                return MapSerializer.simpleFailureResponse("error_file_failure", "file creation failed on the backend. retry request!");
            } catch (FailingHttpStatusCodeException fhttpe) {
                return MapSerializer.simpleFailureResponse("error_web_scraping", "dining.brown.edu appears to be down! Please verify this is true.");
            } catch (IndexOutOfBoundsException oobe) {
                return MapSerializer.simpleFailureResponse("error_web_scraping", "Web scraping returned unexpected structure! Date might be outside of available bounds. Please review the menu for this date on dining.brown.edu .");
            }
        } catch (ParseException pe) {
            return MapSerializer.simpleFailureResponse("error_parse", "Menu file did not have a parsable expiry date! Please check formatting of menu files.");
//        } catch (Exception e) {
//            return MapSerializer.exceptionalFailureResponse(e);
//        }
        }
    }

}
