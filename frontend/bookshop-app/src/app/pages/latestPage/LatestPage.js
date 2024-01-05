import React, { useEffect, useState } from 'react';

import Topbar from "../../components/topbar/Topbar"
import Navbar from "../../components/navbar/Navbar"
import Menu from "../../components/menu/Menu"
import BooksContainer from "../../components/booksContainer/BooksContainer"
import EncouragementBar from "../../components/encouragementBar/EncouragementBar"
import Footer from "../../components/footer/Footer"

import './new-books.css';
import API_ENDPOINTS from "../../common/config-test";

const LatestPage = () => {
    const [recentlyAddedBooks, setRecentlyAddedBooks] = useState([]);

    useEffect(() => {
        // Fetch recently added books from the API
        fetch(API_ENDPOINTS.recentlyAdded)
            .then((response) => response.json())
            .then((data) => setRecentlyAddedBooks(data))
            .catch((error) => console.error('Error fetching recently added books:', error));
    }, []);

    return (
        <div>
            <Topbar/>
            <Navbar/>
            <Menu/>
            <section className="new-books-content">
                <h1 className="page-section-title new-books-content-title">Recently added books</h1>
                {recentlyAddedBooks.length > 0 ? (
                    <BooksContainer booksResult={recentlyAddedBooks} />
                ) : (
                    <div className="new-books-content-message">
                        No recently added books data found
                    </div>
                )}
            </section>
            {!localStorage.getItem('auth_token') && (
                <EncouragementBar/>
            )}
            <Footer/>
        </div>
    );
};

export default LatestPage;