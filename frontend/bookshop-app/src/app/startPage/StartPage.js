import React, { useState, useEffect } from 'react';

// import 'public/css/start-page.css';
import './start-page.css';

const StartPage = () => {
    // TODO -> State to handle total books, top sold books, recently added books, etc.
    const [totalBooks, setTotalBooks] = useState(0);
    const [topSoldBooks, setTopSoldBooks] = useState([]);
    const [recentlyAddedBooks, setRecentlyAddedBooks] = useState([]);

    // useEffect for fetching data from the server using REST API
    useEffect(() => {
        // TODO -> Fetch totalBooks, topSoldBooks, recentlyAddedBooks, etc. using REST API
        // TODO -> Update state accordingly
    }, []);

    // Dummy data for features
    const features = [
        { icon: 'fa-solid fa-check-double', title: 'High-quality Books' },
        { icon: 'fas fa-shipping-fast', title: 'Free Delivery' },
        { icon: 'fa-solid fa-arrow-left', title: '21-Day Return' },
        { icon: 'fas fa-phone-volume', title: '24/7 Support' },
    ];

    return (
        <div>
            <div className="start-page-content">
                <section className="start-page-content-start">
                    {/* TODO */}
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
                {/* TODO -> Include other sections and components */}
            </div>

            {/* TODO -> Include React equivalents for encouragement bar */}
            {/* TODO -> Include React equivalents for recently added books */}
            {/* TODO -> Include React equivalents for footer */}
            {/* TODO -> Include React equivalents for JavaScript files */}
        </div>
    );
};

export default StartPage;