package edu.brown.cs.student.main.server;

import static org.junit.jupiter.api.Assertions.*;

import com.squareup.moshi.JsonEncodingException;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import okio.Buffer;
import okio.BufferedSource;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class MapSerializerTest {
  @Before
  public void setup() {}

  @After
  public void tearDown() {}

  @Test
  public void checkToJson() {
    Map<String, Object> someMap = new LinkedHashMap<>();
    someMap.put("test", "map");
    someMap.put("check", "something");
    someMap.put("expects", null);
    // ^ this will not be included in the expected json response!
    someMap.put("listed", Arrays.asList(1, "some", true, "front", 2.4));
    someMap.put("bool", false);

    String resJson = MapSerializer.toJson(someMap);
    String expJson =
        "{\"test\":\"map\",\"check\":\"something\",\"listed\":[1,\"some\",true,\"front\",2.4],\"bool\":false}";

    assertEquals(expJson, resJson);
  }

  @Test
  public void onlyNullToJson() {
    Map<String, Object> someMap = new LinkedHashMap<>();
    someMap.put("none", null);
    someMap.put("of these", null);
    someMap.put("will be added!", null);

    String resJson = MapSerializer.toJson(someMap);

    assertEquals("{}", resJson);
  }

  @Test
  public void malformedDataThrowsFromJson() throws IOException {
    BufferedSource someBuffer =
        new Buffer().readFrom(new StringBufferInputStream("some garbage lol"));
    assertThrows(
        JsonEncodingException.class,
        () -> {
          MapSerializer.fromJson(someBuffer);
        });
  }

  @Test
  public void malformedDataThrowsToJson() throws IOException {
    BufferedSource someBuffer =
        new Buffer().readFrom(new StringBufferInputStream("some garbage lol"));
    Map<String, Object> someMap = new LinkedHashMap<>();
    someMap.put("test", "map");
    someMap.put("check", "something");
    someMap.put("expects", someBuffer);
    assertThrows(
        Exception.class,
        () -> {
          MapSerializer.toJson(someMap);
        });
    // ^ will throw **something**, but unclear what to reasonably expect
  }

  @Test
  public void standardFromJson() throws IOException {
    BufferedSource someBuffer =
        new Buffer()
            .readFrom(
                new StringBufferInputStream(
                    "{\"test\":\"map\",\"punch\":\"up\",\"check\":\"something\",\"listed\":[1,\"some\",true,\"front\",2.4]}"));
    Map<String, Object> someMap = new LinkedHashMap<>();
    someMap.put("test", "map");
    someMap.put("punch", "up");
    someMap.put("check", "something");
    someMap.put("listed", Arrays.asList(1.0, "some", true, "front", 2.4));
    Map<String, Object> resJson = MapSerializer.fromJson(someBuffer);
    assertEquals(someMap, resJson);
  }

  @Test
  public void moreComplexFromJson() throws IOException {
    BufferedSource someBuffer =
        new Buffer()
            .readFrom(
                new StringBufferInputStream(
                    "{\"properties\": {\n"
                        + "        \"updated\": \"2023-03-22T02:25:42+00:00\",\n"
                        + "        \"units\": \"us\",\n"
                        + "        \"forecastGenerator\": \"BaselineForecastGenerator\",\n"
                        + "        \"generatedAt\": \"2023-03-22T03:06:32+00:00\",\n"
                        + "        \"updateTime\": \"2023-03-22T02:25:42+00:00\",\n"
                        + "        \"validTimes\": \"2023-03-21T20:00:00+00:00/P7DT5H\",\n"
                        + "        \"elevation\": {\n"
                        + "            \"unitCode\": \"wmoUnit:m\",\n"
                        + "            \"value\": 456.8952\n"
                        + "        }}}"));

    Map<String, Object> finalMap = new LinkedHashMap<>();
    Map<String, Object> propsMap = new LinkedHashMap<>();
    propsMap.put("updated", "2023-03-22T02:25:42+00:00");
    propsMap.put("units", "us");
    propsMap.put("forecastGenerator", "BaselineForecastGenerator");
    propsMap.put("generatedAt", "2023-03-22T03:06:32+00:00");
    propsMap.put("updateTime", "2023-03-22T02:25:42+00:00");
    propsMap.put("validTimes", "2023-03-21T20:00:00+00:00/P7DT5H");
    Map<String, Object> eleMap = new LinkedHashMap<>();
    eleMap.put("unitCode", "wmoUnit:m");
    eleMap.put("value", "456.8952");
    propsMap.put("elevation", eleMap);
    finalMap.put("properties", propsMap);
    Map<String, Object> resJson = MapSerializer.fromJson(someBuffer);
    // converting to strings because internal object comparison gets tricky here!
    // some comment to test
    assertEquals(finalMap.toString(), resJson.toString());
  }

  @Test
  public void simpleSuccessExample() {
    String expJson = "{\"result\":\"success\",\"something\":[1,\"some\",true,\"front\",2.4]}";
    String resJson =
        MapSerializer.simpleSuccessResponse(
            "something", Arrays.asList(1, "some", true, "front", 2.4));
    assertEquals(expJson, resJson);
  }

  @Test
  public void simpleFailureExample() {
    String resJson = MapSerializer.simpleFailureResponse("error_ohno", "bad news!");
    String expJson = "{\"result\":\"error_ohno\",\"error_ohno\":\"bad news!\"}";
    assertEquals(expJson, resJson);
  }

  @Test
  public void simpleExceptionalExample() {
    Exception holdException = new RuntimeException("oops!");
    String resJson = MapSerializer.exceptionalFailureResponse(holdException);
    String expJson =
        "{\"result\":\"error_unknown\",\"error_unknown\":\""
            + MapSerializer.UNKNOWN_ERROR_MESSAGE
            + "\",\"exceptionAsString\":\"java.lang.RuntimeException: oops!\","
            + "\"exceptionMessage\":\"oops!\",\"exceptionStackTrace\":\""
            + Arrays.stream(holdException.getStackTrace()).toList()
            + "\"}";
    assertEquals(expJson, resJson);
  }
}
