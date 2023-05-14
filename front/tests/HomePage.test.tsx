import "@testing-library/jest-dom";
import { render, screen } from "@testing-library/react";
import AboutUs from "../src/AboutUsPage/AboutUs";
import React from "react";
import "@testing-library/jest-dom";
import { BrowserRouter } from "react-router-dom";
import App from "./../../front/src/App";

describe("Home page", () => {
  test("Test NavBar Render", () => {
    render(<App></App>);
    const NavBarText = screen.getAllByText("The Ratty Reviews 🐀");
    expect(NavBarText.length).toBe(1);
  });

  test("Test Review Meal Button Render", () => {
    render(<App></App>);
    const ReviewMealButton = screen.getAllByText("Review Meal");
    expect(ReviewMealButton.length).toBe(1);
  });

  test("Test About Us Button Render", () => {
    render(<App></App>);
    const AboutUsButton = screen.getAllByText("About Us");
    expect(AboutUsButton.length).toBe(1);
  });

  test("Test Date Render", () => {
    const date = new Date();
    const dateString =
      date.toString().split(" ")[0] +
      " " +
      date.toString().split(" ")[1] +
      " " +
      date.toString().split(" ")[2];
    render(<App></App>);
    console.log(dateString);
    const dateToday = screen.getAllByText(dateString);
    expect(dateToday.length).toBe(1);
  });

  test("Test Breakfast Dropdown Render", () => {
    render(<App></App>);
    const breakfastScore = screen.getAllByText("Breakfast Score");
    expect(breakfastScore.length).toBe(1);
  });

  test("Test Lunch Dropdown Render", () => {
    render(<App></App>);
    const lunchScore = screen.getAllByText("Lunch Score");
    expect(lunchScore.length).toBe(1);
  });

  test("Test Dinner Dropdown Render", () => {
    render(<App></App>);
    const dinnerScore = screen.getAllByText("Dinner Score");
    expect(dinnerScore.length).toBe(1);
  });

  test("Test Filter Render", () => {
    render(<App></App>);
    const filter = screen.getAllByText("none");
    expect(filter.length).toBe(1);
  });

});
