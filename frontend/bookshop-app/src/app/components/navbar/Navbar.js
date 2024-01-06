import React from 'react';
import { Link } from 'react-router-dom';

import Role from '../../common/constants/Role';

import "./navbar.css"

import { faSearch } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

const Navbar = () => {
    const redirectUrl = localStorage.getItem('auth_token')
        ? (() => {
            switch (localStorage.getItem('user_role')) {
                case Role.ROLE_USER || Role.ROLE_EMPLOYEE || Role.ROLE_ADMIN:
                    return '/dashboard';
                default:
                    return '/';
            }
        })()
        : '/';

    const roleText = (() => {
        if (!localStorage.getItem('auth_token')) {
            return 'Book Shop';
        } else {
            switch (localStorage.getItem('user_role')) {
                case Role.ROLE_USER:
                    return 'Book Shop';
                case Role.ROLE_EMPLOYEE:
                    return 'Employee Panel';
                case Role.ROLE_ADMIN:
                    return 'Admin Panel';
                default:
                    return '';
            }
        }
    })();

    const isUserStaff = localStorage.getItem('user_role') === Role.ROLE_EMPLOYEE || localStorage.getItem('user_role') === Role.ROLE_ADMIN;

    return (
        <div className="navbar">
            <div className="navbar-logo">
                <Link to={redirectUrl}>
                    <span>{roleText}</span>
                </Link>
            </div>
            <div className={`navbar-search ${isUserStaff ? 'navbar-search-disabled blocked' : ''}`}>
                <form className="navbar-search-form" action="search" method="GET">
                    <div className="navbar-search-box">
                        <input name="searchkey" type="text" placeholder="Search for title, author" disabled={isUserStaff} />
                        <input type="hidden" name="currency" value={localStorage.getItem('user_currency')} />
                        <button type="submit" disabled={isUserStaff}>
                            <FontAwesomeIcon icon={faSearch} className="navbar-search-icon" />
                        </button>
                    </div>
                </form>
            </div>
            <div className="customer-service">
                <div className="phone-number">
                    <p>Customer Support</p>
                    <h5>+18 26 248 54</h5>
                </div>
            </div>
        </div>
    );
};

export default Navbar;