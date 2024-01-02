import React, { useState, useEffect } from 'react';

import "./welcome-message.css";

const WelcomeSection = () => {
    const [welcomeShown, setWelcomeShown] = useState(false);

    useEffect(() => {
        // Check if the welcome message has been shown
        const isWelcomeShown = localStorage.getItem('welcome_message_event') === 'true';

        // If not shown, set the state to true and mark it as shown in localStorage
        if (!isWelcomeShown) {
            setWelcomeShown(true);
            localStorage.setItem('welcome_message_event', 'true');
        }
    }, []);

    const handleCloseButtonClick = () => {
        setWelcomeShown(false);
    };

    return (
        <>
            {welcomeShown && (
                <section id="welcome-section" className="welcome-section">
                    <div className="welcome-message">
                        <h1 className="welcome-message-text">Welcome {localStorage.getItem("user_name")}! Login was successful.</h1>
                        <button id="close-button" className="welcome-message-button" onClick={handleCloseButtonClick}>
                            &times;
                        </button>
                    </div>
                </section>
            )}
        </>
    );
};

export default WelcomeSection;