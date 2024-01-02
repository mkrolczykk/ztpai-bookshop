import React, { useState } from 'react';
import { Link } from 'react-router-dom';

import './topbar.css';

const Topbar = () => {
    const [accountDropdownOpen, setAccountDropdownOpen] = useState(false);
    const [currencyDropdownOpen, setCurrencyDropdownOpen] = useState(false);
    const [languageDropdownOpen, setLanguageDropdownOpen] = useState(false);

    const handleDropdownToggle = (dropdown) => {
        switch (dropdown) {
            case 'account':
                setAccountDropdownOpen(!accountDropdownOpen);
                break;
            case 'currency':
                setCurrencyDropdownOpen(!currencyDropdownOpen);
                break;
            case 'language':
                setLanguageDropdownOpen(!languageDropdownOpen);
                break;
            default:
                break;
        }
    };

    return (
        <div className="topbar">
            <div className="topbar-header">
                <div className="top-nav">
                    <ul>
                        <li>
                            <Link to="/">About</Link>
                        </li>
                        <li>
                            <Link to="/contact">Contact</Link>
                        </li>
                        <li>
                            <Link to="/">Help</Link>
                        </li>
                        <li>
                            <Link to="/">FAQs</Link>
                        </li>
                    </ul>
                </div>
                <div className="account-nav">
                    <div className="dropdown" onClick={() => handleDropdownToggle('account')}>
                        <button className="dropbtn">My Account <i className="fa fa-caret-down fa-lg"></i></button>
                        <div className={`dropdown-content ${accountDropdownOpen ? 'show' : ''}`}>
                            {sessionStorage.getItem('authenticated') ? (
                                <>
                                    <Link to="/myaccount">My Account</Link>
                                    <Link to="/logout">Logout</Link>
                                </>
                            ) : (
                                <>
                                    <Link to="/login">Sign in</Link>
                                    <Link to="/register">Sign up</Link>
                                </>
                            )}
                        </div>
                    </div>
                    {/* Similar structure for currency and language dropdowns */}
                </div>
            </div>
        </div>
    );
};

export default Topbar;