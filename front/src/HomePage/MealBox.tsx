import React from "react";
import { render } from "react-dom";

interface MealBoxProps {
  dateString: String;
  parser: (date: String) => String;
}

function MealBox(props: MealBoxProps) {
  return (
    <div id="meal-box">
      <div className="title-line-container">
        <div className="date-text-container">
          <p className="date-text">{props.parser(props.dateString)}</p>
        </div>
        <div className="info-icon">
          <p>ℹ️</p>
        </div>
      </div>
    </div>
  );
}

export default MealBox;
