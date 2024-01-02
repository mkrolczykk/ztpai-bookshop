import React from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
} from "react-router-dom";

// import history from './helpers/history';
import RouteGuard from "./components/RouteGuard"

import StartPage from "./pages/startPage/StartPage";
import LoginPage from "./pages/loginPage/LoginPage";
import RegisterPage from "./pages/registerPage/RegisterPage";

import './App.css';

function App() {
  return (
      <div>
        <Router>
          <Routes>
            <Route path="/" element={<StartPage />}/>
            {/*<Route element={<Navigate to="/" />}/>*/}
            <Route path="/login" element={<LoginPage />}/>
            <Route path="/register" element={<RegisterPage />}/>
            <Route path="/dashboard" element={<RouteGuard />} />
            {/*<Route element={<Navigate to="/login" />}/>*/}
            {/*<Route path="/dashboard" element={<DashboardPage />}/>*/}
            {/*<Route element={<Navigate to="/dashboard"/>}/>*/}
          </Routes>
        </Router>
      </div>
  );
}

export default App;
