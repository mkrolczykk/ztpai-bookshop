import React from 'react';
import { Link } from 'react-router-dom';

import "./encouragement-bar.css";

const EncouragementBar = () => {
    return (
        <div className="encouragement-container">
            <div className="encouragement-text">
                <h2>Log in for more possibilities</h2>
                <p>We'd love to help you find books you'll love.</p>
            </div>
            <div className="encouragement-button button">
                <Link to="/login">Log in</Link>
            </div>
        </div>
    );
};

export default EncouragementBar;
