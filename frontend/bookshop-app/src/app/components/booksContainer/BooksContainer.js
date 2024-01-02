import React from 'react';

import { Link } from 'react-router-dom';

import './books-container.css';

const BooksContainer = ({ booksResult }) => {
    return (
        <div className="books-container">
            <section className="books-section">
                {booksResult.map((book) => (
                    <div key={book.getTitle()} className="book-section-cover">
                        {(() => {
                            const bookTitle = book.getTitle().toLowerCase().replace(/\s+/g, '-');
                            const coverPath = `public/img/books/${bookTitle}.png`;
                            const coverSrc = fileExists(coverPath) ? coverPath : 'public/img/books/mock-book-cover.png';

                            return (
                                <Link to={`bookDetail?bookTitle=${bookTitle}`}>
                                    <img className="book-cover" src={coverSrc} alt="Book cover" />
                                </Link>
                            );
                        })()}

                        <div className="book-description">
                            <h2 className="book-description-title">
                                <Link to={`bookDetail?bookTitle=${book.getTitle().toLowerCase().replace(/\s+/g, '-')}`}>
                                    {book.getTitle()}
                                </Link>
                            </h2>
                            <p>{book.getAuthors()}</p>
                            <div className="price-section">
                                <p>
                                    {book.getPrice()} {book.getCurrency()}
                                </p>
                            </div>
                        </div>
                    </div>
                ))}
            </section>
        </div>
    );
};

const fileExists = (path) => {
    return true; // Assuming the file exists
};

export default BooksContainer;