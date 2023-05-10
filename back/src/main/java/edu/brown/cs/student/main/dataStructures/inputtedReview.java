package edu.brown.cs.student.main.dataStructures;

import java.util.List;

public class inputtedReview {
    public record SummaryReview (String UserID, String Date, List<Food.FoodItem> Ratings, String comment) {}
}
