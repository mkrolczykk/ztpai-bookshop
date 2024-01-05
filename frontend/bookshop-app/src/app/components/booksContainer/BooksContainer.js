import React from 'react';
import { Link } from 'react-router-dom';

import defaultCover from './books/mock-book-cover.png';

import './books-container.css';

const BooksContainer = ({ booksResult }) => {
    return (
        <div className="books-container">
            <section className="books-section">
                {booksResult.map((book) => (
                    <div key={book.title} className="book-section-cover">
                        <Link to={`bookDetail?bookTitle=${book.title.toLowerCase().replace(/\s+/g, '-')}`}>
                            <img className="book-cover" src={defaultCover} alt="Book cover" />
                        </Link>

                        <div className="book-description">
                            <h2 className="book-description-title">
                                <Link to={`bookDetail?bookTitle=${book.title.toLowerCase().replace(/\s+/g, '-')}`}>
                                    {book.title}
                                </Link>
                            </h2>
                            <p>{book.authors}</p>
                            <div className="price-section">
                                <p>
                                    {book.price} {book.currency}
                                </p>
                            </div>
                        </div>
                    </div>
                ))}
            </section>
        </div>
    );
};

export default BooksContainer;