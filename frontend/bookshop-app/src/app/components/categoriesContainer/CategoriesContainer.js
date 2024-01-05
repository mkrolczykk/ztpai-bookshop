import React from 'react';

import './categories-container.css';

import defaultCover from './categoriesImages/default-cover.png';
import artCover from './categoriesImages/art.png';
import biographyCover from './categoriesImages/biography.png';
import comicsCover from './categoriesImages/comics.png';
import fantasyCover from './categoriesImages/fantasy.png';
import financeCover from './categoriesImages/finance.png';
import historicalFictionCover from './categoriesImages/historical-fiction.png';
import horrorCover from './categoriesImages/horror.png';
import musicCover from './categoriesImages/music.png';
import mysteryCover from './categoriesImages/mystery.png';
import philosophyCover from './categoriesImages/philosophy.png';
import poetryCover from './categoriesImages/poetry.png';
import religionCover from './categoriesImages/religion.png';
import romanceCover from './categoriesImages/romance.png';
import scienceCover from './categoriesImages/science.png';
import scienceFictionCover from './categoriesImages/science-fiction.png';
import thrillerCover from './categoriesImages/thriller.png';
import traverCover from './categoriesImages/travel.png';
import trueCrimeCover from './categoriesImages/true-crime.png';

const CategoriesContainer = ({ bookCategories }) => {
    const coverMap = {
        'Art': artCover,
        'Biography': biographyCover,
        'Comics': comicsCover,
        'Fantasy': fantasyCover,
        'Finance': financeCover,
        'Historical Fiction': historicalFictionCover,
        'Horror': horrorCover,
        'Music': musicCover,
        'Mystery': mysteryCover,
        'Philosophy': philosophyCover,
        'Poetry': poetryCover,
        'Religion': religionCover,
        'Romance': romanceCover,
        'Science': scienceCover,
        'Science Fiction': scienceFictionCover,
        'Thriller': thrillerCover,
        'Travel': traverCover,
        'True Crime': trueCrimeCover
    };

    return (
        <section className="categories-container">
            <section className="categories-container-books-categories">
                <h1 className="page-section-title categories-container-books-categories-title">Categories</h1>
                <div className="categories-container-books-categories-container">
                    {bookCategories.map((category) => {
                        const genre = category.genre;
                        const thumbnailPath = getImagePath(genre, coverMap);
                        const categoryLink = `/category?type=${genre.toLowerCase().replace(/\s+/g, '-')}`;

                        return (
                            <a key={genre} href={categoryLink} className="categories-container-books-categories-container-category">
                                <img src={thumbnailPath} alt={genre} />
                                <h2>{genre}</h2>
                            </a>
                        );
                    })}
                </div>
            </section>
        </section>
    );
};

const getImagePath = (genre, coverMap) => {
    return coverMap[genre] || defaultCover;
};

export default CategoriesContainer;