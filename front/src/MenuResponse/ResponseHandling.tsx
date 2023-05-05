interface FoodItem {
  title: String;
  description: String;
  rating: Number;
  foodRestrictions: Array<String>;
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

function isMenuResponse(json: any) {
  if (!("menus" in json)) return false;
  return true;
}

export { isMenuResponse, FullMenuResponse };    export type { FoodItem };

