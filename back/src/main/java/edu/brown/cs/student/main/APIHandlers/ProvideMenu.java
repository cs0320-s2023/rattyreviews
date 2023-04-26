package edu.brown.cs.student.main.APIHandlers;

import edu.brown.cs.student.main.server.MapSerializer;
import spark.Request;
import spark.Response;
import spark.Route;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;

import static edu.brown.cs.student.main.webscraping.ScrapeDiningMenu.getAllMenus;
import java.util.List;
import edu.brown.cs.student.main.Utils.Food;


public class ProvideMenu implements Route {
    /**
     * provides the full menu for the sharpe refectory for the given date. will return the current day if the date isn't specified
     * Might fail for future dates, but seems to work for past dates arbitrarily
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        /**must be in format YYYY-MM-DD*/
        LocalDate asked = LocalDate.parse(request.queryParamOrDefault("date", LocalDate.now(ZoneId.of("UTC-04:00")).toString()));
        Map<String, Food.Menu> menuCollection = getAllMenus(asked);
        return(MapSerializer.toJsonGeneric(menuCollection, Food.Menu.class));
    }
}
