package edu.brown.cs.student.main.server;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import okio.BufferedSource;

public class MapSerializer {
  public static final String UNKNOWN_ERROR_MESSAGE =
      "There was an error, but it's not obvious what caused it. Report this to the developers.";

  /**
   * Sets up a map to be converted to a JSON success response string.
   *
   * @param label The label to be applied to the targetObj
   * @param targetObj The serializable object that will be associated with label, just after the
   *     response data field.
   * @return A JSON string indicating a success response.
   */
  public static String simpleSuccessResponse(String label, Object targetObj) {
    Map<String, Object> someMap = new LinkedHashMap<>();
    someMap.put("result", "success");
    someMap.put(label, targetObj);
    return toJson(someMap);
  }

  /**
   * Sets up a map to be converted to a JSON failure response string.
   *
   * @param errorType The type of error experienced by a caller
   * @param message The message pertaining to the given errorType
   * @return A JSON string indicating an errorType response.
   */
  public static String simpleFailureResponse(String errorType, String message) {
    Map<String, Object> someMap = new LinkedHashMap<>();
    someMap.put("result", errorType);
    someMap.put(errorType, message);
    return toJson(someMap);
  }

  /**
   * Sets up a map to be converted to a JSON failure response string, using an otherwise unhandled
   * exception.
   *
   * @param e The exception experienced by the caller.
   * @return A JSON string indicating an error_unknown response.
   */
  public static String exceptionalFailureResponse(Exception e) {
    Map<String, Object> someMap = new LinkedHashMap<>();

    someMap.put("result", "error_unknown");
    someMap.put("error_unknown", UNKNOWN_ERROR_MESSAGE);
    someMap.put("exceptionAsString", e.toString());
    someMap.put("exceptionMessage", e.getMessage());
    // Putting out stack trace for easy checks and localization of what went wrong.
    someMap.put("exceptionStackTrace", Arrays.stream(e.getStackTrace()).toList().toString());

    return MapSerializer.toJson(someMap);
  }

  /**
   * Uses Moshi to build a JSON string out of a Map of strings and object.
   *
   * @param targetMap The map to build a JSON string from
   * @return The JSON string resulting from Map adaptation.
   */
  public static String toJson(Map<String, Object> targetMap) {
    try {
      Moshi moshi = new Moshi.Builder().build();
      JsonAdapter<Map> adapter =
          moshi.adapter(Types.newParameterizedType(Map.class, String.class, Object.class));
      String someStr = adapter.toJson(targetMap);
      return someStr;
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Uses Moshi to convert a BufferedSource to a Map of strings and objects
   *
   * @param jsonStr The BufferedSource (otfen from an URL based input stream) to be converted
   * @return A map of strings and objects representing the data provided from the datasource.
   * @throws IOException If the BufferedSource exists in a format that cannot be converted to a
   *     string, object map.
   */
  // This is meant to allow for easier testing with the JSON objects being set out for Maps and CSV
  // work. May or may not work for weather.
  public static Map<String, Object> fromJson(BufferedSource jsonStr) throws IOException {
    try {
      Moshi moshi = new Moshi.Builder().build();
      return moshi.adapter(Map.class).fromJson(jsonStr);
    } catch (IOException e) {
      // Still want this to fail noisily and aggressively; something has gone totally, desperately
      // wrong!
      throw e;
    }
  }

  /**
   * Uses Moshi to convert a BufferedSource to some generic class.
   *
   * @param jsonStr The api endpoint to be checked
   * @param generic The class that the data from the url should be converted into
   * @return Something of the class of generic
   * @param <T> Any value
   * @throws IOException
   */
  public static <T> T fromJsonGeneric(BufferedSource jsonStr, Class<T> generic) throws IOException {
    try {
      Moshi moshi = new Moshi.Builder().build();
      return moshi.adapter(generic).fromJson(jsonStr);
    } catch (IOException e) {
      // Still want this to fail noisily and aggressively; something has gone totally, desperately
      // wrong!
      throw e;
    }
  }
}
