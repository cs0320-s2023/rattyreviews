package edu.brown.cs.student.main.APIHandlers;

import edu.brown.cs.student.main.Utils.Food;
import edu.brown.cs.student.main.server.MapSerializer;
import okio.Buffer;
import spark.Request;
import spark.Response;
import spark.Route;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.ZoneId;

public class GetReviewsFor implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        /**must be in format YYYY-MM-DD*/
        LocalDate asked = LocalDate.parse(request.queryParamOrDefault("date", LocalDate.now(ZoneId.of("UTC-04:00")).toString()));
        String menuStr = new ProvideMenu().GetMenuForDate(asked);
        Buffer helperBuff = new Buffer().writeString(menuStr, Charset.defaultCharset());
        Food.FullMenuResponse fullMenu = MapSerializer.fromJsonGeneric(helperBuff, Food.FullMenuResponse.class);
        //TODO: get reviews from file.

        return null;
    }
}
