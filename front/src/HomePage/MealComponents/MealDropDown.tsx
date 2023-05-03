import "../../styles/MealBox.css";
import Meal from "./Meal";
import { MdArrowDropDown } from "react-icons/Md";

interface MealDropDownProps {
  meal: Meal;
  score: Number;
}

function MealDropDown(props: MealDropDownProps) {
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
    <div className="meal-container">
      <div className="meal-title meal-text">
        <p>{mealString}</p>
      </div>
      <div className="button-score-container">
        <div className="button-score">
          <div className="meal-score meal-text">
            <p>{props.score.toString()}</p>
          </div>
          <div className="dropdown-button-container">
            <button className="dropdown-button">
              <MdArrowDropDown className="dropdown-icon" />
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
export default MealDropDown;
