import React, {useEffect} from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes
} from "react-router-dom";

// security
import SharedRoutesGuard from "./security/SharedRoutesGuard"
import UserRoutesGuard from "./security/UserRoutesGuard"
import EmployeeRoutesGuard from "./security/EmployeeRoutesGuard"
import AdminRoutesGuard from "./security/AdminRoutesGuard"

// components
import StartPage from "./pages/startPage/StartPage";
import SearchPage from "./pages/searchPage/SearchPage"
import LatestPage from "./pages/latestPage/LatestPage"
import BestsellersPage from "./pages/bestsellersPage/BestsellersPage"
import ContactPage from "./pages/contactPage/ContactPage";
import LoginPage from "./pages/loginPage/LoginPage";
import RegisterPage from "./pages/registerPage/RegisterPage";
import DashboardPage from "./pages/dashboardPage/DashboardPage";

// styles
import './App.css';

const App = () => {
  useEffect(() => {
    // set default language and currency
    localStorage.setItem('user_language', 'EN');
    localStorage.setItem('user_currency', 'USD');
  });
  return (
      <div>
        <Router>
          <Routes>
            <Route path="/" element={<StartPage />}/>
            <Route path="/search" element={<SearchPage />}/>
            <Route path="/latest" element={<LatestPage />}/>
            <Route path="/bestsellers" element={<BestsellersPage />}/>
            <Route path="/contact" element={<ContactPage />}/>
            <Route path="/login" element={<LoginPage />}/>
            <Route path="/register" element={<RegisterPage />}/>
            <Route path="/dashboard" element={<SharedRoutesGuard><DashboardPage/></SharedRoutesGuard>}/>
          </Routes>
        </Router>
      </div>
  );
}

export default App;
