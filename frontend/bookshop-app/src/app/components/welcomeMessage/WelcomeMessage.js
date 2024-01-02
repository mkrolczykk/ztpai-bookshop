import React, { useState, useEffect } from 'react';

const WelcomeSection = ({ sessionName }) => {
    const [welcomeShown, setWelcomeShown] = useState(false);

    useEffect(() => {
        // Check if the welcome message has been shown
        const isWelcomeShown = localStorage.getItem('welcomeShown') === 'true';

        // If not shown, set the state to true and mark it as shown in localStorage
        if (!isWelcomeShown) {
            setWelcomeShown(true);
            localStorage.setItem('welcomeShown', 'true');
        }
    }, []);

    return (
        <>
            {!welcomeShown && (
                <section id="welcome-section" className="welcome-section">
                    <div className="welcome-message">
                        <h1 className="welcome-message-text">Welcome {sessionName}! Login was successful.</h1>
                        <button id="close-button" className="welcome-message-button" onClick={() => setWelcomeShown(true)}>
                            &times;
                        </button>
                    </div>
                </section>
            )}
        </>
    );
};

export default WelcomeSection;