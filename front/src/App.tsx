import React, { useEffect, useState } from "react";
import "./styles/App.css";
import { NavBar } from "./NavBar/NavBar";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import HomePage from "./HomePage/HomePage";
import ReviewPage from "./ReviewMealPage/ReviewPage";
import AboutUs from "./AboutUsPage/AboutUs";
import {
  FoodItem,
  FullMenuResponse,
  isMenuResponse,
  parseMeal,
} from "./MenuResponse/ResponseHandling";
import LoginPage from "./login/loginPage";
import { GoogleOAuthProvider } from "@react-oauth/google";
import googClientID from "./private/googClientID";

function App() {
  //TODO: CLEAN THIS PARSING UP ASAP
  const [menu, setMenu] = useState(new FullMenuResponse([], [], []));
  const [date, setDate] = useState(new Date());
  useEffect(() => {
    let dateString = date.toISOString().split("T")[0];
    fetch("http://localhost:3232/menus?date=" + dateString)
      .then((res) => res.json())
      .then((data) => {
        if (isMenuResponse(data)) {
          //TODO: NEED NEED NEED VALIDATION HERE
          let breakfast: Array<FoodItem> = parseMeal(data, "Breakfast");
          let lunch: Array<FoodItem> = parseMeal(data, "Lunch");
          let dinner: Array<FoodItem> = parseMeal(data, "Dinner");
          setMenu(new FullMenuResponse(breakfast, lunch, dinner));
        } else {
          console.log("ERROR"); //BETTER HANDLING NEEDED
        }
      })
      .catch((err) => {
        console.log(err.message);
      });
  }, [date]);
  return (
    <div className="App">
      <GoogleOAuthProvider clientId={googClientID}>
        <BrowserRouter>
          <Routes>
            <Route
              index
              element={<HomePage menu={menu} setDate={setDate} date={date} />}
            ></Route>
            <Route path="review-meal" element={<ReviewPage />}></Route>
            <Route path="about-us" element={<AboutUs />}></Route>
            <Route path="loginTest" element={<LoginPage />}></Route>
          </Routes>
        </BrowserRouter>
      </GoogleOAuthProvider>
    </div>
  );
}

export default App;
