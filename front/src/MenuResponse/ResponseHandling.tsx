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

export { isMenuResponse, FullMenuResponse, parseMeal };
export type { FoodItem };
