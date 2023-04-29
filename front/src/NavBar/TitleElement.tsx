import { Link, Outlet } from "react-router-dom";
import "../styles/NavBar.css";

function TitleElement() {
  return (
    <div className="navbar">
      <div className="title-container">
        <p className="title">
          <Link to="/" className="react-link title">
            The Ratty Reviews 🐀
          </Link>
        </p>
      </div>
      <div className="nav-button review-button">
        <p className="button-text">
          <Link to="/review-meal" className="react-link">
            Review Meal
          </Link>
        </p>
      </div>
      <div className="nav-button about-button">
        <p className="button-text">
          <Link to="/about-us" className="react-link">
            About Us
          </Link>
        </p>
      </div>
      <Outlet />
    </div>
  );
}

export { TitleElement };
