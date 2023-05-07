class FoodItem {
  title: String;
  description: String;
  rating: number;
  foodRestrictions: Array<String>;

  constructor(
    title: String,
    description: String,
    rating: number,
    foodRestrictions: Array<String>
  ) {
    this.title = title;
    this.description = description;
    this.rating = rating;
    this.foodRestrictions = foodRestrictions;
  }
}

class FullMenuResponse {
  breakfast: Array<FoodItem>;
  lunch: Array<FoodItem>;
  dinner: Array<FoodItem>;

  constructor(
    breakfast: Array<FoodItem>,
    lunch: Array<FoodItem>,
    dinner: Array<FoodItem>
  ) {
    this.breakfast = breakfast;
    this.lunch = lunch;
    this.dinner = dinner;
  }
}

class Review {
  UserID: String;
  time: String;
  item: FoodItem;

  constructor(userID: String, time: String, item: FoodItem) {
    this.UserID = userID;
    this.time = time;
    this.item = item;
  }
}

interface ReviewResponse<T> {
  result: String;
  reviews: Array<T>;
}

function isMenuResponse(json: any) {
  if (!("menus" in json)) return false;
  return true;
}

function isReviewResponse(json: any): json is ReviewResponse<Review> {
  if (!("reviews" in json)) return false;
  return true;
}

function parseMeal(json: any, meal: String) {
  let comfortsEntrees: Array<FoodItem> =
    json["menus"]["" + meal]["menu"]["Comforts, entrees"];
  let comfortsSides: Array<FoodItem> =
    json["menus"]["" + meal]["menu"]["Comforts, sides"];
  let greens: Array<FoodItem> = json["menus"]["" + meal]["menu"]["Greens"];
  let kettles: Array<FoodItem> = json["menus"]["" + meal]["menu"]["Kettles"];
  let sweets: Array<FoodItem> = json["menus"]["" + meal]["menu"]["Sweets"];
  return comfortsEntrees
    .concat(comfortsSides)
    .concat(greens)
    .concat(kettles)
    .concat(sweets);
}

export {
  isMenuResponse,
  FullMenuResponse,
  parseMeal,
  isReviewResponse,
  Review,
  FoodItem,
};
