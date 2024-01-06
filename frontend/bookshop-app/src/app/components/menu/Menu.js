import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

import { setAuthToken } from '../../security/setAuthToken';
import API_ENDPOINTS from '../../common/config-test';
import Role from "../../common/constants/Role";

import { faBars, faHeart, faShoppingCart, faCaretDown } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import './menu.css';

const Menu = () => {
    const userRole = localStorage.getItem('user_role');
    const [favoriteBooksCount, setFavoriteBooksCount] = useState(0);
    const [shoppingCartItemCount, setShoppingCartItemCount] = useState(0);
    const [categories, setCategories] = useState([]);
    const [categoriesLoaded, setCategoriesLoaded] = useState(false);

    useEffect(() => {
        // Set auth token in headers
        setAuthToken(localStorage.getItem('auth_token'));
        // Fetch favorite books count and shopping cart item count
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            if (userRole === Role.ROLE_USER) {
                // Fetch favorite books count
                const favoriteBooksResponse = await axios.get(API_ENDPOINTS.favoriteBooksCount);
                const favoriteCount = favoriteBooksResponse.data.result;
                setFavoriteBooksCount(favoriteCount);

                // Fetch shopping cart item count
                const shoppingCartResponse = await axios.get(API_ENDPOINTS.shoppingCartItemCount);
                const cartItemCount = shoppingCartResponse.data.result;
                setShoppingCartItemCount(cartItemCount);
            }

            loadCategories();
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    const loadCategories = async () => {
        try {
            const response = await axios.get(API_ENDPOINTS.genres);
            const categoriesResult = response.data;
            // Sort categories alphabetically
            categoriesResult.sort((a, b) => a.genre.localeCompare(b.genre));
            setCategories(categoriesResult);
            setCategoriesLoaded(true);
        } catch (error) {
            console.error('Error loading categories:', error);
        }
    };

    const handleCategoriesButtonClick = async () => {
        if (!categoriesLoaded) {
            await loadCategories();
        }
    };

    const menuResult = [
        { label: 'Start page', link: '/' },
        { label: 'New books', link: '/latest' },
        { label: 'Bestsellers', link: '/bestsellers' },
        { label: 'Contact', link: '/contact' },
    ];

    const userMenuResult = [
        { label: 'Dashboard', link: '/dashboard' },
        { label: 'Explore books', link: '/explore' },
        { label: 'Favorites', link: '/myFavorites' },
        { label: 'Shopping cart', link: '/shoppingCart' },
    ];

    const employeeMenuResult = [
        { label: 'Orders', link: '/dashboard' },
        { label: 'Add book', link: '/addBook' },
        { label: 'Contact', link: '/contact' },
    ];

    const adminMenuResult = [
        { label: 'Orders', link: '/dashboard' },
        { label: 'Add book', link: '/addBook' },
        { label: 'Add employee', link: '/addEmployee' },
    ];

    const determineMenuResult = () => {
        if (!localStorage.getItem('auth_token')) {
            return menuResult;
        }

        if (userRole === Role.ROLE_USER) {
            return [...menuResult, ...userMenuResult];
        } else if (userRole === Role.ROLE_EMPLOYEE) {
            return employeeMenuResult;
        } else if (userRole === Role.ROLE_ADMIN) {
            return adminMenuResult;
        }
        return menuResult;
    };

    return (
        <div className="menu-container">
            <div className="menu-row">
                <nav className="menu-categories">
                    <a className={`menu-categories-button ${localStorage.getItem('auth_token') && localStorage.getItem('user_role') !== Role.ROLE_USER ? 'disabled' : ''}`} onClick={handleCategoriesButtonClick}>
                        <h2 className="menu-categories-button-title">
                            <FontAwesomeIcon icon={faBars} size="lg" />
                            <p>Categories</p>
                        </h2>
                        <FontAwesomeIcon icon={faCaretDown} size="lg" className="menu-categories-button-icon" />
                    </a>
                    <nav className={`menu-categories-content ${!categoriesLoaded ? 'loading' : ''}`}>
                        {categories.map((category, index) => (
                            <Link key={index} to={`/category?type=${category.genre.toLowerCase().replace(/\s+/g, '-')}`}>
                                {category.genre}
                            </Link>
                        ))}
                    </nav>
                </nav>
                <nav className="menu-navigation">
                    <div className="menu-navigation-pages">
                        {determineMenuResult().map((menu, index) => (
                            <Link key={index} to={menu.link} className="menu-navigation-pages-page">{menu.label}</Link>
                        ))}
                    </div>
                    {(localStorage.getItem('auth_token') &&
                        localStorage.getItem('user_role') === Role.ROLE_USER) && (
                        <div className="menu-navigation-other">
                            <Link to="/myFavorites" className="menu-navigation-other-favourite-books">
                                <FontAwesomeIcon icon={faHeart} size="lg" className="menu-navigation-other-favourite-books-icon" />
                                <span className="menu-navigation-other-favourite-books-value">{favoriteBooksCount}</span>
                            </Link>
                            <Link to="/shoppingCart" className="menu-navigation-other-shopping-card">
                                <FontAwesomeIcon icon={faShoppingCart} size="lg" className="menu-navigation-other-shopping-card-icon" />
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
                            {determineMenuResult().map((menu, index) => (
                                <li key={index}><Link to={menu.link}>{menu.label}</Link></li>
                            ))}
                        </ul>
                    </header>
                </nav>
            </div>
            {/* TODO -> Add script imports or handle functionality directly in React if needed */}
        </div>
    );
};

export default Menu;