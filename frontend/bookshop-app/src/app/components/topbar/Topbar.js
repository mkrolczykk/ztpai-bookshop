import React, { useState } from 'react';
import { Link } from 'react-router-dom';

import LogoutHelper from '../../security/logoutHelper';

import './topbar.css';

const Topbar = () => {
    const [accountDropdownOpen, setAccountDropdownOpen] = useState(false);
    const [currencyDropdownOpen, setCurrencyDropdownOpen] = useState(false);
    const [languageDropdownOpen, setLanguageDropdownOpen] = useState(false);

    const { handleLogout } = LogoutHelper();

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
                            {localStorage.getItem('auth_token') ? (
                                <>
                                    <Link to="/myaccount">My Account</Link>
                                    <Link to="/login" onClick={handleLogout}>Logout</Link>
                                </>
                            ) : (
                                <>
                                    <Link to="/login">Sign in</Link>
                                    <Link to="/register">Sign up</Link>
                                </>
                            )}
                        </div>
                    </div>
                    <div className="dropdown" onClick={() => handleDropdownToggle('currency')}>
                        <button id="dropdown-currency" className="dropbtn" disabled>USD
                            <i className="fa fa-caret-down fa-lg"></i>
                        </button>
                        <div className="dropdown-content" id="currency">
                            <a>PLN</a>
                            <a>EUR</a>
                        </div>
                    </div>
                    <div className="dropdown" onClick={() => handleDropdownToggle('language')}>
                        <button id="dropdown-language" className="dropbtn" disabled>EN
                            <i className="fa fa-caret-down fa-lg"></i>
                        </button>
                        <div className="dropdown-content" id="language">
                            <a>PL</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Topbar;