import React from 'react';
import { Link } from 'react-router-dom';

import Role from '../../common/constants/Role';

import "./navbar.css"

const Navbar = () => {
    const redirectUrl = sessionStorage.getItem('authenticated')
        ? (() => {
            switch (sessionStorage.getItem('roleId')) {
                case Role.ROLE_USER:
                    return '/userDashboard';
                case Role.ROLE_EMPLOYEE:
                    return '/employeeDashboard';
                case Role.ROLE_ADMIN:
                    return '/adminDashboard';
                default:
                    return '/';
            }
        })()
        : '/';

    const roleText = (() => {
        if (!sessionStorage.getItem('authenticated')) {
            return 'Book Shop';
        } else {
            switch (sessionStorage.getItem('roleId')) {
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

    const isUserAuthenticated = sessionStorage.getItem('authenticated');
    const isUserRoleUser = isUserAuthenticated && sessionStorage.getItem('roleId') === Role.ROLE_USER;

    return (
        <div className="navbar">
            <div className="navbar-logo">
                <Link to={redirectUrl}>
                    <span>{roleText}</span>
                </Link>
            </div>
            {(!isUserAuthenticated || isUserRoleUser) && (
                <div className={`navbar-search ${isUserRoleUser ? '' : 'navbar-search-disabled blocked'}`}>
                    <form className="navbar-search-form" action="search" method="POST">
                        <div className="navbar-search-box">
                            <input name="searchkey" type="text" placeholder="Search for title, author" disabled={!isUserRoleUser} />
                            <input type="hidden" name="currency" value="USD" />
                            <button type="submit" disabled={!isUserRoleUser}>
                                <i className="fa fa-search navbar-search-icon"></i>
                            </button>
                        </div>
                    </form>
                </div>
            )}
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