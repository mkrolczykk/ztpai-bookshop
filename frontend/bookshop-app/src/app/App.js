import React from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes
} from "react-router-dom";

// import history from './helpers/history';
import RouteGuard from "./components/RouteGuard"

import StartPage from "./pages/startPage/StartPage";
import SearchPageWrapper from "./pages/searchPage/SearchPage"
import NewBooksPage from "./pages/newBooksPage/NewBooksPage"
import BestsellersPage from "./pages/bestsellersPage/BestsellersPage"
import ContactPage from "./pages/contactPage/ContactPage";
import LoginPage from "./pages/loginPage/LoginPage";
import RegisterPage from "./pages/registerPage/RegisterPage";
import DashboardPage from "./pages/dashboardPage/DashboardPage";

import './App.css';

function App() {
  return (
      <div>
        <Router>
          <Routes>
            <Route path="/" element={<StartPage />}/>
            <Route path="/search" element={<SearchPageWrapper />}/>
            <Route path="/newBooks" element={<NewBooksPage />}/>
            <Route path="/bestsellers" element={<BestsellersPage />}/>
            <Route path="/contact" element={<ContactPage />}/>
            <Route path="/login" element={<LoginPage />}/>
            <Route path="/register" element={<RegisterPage />}/>
            <Route path="/dashboard" element={<RouteGuard><DashboardPage/></RouteGuard>}/>
          </Routes>
        </Router>
      </div>
  );
}

export default App;
