package edu.brown.cs.student.main.Utils;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class reviewController {

  //would love to convert this to some external db at some point, not necessary tho
  //need something like this for food items so we can cross ref when returning menus

  private Dictionary<Integer, Review> reviewDictionary;
  private int REVIEW_ID;

  public reviewController() {
    this.reviewDictionary = new Hashtable<>();
    this.REVIEW_ID = 0;
  }

  public int getNumReviews() {
    return this.reviewDictionary.size();
  }

  public Dictionary<Integer, Review> getReviewDictionary(){
    return this.reviewDictionary;
  }

  public int getREVIEW_ID(){
    return this.REVIEW_ID;
  }

  public void insertReview(Review review) {
    this.reviewDictionary.put(this.REVIEW_ID, review);
    this.REVIEW_ID++;
  }

  public void deleteReview(int Review_ID) {
    this.reviewDictionary.remove(Review_ID);
  }

  public String toString() {
    return "there are " + getNumReviews() + " reviews in the database";
  }

}
