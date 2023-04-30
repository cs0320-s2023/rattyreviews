import React, { useEffect, useState } from "react";
import "./styles/App.css";
import { NavBar } from "./NavBar/NavBar";
import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<NavBar />}></Route>
          <Route path="review-meal" element={<p>Review Meal</p>}></Route>
          <Route path="about-us" element={<p>About Us</p>}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
