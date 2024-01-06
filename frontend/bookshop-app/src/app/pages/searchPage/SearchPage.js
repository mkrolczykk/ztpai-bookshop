import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

import Topbar from '../../components/topbar/Topbar';
import Navbar from '../../components/navbar/Navbar';
import Menu from '../../components/menu/Menu';
import BooksContainer from '../../components/booksContainer/BooksContainer';
import EncouragementBar from '../../components/encouragementBar/EncouragementBar';
import Footer from '../../components/footer/Footer';

import './search-results.css';
import API_ENDPOINTS from '../../common/config-test';

const SearchComponent = ({ booksResult }) => {
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
                        <br/> UI for search feature in progress. Use API instead <br/> <br/>
                        Available endpoint: '/bookapp/api/v1/books/search?searchkey=xxxxx', where xxxxx is book title or author
                    </div>
                )}
            </div>
            {!localStorage.getItem('auth_token') && <EncouragementBar />}
            <Footer />
        </div>
    );
};

const SearchPage = () => {
    const { searchkey } = useParams();
    const [booksResult, setBooksResult] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get(API_ENDPOINTS.search.replace('{search_key}', searchkey), {
                    headers: {
                        'currency': localStorage.getItem('user_currency')
                    }
                });
                setBooksResult(response.data);
            } catch (error) {
                console.error('Error fetching search results:', error);
            }
        };

        fetchData();
    }, [searchkey]);

    return <SearchComponent booksResult={booksResult} />;
};

export default SearchPage;