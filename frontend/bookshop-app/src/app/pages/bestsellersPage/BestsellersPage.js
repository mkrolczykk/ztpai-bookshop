import React, { useEffect, useState } from 'react';

import Topbar from '../../components/topbar/Topbar';
import Navbar from '../../components/navbar/Navbar';
import Menu from '../../components/menu/Menu';
import BooksContainer from '../../components/booksContainer/BooksContainer';
import EncouragementBar from '../../components/encouragementBar/EncouragementBar';
import Footer from '../../components/footer/Footer';

import './bestsellers.css';
import API_ENDPOINTS from "../../common/config-staging";

const BestsellersPage = () => {
    const [topSoldBooks, setTopSoldBooks] = useState([]);
    useEffect(() => {
        // Fetch topSoldBooks from the API
        fetch(API_ENDPOINTS.topSoldBooks)
            .then((response) => response.json())
            .then((data) => setTopSoldBooks(data))
            .catch((error) => console.error('Error fetching top sold books:', error));
    }, []);

    return (
        <div>
            <Topbar />
            <Navbar />
            <Menu />
            <section className="bestsellers-content">
                <h1 className="page-section-title bestsellers-content-title">Bestsellers</h1>
                {topSoldBooks.length > 0 ? (
                    <BooksContainer booksResult={topSoldBooks} />
                ) : (
                    <div className="start-page-content-message">
                        No data available about bestsellers
                    </div>
                )}
            </section>
            {!localStorage.getItem('auth_token') && (
                <EncouragementBar/>
            )}
            <Footer />
        </div>
    );
};

export default BestsellersPage;
