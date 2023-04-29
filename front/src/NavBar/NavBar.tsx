import React, { useEffect, useState } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import "../styles/NavBar.css";
import { TitleElement } from "./TitleElement";

function NavBar() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<TitleElement />}>
          <Route index element={<p>Home</p>}></Route>
          <Route path="review-meal" element={<p>Review Meal</p>}></Route>
          <Route path="about-us" element={<p>About Us</p>}></Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export { NavBar };
