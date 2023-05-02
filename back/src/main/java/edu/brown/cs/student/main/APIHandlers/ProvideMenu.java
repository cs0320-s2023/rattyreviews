package edu.brown.cs.student.main.APIHandlers;

import edu.brown.cs.student.main.server.MapSerializer;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Map;

import static edu.brown.cs.student.main.webscraping.ScrapeDiningMenu.getAllMenus;
import java.util.List;
import edu.brown.cs.student.main.Utils.Food;

public class ProvideMenu implements Route {

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
        //TODO: setup cache eviction
        //TODO: do better json formatting (i.e. response: success, expires: 2023-05-21 23:43:02 UTC-04:00, listings: ...)
        LocalDate asked = LocalDate.parse(request.queryParamOrDefault("date", LocalDate.now(ZoneId.of("UTC-04:00")).toString()));
        try {
            return new String(Files.readAllBytes(Paths.get(REL_FILE_PATH + asked.toString() + ".json")));
        } catch (IOException e) {
            try {
                System.out.println("now scraping");
                Map<String, Food.Menu> menuCollection = getAllMenus(asked);
                String result = MapSerializer.toJsonGeneric(menuCollection, Food.Menu.class);
                Path targetPath = Paths.get(REL_FILE_PATH + asked.toString() +".json");
                Files.writeString(targetPath, result);
                return result;
            } catch (IOException ex2) {
                System.err.println(ex2.toString());
            }
        }
        return null;
    }
}
