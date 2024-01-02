import React, { useState, useEffect } from 'react';

import Topbar from "../../components/topbar/Topbar"
import Navbar from "../../components/navbar/Navbar"
import Menu from "../../components/menu/Menu"
import EncouragementBar from "../../components/encouragementBar/EncouragementBar"
import Footer from "../../components/footer/Footer"

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

    // Features
    const features = [
        { icon: 'fa-solid fa-check-double', title: 'High-quality Books' },
        { icon: 'fas fa-shipping-fast', title: 'Free Delivery' },
        { icon: 'fa-solid fa-arrow-left', title: '21-Day Return' },
        { icon: 'fas fa-phone-volume', title: '24/7 Support' },
    ];

    return (
        <div>
            <Topbar/>
            <Navbar/>
            <Menu/>
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

            <EncouragementBar/>
            {/* TODO -> Include React equivalents for recently added books */}
            <Footer/>
            {/* TODO -> Include React equivalents for JavaScript files */}
        </div>
    );
};

export default StartPage;