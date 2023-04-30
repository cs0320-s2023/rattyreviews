import React, { useEffect, useState } from "react";
import "./styles/App.css";
import { NavBar } from "./NavBar/NavBar";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import HomePage from "./HomePage/HomePage";
import ReviewPage from "./ReviewMealPage/ReviewPage";
import AboutUs from "./AboutUsPage/AboutUs";

function App() {
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
