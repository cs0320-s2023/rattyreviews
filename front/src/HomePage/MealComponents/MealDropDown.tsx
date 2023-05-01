import "../../styles/MealBox.css";
import Meal from "./Meal";

interface MealDropDownProps {
  meal: Meal;
}

function MealDropDown(props: MealDropDownProps) {
  let mealString: String = "Invalid";
  switch (props.meal) {
    case Meal.Breakfast:
      mealString = "Breakfast";
      break;
    case Meal.Lunch:
      mealString = "Lunch";
      break;
    case Meal.Dinner:
      mealString = "Dinner";
      break;
  }
  return (
    <div>
      <div>
        <p>{mealString}</p>
      </div>
    </div>
  );
}

export default MealDropDown;
