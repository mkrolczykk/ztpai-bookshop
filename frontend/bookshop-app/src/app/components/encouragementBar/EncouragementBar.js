import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

import "./encouragement-bar.css";

const EncouragementBar = () => {
    const navigate = useNavigate();

    const handleLoginClick = () => {
        navigate('/login');
    };

    return (
        <div className="encouragement-container">
            <div className="encouragement-text">
                <h2>Log in for more possibilities</h2>
                <p>We'd love to help you find books you'll love.</p>
            </div>
            <div className="encouragement-button button" onClick={handleLoginClick}>
                Log in
            </div>
        </div>
    );
};

export default EncouragementBar;