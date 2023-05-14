package edu.brown.cs.student.main.server;

import edu.brown.cs.student.main.dataStructures.Food;
import edu.brown.cs.student.main.dataStructures.Review;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStructuresTest {
    @Test
    public void testFood(){

        List vegan = new ArrayList<String>();
        vegan.add("vegan");

        List halal = new ArrayList<String>();
        halal.add("");

        List vegetarian = new ArrayList<String>();
        vegetarian.add("vegetarian");

        Food.FoodItem curry = new Food.FoodItem("Curry", "delicious curry", 3.0, vegan);
        Food.FoodItem iceCream = new Food.FoodItem("iceCream", "delicious iceCream", 5, vegetarian);
        Food.FoodItem Brownie = new Food.FoodItem("Brownie", "delicious Brownie", 4.0, halal);
        Food.FoodItem omelet = new Food.FoodItem("Omelet", "delicious omelet", 3.0, new ArrayList<>());
        Food.FoodItem pancake = new Food.FoodItem("Pancake", "delicious pancake", 2.5, new ArrayList<>());
        Food.FoodItem avocadoToast = new Food.FoodItem("avocadoToast", "delicious avocadoToast", 3.5, new ArrayList<>());
        Food.FoodItem pbj = new Food.FoodItem("peanutButterJelly", "delicious curry", 3.0, new ArrayList<>());
        Food.FoodItem plantains = new Food.FoodItem("plantains", "delicious plantains", 5.0, new ArrayList<>());
        Food.FoodItem frenchToast = new Food.FoodItem("frenchToast", "delicious french Toast", 3.0, new ArrayList<>());

        Assert.assertEquals(curry.description(), "delicious curry");
        Assert.assertEquals(iceCream.description(), "delicious iceCream");
        Assert.assertEquals(Brownie.description(), "delicious Brownie");
        Assert.assertEquals(omelet.description(), "delicious omelet");
        Assert.assertEquals(pancake.description(), "delicious pancake");
        Assert.assertEquals(avocadoToast.description(), "delicious avocadoToast");
        Assert.assertEquals(pbj.description(), "delicious curry");
        Assert.assertEquals(plantains.description(), "delicious plantains");
        Assert.assertEquals(frenchToast.description(), "delicious french Toast");


        List<Food.FoodItem> foodItemList = new ArrayList<>();
        foodItemList.add(curry);
        foodItemList.add(iceCream);
        foodItemList.add(Brownie);
        foodItemList.add(omelet);
        foodItemList.add(pancake);
        foodItemList.add(avocadoToast);
        foodItemList.add(pbj);
        foodItemList.add(plantains);
        foodItemList.add(frenchToast);

        Map<String, List<Food.FoodItem>> currentMenu = new HashMap<>();
        Food.Menu breakfast = new Food.Menu(currentMenu);
        Map<String, Food.Menu> foodMenus = new HashMap<>();
        foodMenus.put("breakfast", breakfast);
        Food.FullMenuResponse menuResponse = new Food.FullMenuResponse("success", "5-22-23", foodMenus);
        Assert.assertEquals(menuResponse.menus(), foodMenus);
    }

    @Test
    public void testEquals(){
        List<String> vegan = new ArrayList<String>();
        vegan.add("vegan");
        List<String> halal = new ArrayList<String>();
        halal.add("");
        List vegetarian = new ArrayList<String>();
        vegetarian.add("vegetarian");
        Food.FoodItem iceCream1 = new Food.FoodItem("iceCream", "delicious iceCream", 5, vegan);
        Food.FoodItem iceCream2 = new Food.FoodItem("iceCream", "delicious iceCream", 5, vegan);
        Assert.assertEquals(iceCream2.equals(iceCream1), true);
        Food.FoodItem iceCream3 = new Food.FoodItem("iceCream", "delicious iceCream", 5, vegan);
        Food.FoodItem iceCream4 = new Food.FoodItem("iceCream", "delicious iceCream", 1, vegan);
        Assert.assertEquals(iceCream3.equals(iceCream4), true);
        Food.FoodItem iceCream5 = new Food.FoodItem("iceCream", "delicious iceCream", 5, vegan);
        Food.FoodItem iceCream6 = new Food.FoodItem("iceCream", "delicious iceCreams", 5, vegan);
        Assert.assertEquals(iceCream5.equals(iceCream6), false);
        Food.FoodItem iceCream7 = new Food.FoodItem("iceCream", "delicious iceCream", 5, vegan);
        Food.FoodItem iceCream8 = new Food.FoodItem("iceCream", "delicious iceCreams", 5, vegetarian);
        Assert.assertEquals(iceCream7.equals(iceCream8), false);
        Food.FoodItem iceCream9 = new Food.FoodItem("iceCream", "delicious iceCream", 5, vegan);
        Food.FoodItem iceCream10 = new Food.FoodItem("iceCreams", "delicious iceCream", 5, vegan);
        Assert.assertEquals(iceCream9.equals(iceCream10), false);
    }

    @Test
    public void testReview(){
        List<String> vegan = new ArrayList<String>();
        vegan.add("vegan");
        List<String> halal = new ArrayList<String>();
        halal.add("");
        List vegetarian = new ArrayList<String>();
        vegetarian.add("vegetarian");
        Food.FoodItem iceCream1 = new Food.FoodItem("iceCream", "delicious iceCream", 5, vegan);
        Food.FoodItem iceCream2 = new Food.FoodItem("iceCream", "delicious iceCream", 5, vegan);
        Assert.assertEquals(iceCream2.equals(iceCream1), true);
        Food.FoodItem iceCream3 = new Food.FoodItem("iceCream", "delicious iceCream", 5, vegan);
        Food.FoodItem iceCream4 = new Food.FoodItem("iceCream", "delicious iceCream", 1, vegan);
        Assert.assertEquals(iceCream3.equals(iceCream4), true);
        Food.FoodItem iceCream5 = new Food.FoodItem("iceCream", "delicious iceCream", 5, vegan);
        Food.FoodItem iceCream6 = new Food.FoodItem("iceCream", "delicious iceCreams", 5, vegan);
        Assert.assertEquals(iceCream5.equals(iceCream6), false);
        Food.FoodItem iceCream7 = new Food.FoodItem("iceCream", "delicious iceCream", 5, vegan);
        Food.FoodItem iceCream8 = new Food.FoodItem("iceCream", "delicious iceCreams", 5, vegetarian);
        Assert.assertEquals(iceCream7.equals(iceCream8), false);
        Food.FoodItem iceCream9 = new Food.FoodItem("iceCream", "delicious iceCream", 5, vegan);
        Food.FoodItem iceCream10 = new Food.FoodItem("iceCreams", "delicious iceCream", 5, vegan);
        Assert.assertEquals(iceCream9.equals(iceCream10), false);


        Review.foodReview user1 = new Review.foodReview("user1", "5-10-2023", iceCream1, "Awesome");
        Review.foodReview user2 = new Review.foodReview("user2", "5-10-2023", iceCream2, "Great Ice Cream");
        Review.foodReview user3 = new Review.foodReview("user3", "5-10-2023", iceCream3, "The ice Cream was ok");
        Review.foodReview user4 = new Review.foodReview("user4", "5-09-2023", iceCream4, "Very Nice");
        Review.foodReview user5 = new Review.foodReview("user5", "5-08-2023", iceCream5, "Chocolate Flakes :)");

        Review.foodReview user6 = new Review.foodReview("user1", "5-10-2023", iceCream1, "Awesome");
        Review.foodReview user7 = new Review.foodReview("user2", "5-10-2023", iceCream2, "Great Ice Cream");
        Review.foodReview user8 = new Review.foodReview("user3", "5-10-2023", iceCream3, "The ice Cream was ok");
        Review.foodReview user9 = new Review.foodReview("user4", "5-09-2023", iceCream4, "Very Nice");
        Review.foodReview user10 = new Review.foodReview("user5", "5-08-2023", iceCream5, "Chocolate Flakes :)");

        Review.foodReview user11 = new Review.foodReview("user1", "5-10-2023", iceCream1, "Awesome");
        Review.foodReview user12 = new Review.foodReview("user2", "5-10-2023", iceCream2, "Great Ice Cream");
        Review.foodReview user13 = new Review.foodReview("user3", "5-10-2023", iceCream3, "The ice Cream was ok");
        Review.foodReview user14 = new Review.foodReview("user4", "5-09-2023", iceCream4, "Very Nice");
        Review.foodReview user15 = new Review.foodReview("user5", "5-08-2023", iceCream5, "Chocolate Flakes :)");

        Review.foodReview user16 = new Review.foodReview("user1", "5-10-2023", iceCream1, "Awesome");
        Review.foodReview user17 = new Review.foodReview("user2", "5-10-2023", iceCream2, "Great Ice Cream");
        Review.foodReview user18 = new Review.foodReview("user3", "5-10-2023", iceCream3, "The ice Cream was ok");
        Review.foodReview user19 = new Review.foodReview("user4", "5-09-2023", iceCream4, "Very Nice");
        Review.foodReview user20 = new Review.foodReview("user5", "5-08-2023", iceCream5, "Chocolate Flakes :)");

        Review.foodReview user21 = new Review.foodReview("user1", "5-10-2023", iceCream1, "Awesome");
        Review.foodReview user22 = new Review.foodReview("user2", "5-10-2023", iceCream2, "Great Ice Cream");
        Review.foodReview user23 = new Review.foodReview("user3", "5-10-2023", iceCream3, "The ice Cream was ok");
        Review.foodReview user24 = new Review.foodReview("user4", "5-09-2023", iceCream4, "Very Nice");
        Review.foodReview user25 = new Review.foodReview("user5", "5-08-2023", iceCream5, "Chocolate Flakes :)");

        Review.foodReview user26 = new Review.foodReview("user1", "5-10-2023", iceCream1, "Awesome");
        Review.foodReview user27 = new Review.foodReview("user2", "5-10-2023", iceCream2, "Great Ice Cream");
        Review.foodReview user28 = new Review.foodReview("user3", "5-10-2023", iceCream3, "The ice Cream was ok");
        Review.foodReview user29 = new Review.foodReview("user4", "5-09-2023", iceCream4, "Very Nice");
        Review.foodReview user30 = new Review.foodReview("user5", "5-08-2023", iceCream5, "Chocolate Flakes :)");

        Review.foodReview user31 = new Review.foodReview("user1", "5-10-2023", iceCream1, "Awesome");
        Review.foodReview user32 = new Review.foodReview("user2", "5-10-2023", iceCream2, "Great Ice Cream");
        Review.foodReview user33 = new Review.foodReview("user3", "5-10-2023", iceCream3, "The ice Cream was ok");
        Review.foodReview user34 = new Review.foodReview("user4", "5-09-2023", iceCream4, "Very Nice");
        Review.foodReview user35 = new Review.foodReview("user5", "5-08-2023", iceCream5, "Chocolate Flakes :)");

        Review.foodReview user36 = new Review.foodReview("user1", "5-10-2023", iceCream1, "Awesome");
        Review.foodReview user37 = new Review.foodReview("user2", "5-10-2023", iceCream2, "Great Ice Cream");
        Review.foodReview user38 = new Review.foodReview("user3", "5-10-2023", iceCream3, "The ice Cream was ok");
        Review.foodReview user39 = new Review.foodReview("user4", "5-09-2023", iceCream4, "Very Nice");
        Review.foodReview user40 = new Review.foodReview("user5", "5-08-2023", iceCream5, "Chocolate Flakes :)");

        Review.foodReview user41 = new Review.foodReview("user1", "5-10-2023", iceCream1, "Awesome");
        Review.foodReview user42 = new Review.foodReview("user2", "5-10-2023", iceCream2, "Great Ice Cream");
        Review.foodReview user43 = new Review.foodReview("user3", "5-10-2023", iceCream3, "The ice Cream was ok");
        Review.foodReview user44 = new Review.foodReview("user4", "5-09-2023", iceCream4, "Very Nice");
        Review.foodReview user45 = new Review.foodReview("user5", "5-08-2023", iceCream5, "Chocolate Flakes :)");

        Review.foodReview user46 = new Review.foodReview("user1", "5-10-2023", iceCream1, "Awesome");
        Review.foodReview user47 = new Review.foodReview("user2", "5-10-2023", iceCream2, "Great Ice Cream");
        Review.foodReview user48 = new Review.foodReview("user3", "5-10-2023", iceCream3, "The ice Cream was ok");
        Review.foodReview user49 = new Review.foodReview("user4", "5-09-2023", iceCream4, "Very Nice");
        Review.foodReview user50 = new Review.foodReview("user5", "5-08-2023", iceCream5, "Chocolate Flakes :)");

        Review.foodReview user51 = new Review.foodReview("user1", "5-10-2023", iceCream1, "Awesome");
        Review.foodReview user52 = new Review.foodReview("user2", "5-10-2023", iceCream2, "Great Ice Cream");
        Review.foodReview user53 = new Review.foodReview("user3", "5-10-2023", iceCream3, "The ice Cream was ok");
        Review.foodReview user54 = new Review.foodReview("user4", "5-09-2023", iceCream4, "Very Nice");
        Review.foodReview user55 = new Review.foodReview("user5", "5-08-2023", iceCream5, "Chocolate Flakes :)");

        Review.foodReview user56 = new Review.foodReview("user1", "5-10-2023", iceCream1, "Awesome");
        Review.foodReview user57 = new Review.foodReview("user2", "5-10-2023", iceCream2, "Great Ice Cream");
        Review.foodReview user58 = new Review.foodReview("user3", "5-10-2023", iceCream3, "The ice Cream was ok");
        Review.foodReview user59 = new Review.foodReview("user4", "5-09-2023", iceCream4, "Very Nice");
        Review.foodReview user60 = new Review.foodReview("user5", "5-08-2023", iceCream5, "Chocolate Flakes :)");

        Review.foodReview user61 = new Review.foodReview("user1", "5-10-2023", iceCream1, "Awesome");
        Review.foodReview user62 = new Review.foodReview("user2", "5-10-2023", iceCream2, "Great Ice Cream");
        Review.foodReview user63 = new Review.foodReview("user3", "5-10-2023", iceCream3, "The ice Cream was ok");
        Review.foodReview user64 = new Review.foodReview("user4", "5-09-2023", iceCream4, "Very Nice");
        Review.foodReview user65 = new Review.foodReview("user5", "5-08-2023", iceCream5, "Chocolate Flakes :)");

        Review.foodReview user66 = new Review.foodReview("user1", "5-10-2023", iceCream1, "Awesome");
        Review.foodReview user67 = new Review.foodReview("user2", "5-10-2023", iceCream2, "Great Ice Cream");
        Review.foodReview user68 = new Review.foodReview("user3", "5-10-2023", iceCream3, "The ice Cream was ok");
        Review.foodReview user69 = new Review.foodReview("user4", "5-09-2023", iceCream4, "Very Nice");
        Review.foodReview user70 = new Review.foodReview("user5", "5-08-2023", iceCream5, "Chocolate Flakes :)");

        Review.foodReview user71 = new Review.foodReview("user1", "5-10-2023", iceCream1, "Awesome");
        Review.foodReview user72 = new Review.foodReview("user2", "5-10-2023", iceCream2, "Great Ice Cream");
        Review.foodReview user73 = new Review.foodReview("user3", "5-10-2023", iceCream3, "The ice Cream was ok");
        Review.foodReview user74 = new Review.foodReview("user4", "5-09-2023", iceCream4, "Very Nice");
        Review.foodReview user75 = new Review.foodReview("user5", "5-08-2023", iceCream5, "Chocolate Flakes :)");

        Review.foodReview user76 = new Review.foodReview("user1", "5-10-2023", iceCream1, "Awesome");
        Review.foodReview user77 = new Review.foodReview("user2", "5-10-2023", iceCream2, "Great Ice Cream");
        Review.foodReview user78 = new Review.foodReview("user3", "5-10-2023", iceCream3, "The ice Cream was ok");
        Review.foodReview user79 = new Review.foodReview("user4", "5-09-2023", iceCream4, "Very Nice");
        Review.foodReview user80 = new Review.foodReview("user5", "5-08-2023", iceCream5, "Chocolate Flakes :)");

        Review.foodReview user81 = new Review.foodReview("user1", "5-10-2023", iceCream1, "Awesome");
        Review.foodReview user82 = new Review.foodReview("user2", "5-10-2023", iceCream2, "Great Ice Cream");
        Review.foodReview user83 = new Review.foodReview("user3", "5-10-2023", iceCream3, "The ice Cream was ok");
        Review.foodReview user84 = new Review.foodReview("user4", "5-09-2023", iceCream4, "Very Nice");
        Review.foodReview user85 = new Review.foodReview("user5", "5-08-2023", iceCream5, "Chocolate Flakes :)");

        Review.foodReview user86 = new Review.foodReview("user1", "5-10-2023", iceCream1, "Awesome");
        Review.foodReview user87 = new Review.foodReview("user2", "5-10-2023", iceCream2, "Great Ice Cream");
        Review.foodReview user88 = new Review.foodReview("user3", "5-10-2023", iceCream3, "The ice Cream was ok");
        Review.foodReview user89 = new Review.foodReview("user4", "5-09-2023", iceCream4, "Very Nice");
        Review.foodReview user90 = new Review.foodReview("user5", "5-08-2023", iceCream5, "Chocolate Flakes :)");

        Review.foodReview user91 = new Review.foodReview("user1", "5-10-2023", iceCream1, "Awesome");
        Review.foodReview user92 = new Review.foodReview("user2", "5-10-2023", iceCream2, "Great Ice Cream");
        Review.foodReview user93 = new Review.foodReview("user3", "5-10-2023", iceCream3, "The ice Cream was ok");
        Review.foodReview user94 = new Review.foodReview("user4", "5-09-2023", iceCream4, "Very Nice");
        Review.foodReview user95 = new Review.foodReview("user5", "5-08-2023", iceCream5, "Chocolate Flakes :)");

        Review.foodReview user96 = new Review.foodReview("user1", "5-10-2023", iceCream1, "Awesome");
        Review.foodReview user97 = new Review.foodReview("user2", "5-10-2023", iceCream2, "Great Ice Cream");
        Review.foodReview user98 = new Review.foodReview("user3", "5-10-2023", iceCream3, "The ice Cream was ok");
        Review.foodReview user99 = new Review.foodReview("user4", "5-09-2023", iceCream4, "Very Nice");
        Review.foodReview user100 = new Review.foodReview("user5", "5-08-2023", iceCream5, "Chocolate Flakes :)");

        List<Review.foodReview> reviewList = new ArrayList<>();
        reviewList.add(user1);
        reviewList.add(user2);
        reviewList.add(user3);
        reviewList.add(user4);
        reviewList.add(user5);
        reviewList.add(user6);
        reviewList.add(user7);
        reviewList.add(user8);
        reviewList.add(user9);
        reviewList.add(user10);
        reviewList.add(user11);
        reviewList.add(user12);
        reviewList.add(user13);
        reviewList.add(user14);
        reviewList.add(user15);
        reviewList.add(user16);
        reviewList.add(user17);
        reviewList.add(user18);
        reviewList.add(user19);
        reviewList.add(user20);
        reviewList.add(user21);
        reviewList.add(user22);
        reviewList.add(user23);
        reviewList.add(user24);
        reviewList.add(user25);
        reviewList.add(user26);
        reviewList.add(user27);
        reviewList.add(user28);
        reviewList.add(user29);
        reviewList.add(user30);
        reviewList.add(user31);
        reviewList.add(user32);
        reviewList.add(user33);
        reviewList.add(user34);
        reviewList.add(user35);
        reviewList.add(user36);
        reviewList.add(user37);
        reviewList.add(user38);
        reviewList.add(user39);
        reviewList.add(user40);
        reviewList.add(user41);
        reviewList.add(user42);
        reviewList.add(user43);
        reviewList.add(user44);
        reviewList.add(user45);
        reviewList.add(user46);
        reviewList.add(user47);
        reviewList.add(user48);
        reviewList.add(user49);
        reviewList.add(user50);
        reviewList.add(user51);
        reviewList.add(user52);
        reviewList.add(user53);
        reviewList.add(user54);
        reviewList.add(user55);
        reviewList.add(user56);
        reviewList.add(user57);
        reviewList.add(user58);
        reviewList.add(user59);
        reviewList.add(user60);
        reviewList.add(user61);
        reviewList.add(user62);
        reviewList.add(user63);
        reviewList.add(user64);
        reviewList.add(user65);
        reviewList.add(user66);
        reviewList.add(user67);
        reviewList.add(user68);
        reviewList.add(user69);
        reviewList.add(user70);
        reviewList.add(user70);
        reviewList.add(user71);
        reviewList.add(user72);
        reviewList.add(user73);
        reviewList.add(user74);
        reviewList.add(user75);
        reviewList.add(user76);
        reviewList.add(user77);
        reviewList.add(user78);
        reviewList.add(user79);
        reviewList.add(user80);
        reviewList.add(user81);
        reviewList.add(user82);
        reviewList.add(user83);
        reviewList.add(user84);
        reviewList.add(user85);
        reviewList.add(user86);
        reviewList.add(user87);
        reviewList.add(user88);
        reviewList.add(user89);
        reviewList.add(user90);
        reviewList.add(user91);
        reviewList.add(user92);
        reviewList.add(user93);
        reviewList.add(user94);
        reviewList.add(user95);
        reviewList.add(user96);
        reviewList.add(user97);
        reviewList.add(user98);
        reviewList.add(user99);
        reviewList.add(user100);

        Review.listOfFoodReview ofFoodReview = new Review.listOfFoodReview(reviewList);
        Assert.assertEquals(ofFoodReview.reviews(), reviewList);
        Assert.assertEquals(reviewList.size(), 100);

    }
}
