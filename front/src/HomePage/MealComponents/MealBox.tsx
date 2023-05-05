import { FullMenuResponse } from "../../MenuResponse/ResponseHandling";
import "./../../styles/MealBox.css";
import Meal from "./Meal";
import MealDropDown from "./MealDropDown";

interface MealBoxProps {
  dateString: String;
  parser: (date: String) => String;
  menu: FullMenuResponse;
}

function MealBox(props: MealBoxProps) {
  return (
    <div className="meal-box-container">
      <div id="meal-box">
        <div className="title-line-container">
          <div className="date-text-container">
            <p className="date-text">{props.parser(props.dateString)}</p>
          </div>
          <div className="info-icon">
            <p>ℹ️</p>
          </div>
        </div>
        <div className="dropdown-container">
          <div className="meals-container">
            <MealDropDown
              meal={Meal.Breakfast}
              score={3}
              meals={props.menu.breakfast}
            />
          </div>
          <div className="meals-container">
            <MealDropDown
              meal={Meal.Lunch}
              score={4}
              meals={props.menu.lunch}
            />
          </div>
          <div className="meals-container">
            <MealDropDown
              meal={Meal.Dinner}
              score={5}
              meals={props.menu.dinner}
            />
          </div>
        </div>
      </div>
    </div>
  );
}

export default MealBox;
