import { useState } from "react";
import { FoodItem, Review } from "../../MenuResponse/ResponseHandling";
import "../../styles/MealBox.css";
import Meal from "./Meal";
import { MdArrowDropDown } from "react-icons/Md";
import { FoodItemBox } from "./FoodItemBox";
import { WilsonScore } from "./ScoreCalculators/WilsonScore";

interface MealDropDownProps {
  meal: Meal;
  score: String;
  meals: Array<FoodItem>;
  reviews: Array<Review>;
}

function MealDropDown(props: MealDropDownProps) {
  const [visibility, setVisibility] = useState(true);
  let mealString: String = "Invalid";
  switch (props.meal) {
    case Meal.Breakfast:
      mealString = "Breakfast Score";
      break;
    case Meal.Lunch:
      mealString = "Lunch Score";
      break;
    case Meal.Dinner:
      mealString = "Dinner Score";
      break;
  }
  return (
    <div className="all-meals-container">
      <div className="meal-container">
        <div className="meal-title meal-text">
          <p>{mealString}</p>
        </div>
        <div className="button-score-container">
          <div className="button-score">
            <div className="meal-score meal-text">
              <p>{props.score}</p>
            </div>
            <div className="dropdown-button-container">
              <button
                className="dropdown-button"
                onClick={() => setVisibility(!visibility)}
                style={{
                  transform: visibility ? "rotate(-90deg)" : "rotate(0)",
                }}
              >
                <MdArrowDropDown className="dropdown-icon" />
              </button>
            </div>
          </div>
        </div>
      </div>
      {props.meals.map((item: FoodItem) => {
        let score = WilsonScore(props.reviews, item.title);
        return (
          <FoodItemBox
            score={score != -1 ? score : -1}
            item={item}
            visibility={visibility}
          />
        );
      })}
    </div>
  );
}
export default MealDropDown;
