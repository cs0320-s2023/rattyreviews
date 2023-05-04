import React, { useEffect, useState } from "react";
import "./styles/App.css";
import { NavBar } from "./NavBar/NavBar";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import HomePage from "./HomePage/HomePage";
import ReviewPage from "./ReviewMealPage/ReviewPage";
import AboutUs from "./AboutUsPage/AboutUs";

interface MenuResponse {
  menus: Map<String, Object>;
}

interface FullMenuResponse {
  Breakfast: Map<String, Object>;
  Lunch: Map<String, Object>;
  Dinner: Map<String, Object>;
}

function isMenuResponse(json: any): json is MenuResponse {
  if (!("menus" in json)) return false;
  return true;
}

function App() {
  const [menus, setMenus] = useState(new Map<String, Object>());

  useEffect(() => {
    fetch("http://localhost:3232/menus")
      .then((res) => res.json())
      .then((data: MenuResponse) => {
        if (isMenuResponse(data)) {
          setMenus(data.menus);
        } else {
          console.log("ERROR"); //BETTER HANDLING NEEDED
        }
      })
      .catch((err) => {
        console.log(err.message);
      });
  }, []);

  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route index element={<HomePage menus={menus} />}></Route>
          <Route path="review-meal" element={<ReviewPage />}></Route>
          <Route path="about-us" element={<AboutUs />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
