import React from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
} from "react-router-dom";

import StartPage from "./startPage/StartPage";
// styles
import './App.css';

const styles = {
  app: {
    boxSizing: "border-box",
    fontSize: "17px",
    margin: 0,
    padding: 0,
  },
};

function App() {
  return (
      <div style={styles.app}>
        <Router>
          <Routes>
            {/*<Route path="/dashboard" exact>*/}
            {/*  <Dashboard />*/}
            {/*</Route>*/}
            <Route path="/" element={<StartPage />}/>
            <Route element={<Navigate to="/" />}/>
          </Routes>
        </Router>
      </div>
  );
}

export default App;
