package edu.brown.cs.student.main.Utils;

import edu.brown.cs.student.main.server.MapSerializer;
import okio.Buffer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public class reviewController {

  private final String REVIEW_STORAGE_PATH = "src\\main\\java\\edu\\brown\\cs\\student\\main\\reviewData\\ReviewStore2.json";

  //would love to convert this to some external db at some point, not necessary tho
  //need something like this for title items so we can cross ref when returning menus

  private Map<Integer, Review.foodReview> reviewDictionary;
  private int REVIEW_ID;

  public reviewController() {
    this.reviewDictionary = new Hashtable<>();
    this.REVIEW_ID = 0;
  }

  public int getNumReviews() {
    return this.reviewDictionary.size();
  }

  public Map<Integer, Review.foodReview> getReviewDictionary(){
    return this.reviewDictionary;
  }

  public List<Review.foodReview> getPendingListOfReviews() {
    List<Review.foodReview> res = new ArrayList<>();
    for (Review.foodReview rev : this.reviewDictionary.values()) {
      res.add(rev);
    }
    return res;
  }

  public int getREVIEW_ID(){
    return this.REVIEW_ID;
  }

  //TODO: need to ensure methods are in place to prevent a user from submitting several reviews at once
  public void insertReview(Review.foodReview review) {
    this.reviewDictionary.put(this.REVIEW_ID, review);
    this.REVIEW_ID++;
  }

  public void deleteReview(int Review_ID) {
    this.reviewDictionary.remove(Review_ID);
  }

  public String toString() {
    return "there are " + getNumReviews() + " reviews in the controller";
  }

  public void clearController() {
    this.reviewDictionary.clear();
  }

  public void addToStorage() {
    Path targetPath = Paths.get(REVIEW_STORAGE_PATH);
    try {
      Buffer contentBuff = new Buffer().write(Files.readAllBytes(targetPath));
      Review.listOfFoodReview updatedContent = MapSerializer.fromJsonGeneric(contentBuff, Review.listOfFoodReview.class);

      for (Review.foodReview rev : this.reviewDictionary.values()) {
        updatedContent.reviews().add(rev);
      }
      String res = MapSerializer.toJsonTotalGeneric(updatedContent, Review.listOfFoodReview.class);

      Files.writeString(targetPath, res, Charset.defaultCharset(), TRUNCATE_EXISTING);
      clearController();
    } catch (IOException e) {
      List<Review.foodReview> collectedReviews = new ArrayList<>(this.reviewDictionary.values());
      Review.listOfFoodReview newContent = new Review.listOfFoodReview(collectedReviews);

      String res = MapSerializer.toJsonTotalGeneric(newContent, Review.listOfFoodReview.class);

      try {
        Files.writeString(targetPath, res, Charset.defaultCharset());
        clearController();
      } catch (IOException ex2) {
        //TODO: better err handling
        System.err.println("OH NO");
      }
    }
  }

  public Review.listOfFoodReview getStoredListOfReviews() {
    Path targetPath = Paths.get(REVIEW_STORAGE_PATH);
    try {
      String someSaved = new String(Files.readAllBytes(targetPath));
      return MapSerializer.fromJsonGeneric(new Buffer().writeString(someSaved, Charset.defaultCharset()), Review.listOfFoodReview.class);
    } catch (IOException e) {
      //Want this to return nothing-- maybe there was just a request for a file that doesn't exist yet
      System.err.println("couldn't get stored list!");
      System.err.println(e);
      return new Review.listOfFoodReview(new ArrayList<>());
    }
  }


}
