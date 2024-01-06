import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';

import Topbar from '../../components/topbar/Topbar';
import Navbar from '../../components/navbar/Navbar';
import Menu from '../../components/menu/Menu';
import BooksContainer from '../../components/booksContainer/BooksContainer';
import EncouragementBar from '../../components/encouragementBar/EncouragementBar';
import Footer from '../../components/footer/Footer';

import './search-results.css';

const SearchPage = ({ booksResult, messages }) => {
    return (
        <div>
            <Topbar />
            <Navbar />
            <Menu />
            <div className="find-results-content">
                {booksResult.length > 0 ? (
                    <>
                        <h1 className="find-results-content-message">
                            Search result: {booksResult.length}
                        </h1>
                        <BooksContainer booksResult={booksResult} />
                    </>
                ) : (
                    <div className="find-results-content-message">
                        {messages &&
                            messages.map((message, index) => (
                                <div key={index}>{message}</div>
                            ))}
                    </div>
                )}
            </div>
            {!localStorage.getItem('auth_token') && <EncouragementBar />}
            <Footer />
        </div>
    );
};

const SearchPageWrapper = () => {
    const { param1, param2 } = useParams();

    const [booksResult, setBooksResult] = useState([]);
    const [messages, setMessages] = useState([]);

    // TODO -> Get data from API
    useEffect(() => {
        // TODO -> Get data from API, etc., using parameters from URL
    }, [param1, param2]);

    return <SearchPage booksResult={booksResult} messages={messages} />;
};

export default SearchPageWrapper;