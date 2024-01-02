import React, { useState } from 'react';

import Topbar from "../../components/topbar/Topbar";
import Navbar from "../../components/navbar/Navbar";
import Menu from "../../components/menu/Menu";
import Footer from "../../components/footer/Footer"

import './login.css';

const LoginPage = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [messages, setMessages] = useState([]);

    const handleSubmit = (e) => {
        e.preventDefault();

        // Add your login logic here, for example, sending a request to a server.

        // For demo purposes, just setting some example messages.
        setMessages(['Login successful']);
    };

    return (
        <div>
            <Topbar/>
            <Navbar/>
            <Menu/>

            <section className="login-content">
                <h2 className="page-section-title login-content-title">Login to the site</h2>
                <div className="login-section-form-container">
                    <form id="login" className="login" onSubmit={handleSubmit}>
                        <div className="messages">
                            {messages.map((message, index) => (
                                <div key={index}>{message}</div>
                            ))}
                        </div>
                        <fieldset>
                            <input
                                name="email"
                                placeholder="Email Address"
                                type="email"
                                tabIndex="1"
                                required
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                            />
                        </fieldset>
                        <fieldset>
                            <input
                                name="password"
                                placeholder="Password"
                                type="password"
                                tabIndex="2"
                                required
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                            />
                        </fieldset>
                        <fieldset>
                            <button type="submit" id="contact-submit" data-submit="...Sending">
                                Login
                            </button>
                        </fieldset>
                        <p className="login-terms">
                            By logging in to the site, you agree to the <a href="/contact">Privacy Policy</a> and{' '}
                            <a href="/contact">Terms of Use</a> of the store.
                        </p>
                        <p className="login-redirect">
                            No account yet?<a href="/register">Register</a>
                        </p>
                    </form>
                </div>
            </section>

            {/* Include your footer component here */}
            <Footer />
        </div>
    );
};

export default LoginPage;
