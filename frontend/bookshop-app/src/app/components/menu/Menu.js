import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

import './menu.css'

const Menu = () => {
    const [favoriteBooksCount, setFavoriteBooksCount] = useState(0);
    const [shoppingCartItemCount, setShoppingCartItemCount] = useState(0);

    useEffect(() => {
        // Fetch favorite books count and shopping cart item count here (API calls or other logic)
        // For now, set them to 0 as placeholders
        setFavoriteBooksCount(0);
        setShoppingCartItemCount(0);
    }, []); // Empty dependency array to run the effect only once (on mount)

    const menuResult = [
        { label: 'Start page', link: '/' },
        { label: 'New books', link: '/latest' },
        { label: 'Bestsellers', link: '/bestsellers' },
        { label: 'Contact', link: '/contact' },
    ];

    const userMenuResult = [
        { label: 'Dashboard', link: '/userDashboard' },
        { label: 'Explore books', link: '/explore' },
        { label: 'Favorites', link: '/myFavorites' },
        { label: 'Shopping cart', link: '/shoppingCart' },
    ];

    const employeeMenuResult = [
        { label: 'Orders', link: '/employeeDashboard' },
        { label: 'Add book', link: '/addBook' },
        { label: 'Contact', link: '/contact' },
    ];

    const adminMenuResult = [
        { label: 'Orders', link: '/adminDashboard' },
        { label: 'Add book', link: '/addBook' },
        { label: 'Add employee', link: '/addEmployee' },
    ];

    return (
        <div className="menu-container">
            <div className="menu-row">
                {(!sessionStorage.getItem('authenticated') ||
                    (sessionStorage.getItem('authenticated') && sessionStorage.getItem('roleId') === 'user')) ? (
                    <nav className="menu-categories">
                        <a className="menu-categories-button">
                            <h2 className="menu-categories-button-title">
                                <i className="fa fa-bars fa-md"></i>
                                <p>Categories</p>
                            </h2>
                            <i className="fa fa-caret-down fa-lg menu-categories-button-icon"></i>
                        </a>
                        <nav className="menu-categories-content"></nav>
                    </nav>
                ) : (
                    <nav className="menu-categories menu-categories-disabled disabled">
                        <a className="menu-categories-button disabled">
                            <h2 className="menu-categories-button-title disabled">
                                <i className="fa fa-bars fa-md"></i>
                                <p>Categories</p>
                            </h2>
                            <i className="fa fa-caret-down fa-lg menu-categories-button-icon"></i>
                        </a>
                    </nav>
                )}
                <nav className="menu-navigation">
                    <div className="menu-navigation-pages">
                        {(!sessionStorage.getItem('authenticated') ||
                            sessionStorage.getItem('authenticated') && sessionStorage.getItem('roleId') === 'user') ? (
                            menuResult.map((menu, index) => (
                                <Link key={index} to={menu.link} className="menu-navigation-pages-page">{menu.label}</Link>
                            ))
                        ) : (
                            (sessionStorage.getItem('authenticated') && sessionStorage.getItem('roleId') === 'user') ? (
                                userMenuResult.map((menu, index) => (
                                    <Link key={index} to={menu.link} className="menu-navigation-pages-page">{menu.label}</Link>
                                ))
                            ) : (
                                (sessionStorage.getItem('authenticated') && sessionStorage.getItem('roleId') === 'employee') ? (
                                    employeeMenuResult.map((menu, index) => (
                                        <Link key={index} to={menu.link} className="menu-navigation-pages-page">{menu.label}</Link>
                                    ))
                                ) : (
                                    (sessionStorage.getItem('authenticated') && sessionStorage.getItem('roleId') === 'admin') && (
                                        adminMenuResult.map((menu, index) => (
                                            <Link key={index} to={menu.link} className="menu-navigation-pages-page">{menu.label}</Link>
                                        ))
                                    )
                                )
                            )
                        )}
                    </div>
                    {(sessionStorage.getItem('authenticated') && sessionStorage.getItem('roleId') === 'user') && (
                        <div className="menu-navigation-other">
                            <Link to="/myFavorites" className="menu-navigation-other-favourite-books">
                                <i className="fa fa-heart fa-lg menu-navigation-other-favourite-books-icon"></i>
                                <span className="menu-navigation-other-favourite-books-value">{favoriteBooksCount}</span>
                            </Link>
                            <Link to="/shoppingCart" className="menu-navigation-other-shopping-card">
                                <i className="fa fa-shopping-cart fa-lg menu-navigation-other-shopping-card-icon"></i>
                                <span className="menu-navigation-other-shopping-card-value">{shoppingCartItemCount}</span>
                            </Link>
                        </div>
                    )}
                </nav>
                <nav className="menu-mobile">
                    <header className="menu-mobile-header">
                        <Link to="/" className="menu-mobile-header-logo">Book Shop</Link>
                        <input className="menu-mobile-header-menu-btn" type="checkbox" id="menu-btn" />
                        <label className="menu-mobile-header-menu-icon" htmlFor="menu-btn"><span className="navicon"></span></label>
                        <ul className="menu-mobile-header-menu">
                            {(!sessionStorage.getItem('authenticated') ||
                                sessionStorage.getItem('authenticated') && sessionStorage.getItem('roleId') === 'user') ? (
                                menuResult.map((menu, index) => (
                                    <li key={index}><Link to={menu.link}>{menu.label}</Link></li>
                                ))
                            ) : (
                                (sessionStorage.getItem('authenticated') && sessionStorage.getItem('roleId') === 'user') ? (
                                    userMenuResult.map((menu, index) => (
                                        <li key={index}><Link to={menu.link}>{menu.label}</Link></li>
                                    ))
                                ) : (
                                    (sessionStorage.getItem('authenticated') && sessionStorage.getItem('roleId') === 'employee') ? (
                                        employeeMenuResult.map((menu, index) => (
                                            <li key={index}><Link to={menu.link}>{menu.label}</Link></li>
                                        ))
                                    ) : (
                                        (sessionStorage.getItem('authenticated') && sessionStorage.getItem('roleId') === 'admin') && (
                                            adminMenuResult.map((menu, index) => (
                                                <li key={index}><Link to={menu.link}>{menu.label}</Link></li>
                                            ))
                                        )
                                    )
                                )
                            )}
                        </ul>
                    </header>
                </nav>
            </div>
            {/* TODO -> Add script imports or handle functionality directly in React if needed */}
        </div>
    );
};

export default Menu;