import React, { useEffect, useState } from 'react';

import Topbar from "../../components/topbar/Topbar"
import Navbar from "../../components/navbar/Navbar"
import Menu from "../../components/menu/Menu"
import BooksContainer from "../../components/booksContainer/BooksContainer"
import EncouragementBar from "../../components/encouragementBar/EncouragementBar"
import Footer from "../../components/footer/Footer"

import './new-books.css';

const NewBooksPage = () => {
    const [recentlyAddedBooks, setRecentlyAddedBooks] = useState([]);
    const [messages, setMessages] = useState([]);
    const isAuthenticated = sessionStorage.getItem('authenticated');

    useEffect(() => {
        // TODO -> umiescic logikę pobierania recentlyAddedBooks oraz messages z API lub innego źródła danych
        // TODO -> ustawic dane przy użyciu setRecentlyAddedBooks i setMessages
    }, []); //

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
                        {messages.map((message, index) => (
                            <div key={index}>{message}</div>
                        ))}
                    </div>
                )}
            </section>
            {!isAuthenticated && (
                <EncouragementBar/>
            )}
            <Footer/>
        </div>
    );
};

export default NewBooksPage;