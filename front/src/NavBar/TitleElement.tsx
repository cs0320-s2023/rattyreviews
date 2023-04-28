import "../styles/NavBar.css";

function TitleElement() {
  return (
    <div className="navbar">
      <div className="title-container">
        <p className="title">The Ratty Reviews 🐀</p>
      </div>
      <div className="nav-button review-button">
        <p className="button-text">Review Meal</p>
      </div>
      <div className="nav-button about-button">
        <p className="button-text">About Us</p>
      </div>
    </div>
  );
}

export { TitleElement };
