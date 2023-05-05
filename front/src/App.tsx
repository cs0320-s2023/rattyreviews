import React, { useEffect, useState } from "react";
import "./styles/App.css";
import { NavBar } from "./NavBar/NavBar";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import HomePage from "./HomePage/HomePage";
import ReviewPage from "./ReviewMealPage/ReviewPage";
import AboutUs from "./AboutUsPage/AboutUs";

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

function App() {
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
  const [menu, setMenu] = useState(new FullMenuResponse([], [], []));
  useEffect(() => {
    fetch("http://localhost:3232/menus")
      .then((res) => res.json())
      .then((data) => {
        if (isMenuResponse(data)) {
          //TODO: NEED NEED NEED VALIDATION HERE
          let breakfast: Array<FoodItem> = parseMeal(data, "Breakfast");
          let lunch: Array<FoodItem> = parseMeal(data, "Breakfast");
          let dinner: Array<FoodItem> = parseMeal(data, "Breakfast");
          setMenu(new FullMenuResponse(breakfast, lunch, dinner));
        } else {
          console.log("ERROR"); //BETTER HANDLING NEEDED
        }
      })
      .catch((err) => {
        console.log(err.message);
      });
  }, []);
  console.log(menu);
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route index element={<HomePage />}></Route>
          <Route path="review-meal" element={<ReviewPage />}></Route>
          <Route path="about-us" element={<AboutUs />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
