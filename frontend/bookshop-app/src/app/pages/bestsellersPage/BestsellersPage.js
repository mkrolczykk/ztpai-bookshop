import React, { useEffect, useState } from 'react';

import Topbar from '../../components/topbar/Topbar';
import Navbar from '../../components/navbar/Navbar';
import Menu from '../../components/menu/Menu';
import BooksContainer from '../../components/booksContainer/BooksContainer';
import EncouragementBar from '../../components/encouragementBar/EncouragementBar';
import Footer from '../../components/footer/Footer';

import './bestsellers.css';

const BestsellersPage = () => {
    const [topSoldBooks, setTopSoldBooks] = useState([]);
    const [messages, setMessages] = useState([]);
    const isAuthenticated = sessionStorage.getItem('authenticated');

    useEffect(() => {
        // TODO -> umiescic logikę pobierania topSoldBooks oraz messages z API lub innego źródła danych
        // TODO -> Ustaw dane przy użyciu setTopSoldBooks i setMessages
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
                        {messages.map((message, index) => (
                            <div key={index}>{message}</div>
                        ))}
                    </div>
                )}
            </section>
            {!isAuthenticated && <EncouragementBar />}
            <Footer />
        </div>
    );
};

export default BestsellersPage;
