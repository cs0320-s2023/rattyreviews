package edu.brown.cs.student.main.Utils;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class reviewController {

  private Dictionary<Integer, Review> reviewDictionary;
  private int ID;
  private List<Food> foodItems;

  public reviewController() {
    this.reviewDictionary = new Hashtable<>();
    this.foodItems = new ArrayList<>();
    this.ID = 0;
  }

  public int getNumReviews() {
    return this.reviewDictionary.size();
  }

  public void insertReview(Review review) {
    this.reviewDictionary.put(this.ID, review);
    this.ID++;
  }

  public void deleteReview(int Review_ID) {
    this.reviewDictionary.remove(Review_ID);
  }

  public String toString() {
    return "there are " + getNumReviews() + " reviews in the database";
  }

  public void addFoodItem(Food food) {
    foodItems.add(food);
  }

  public int getFoodListSize() {
    return foodItems.size();
  }
}
