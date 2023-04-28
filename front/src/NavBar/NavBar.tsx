import React, { useEffect, useState } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import "../styles/NavBar.css";
import { TitleElement } from "./TitleElement";

function NavBar() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<TitleElement />}>
          
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export { NavBar };
