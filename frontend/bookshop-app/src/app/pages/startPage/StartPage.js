import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

import Role from '../../common/constants/Role';
import API_ENDPOINTS from '../../common/config-test';

import Topbar from '../../components/topbar/Topbar';
import Navbar from '../../components/navbar/Navbar';
import Menu from '../../components/menu/Menu';
import CategoriesContainer from '../../components/categoriesContainer/CategoriesContainer';
import BooksContainer from '../../components/booksContainer/BooksContainer';
import EncouragementBar from '../../components/encouragementBar/EncouragementBar';
import Footer from '../../components/footer/Footer';

import './start-page.css';

import pcPhoto1 from './start-page-image1.png';
import pcPhoto2 from './start-page-image2.png';

const StartPage = () => {
    const [totalBooks, setTotalBooks] = useState(0);
    const [topSoldBooks, setTopSoldBooks] = useState([]);
    const [recentlyAddedBooks, setRecentlyAddedBooks] = useState([]);
    const [bookCategories, setBookCategories] = useState([]);
    const navigate = useNavigate();

    const determineExploreLink = () => {
        console.log(localStorage.getItem('auth_token'))
        if (localStorage.getItem('auth_token')) {
            switch (localStorage.getItem('user_role')) {
                case Role.ROLE_USER || Role.ROLE_EMPLOYEE || Role.ROLE_ADMIN:
                    return '/dashboard';
                default:
                    return '/login';
            }
        } else {
            return '/login';
        }
    };

    useEffect(() => {
        // Fetch book genres from the API
        fetch(API_ENDPOINTS.genres)
            .then((response) => response.json())
            .then((data) => setBookCategories(data))
            .catch((error) => console.error('Error fetching book genres:', error));

        // Fetch recently added books from the API
        fetch(API_ENDPOINTS.recentlyAdded)
            .then((response) => response.json())
            .then((data) => setRecentlyAddedBooks(data))
            .catch((error) => console.error('Error fetching recently added books:', error));

        // Fetch totalBooks from the API
        fetch(API_ENDPOINTS.totalBooks)
            .then((response) => response.json())
            .then((data) => setTotalBooks(data.result))
            .catch((error) => console.error('Error fetching total books:', error));

        // Fetch topSoldBooks from the API
        fetch(API_ENDPOINTS.topSoldBooks)
            .then((response) => response.json())
            .then((data) => setTopSoldBooks(data))
            .catch((error) => console.error('Error fetching top sold books:', error));
    }, []);

    const features = [
        { icon: 'fa-solid fa-check-double', title: 'High-quality Books' },
        { icon: 'fas fa-shipping-fast', title: 'Free Delivery' },
        { icon: 'fa-solid fa-arrow-left', title: '21-Day Return' },
        { icon: 'fas fa-phone-volume', title: '24/7 Support' },
    ];

    return (
        <div>
            <Topbar />
            <Navbar />
            <Menu />
            <div className="start-page-content">
                <section className="start-page-content-start">
                    <div className="start-page-content-start-left">
                        <div className="start-page-content-left-image">
                            <img src={pcPhoto1} alt="" />
                        </div>
                        <div className="start-page-content-left-descr">
                            <h1>The world of books</h1>
                            <p>Discover the vast world of books and embark on a literary adventure with our diverse collection.</p>
                            <div className="start-page-content-start-right-button button" onClick={() => navigate(determineExploreLink())}>
                                Buy now
                            </div>
                        </div>
                    </div>
                    <div className="start-page-content-start-right">
                        <div className="start-page-content-start-right-title">
                            <h1>We already have over <span className="unique-value-style">{totalBooks}</span> unique books in our warehouses!</h1>
                        </div>
                        <div className="start-page-content-start-right-image">
                            <img src={pcPhoto2} alt="" />
                        </div>
                    </div>
                </section>
                <section className="start-page-content-features">
                    {features.map((feature, index) => (
                        <div key={index} className="start-page-content-features-feature">
                            <div className="start-page-content-features-feature-image">
                                <i className={feature.icon}></i>
                            </div>
                            <div className="start-page-content-features-feature-title">
                                <h2>{feature.title}</h2>
                            </div>
                        </div>
                    ))}
                </section>
                <CategoriesContainer bookCategories={bookCategories} />
                <section className="start-page-content-top-books">
                    <h1 className="page-section-title start-page-content-top-books-title">Top 10 bestsellers</h1>
                    {topSoldBooks.length > 0 ? (
                        <BooksContainer booksResult={topSoldBooks} />
                    ) : (
                        <div className="start-page-content-message">
                            No data available about bestsellers
                        </div>
                    )}
                </section>
            </div>
            <EncouragementBar />
            <section className="start-page-content-recently-added">
                <h1 className="page-section-title start-page-content-recently-added-title">Recently added</h1>
                {recentlyAddedBooks.length > 0 ? (
                    <BooksContainer booksResult={recentlyAddedBooks} />
                ) : (
                    <div className="start-page-content-message">
                        No recently added books data found
                    </div>
                )}
            </section>
            <Footer />
        </div>
    );
};

export default StartPage;