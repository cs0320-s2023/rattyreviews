import React, { useEffect, useState } from "react";
import { BrowserRouter, Routes, Route, Link, Outlet } from "react-router-dom";
import "../styles/NavBar.css";

function NavBar() {
  return (
    <div className="navbar-container">
      <div className="navbar">
        <div className="title-container">
          <p className="title">
            <Link
              to="/"
              className="react-link title"
              aria-label="The Ratty Reviews Title Text, which Links to Home Page."
            >
              The Ratty Reviews 🐀
            </Link>
          </p>
        </div>
        <div
          className="nav-button review-button"
          aria-label="Review Meal Button."
        >
          <p className="button-text">
            <Link to="/review-meal" className="react-link">
              Review Meal
            </Link>
          </p>
        </div>
        <div className="nav-button about-button" aria-label="About Us Button.">
          <p className="button-text">
            <Link to="/about-us" className="react-link">
              About Us
            </Link>
          </p>
        </div>
        <Outlet />
      </div>
    </div>
  );
}

export { NavBar };
