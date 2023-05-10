# rattyreviews
**Team Members:** Connor Flick (cflick), Akshay Mehta(amehta48), Yonas Amha (Yamha), Shouri Akarapu (sakarapu)

**Repo:** https://github.com/cs0320-s2023/rattyreviews

 A review service for the main campus dinning hall, Ratty! The website gives the ratty meals (Breakfast, Lunch, and Dinner) a rating from 1-5 based on reviews. Users can rate ratty menu items and leave reviews in order to improve the accuracy of the rating. The website also allows users to view specific types of menus, such as gluten free, vegetarian, vegan, and halal.
 
## Design Choices:
- Frontend:

The file structure desctibes frontend files for the ratty reviews website. The structure includes various node modules such as xmlchars, etc., as well as package-lock.json. The public folder contains static files such as images and the index.html file. The src folder has subfolders for AboutUsPage, HomePage, login, MenuResponse, NavBar, private, and ReviewMealPage, each containing relevant files such as components, parsers, and pages. The styles folder contains css files for various components. The tests folder includes test files for the AboutUs and mockFetchTests. Finally, the main.tsx file is the entry point for the application and the vite.config.ts file is the configuration file for Vite, the frontend build tool.

- Backend:

## Errors/Bugs:

-Review page still allows a user to spam the form with a review.
-Home page has no loading indicator for reviews or menus, causing confusing behavior when loading from the server.

## Tests:
Frontend:
- mockFetchTests.test.tsx:

This test file sets up a mock server using MSW to test the handling of server errors in the application. It uses the Testing Library and Jest to simulate user interactions and check whether the application responds correctly to different scenarios.

- AboutUs.test.tsx:
 
This tests the rendering of an About Us page component. The test uses the render and screen methods from the @testing-library/react library to render the AboutUs component with a BrowserRouter component. The test then checks that the rendered page contains certain expected elements and information, including the correct number of team member cards, the correct team member information displayed on each card, and the presence of a navigation bar and a heading.

Backend:

## Setup and Running the Program:

-Frontend: 

The user needs to have Node.js and npm installed on their system. 
The user can install the prohect dependencies with npm install
To run and start the frontend the user has to input "npm run dev"
To run the test files the user has to input "npm test"





