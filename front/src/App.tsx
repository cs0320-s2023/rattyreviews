import React, { useEffect, useState } from "react";
import "./styles/App.css";
import { NavBar } from "./NavBar/NavBar";

function App() {
  return (
    <div className="App">
      <div className="navbar-container">
        <NavBar />
      </div>
      <div></div>
    </div>
  );
}

export default App;
