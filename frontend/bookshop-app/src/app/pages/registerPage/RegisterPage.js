import React, { useState } from 'react';

import Topbar from "../../components/topbar/Topbar";
import Navbar from "../../components/navbar/Navbar";
import Menu from "../../components/menu/Menu";
import Footer from "../../components/footer/Footer"

import './register.css';

const RegisterPage = () => {
    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmedPassword, setConfirmedPassword] = useState('');
    const [notifications, setNotifications] = useState(true);
    const [messages, setMessages] = useState([]);

    const handleSubmit = (e) => {
        e.preventDefault();

        // Add your registration logic here, for example, sending a request to a server.

        // For demo purposes, just setting some example messages.
        setMessages(['Registration successful']);
    };

    return (
        <div>
            <Topbar/>
            <Navbar/>
            <Menu/>

            <section className="register-content">
                <h2 className="page-section-title register-content-title">Create an Account</h2>
                <div className="register-section-form-container">
                    <form id="register" className="register" onSubmit={handleSubmit}>
                        <div className="messages">
                            {messages.map((message, index) => (
                                <div key={index}>{message}</div>
                            ))}
                        </div>
                        <fieldset>
                            <input
                                name="name"
                                placeholder="Name"
                                type="text"
                                tabIndex="0"
                                required
                                value={name}
                                onChange={(e) => setName(e.target.value)}
                            />
                        </fieldset>
                        <fieldset>
                            <input
                                name="surname"
                                placeholder="Surname"
                                type="text"
                                tabIndex="1"
                                required
                                value={surname}
                                onChange={(e) => setSurname(e.target.value)}
                            />
                        </fieldset>
                        <fieldset>
                            <input
                                name="username"
                                placeholder="Username"
                                type="text"
                                tabIndex="2"
                                required
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                            />
                        </fieldset>
                        <fieldset>
                            <input
                                name="email"
                                placeholder="Email Address"
                                type="email"
                                tabIndex="3"
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
                                tabIndex="4"
                                required
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                            />
                        </fieldset>
                        <fieldset>
                            <input
                                name="confirmedPassword"
                                placeholder="Confirm Password"
                                type="password"
                                tabIndex="5"
                                required
                                value={confirmedPassword}
                                onChange={(e) => setConfirmedPassword(e.target.value)}
                            />
                        </fieldset>
                        <fieldset class="notifications-fieldset">
                            <input
                                name="notifications"
                                type="checkbox"
                                id="notifications"
                                checked={notifications}
                                onChange={() => setNotifications(!notifications)}
                            />
                            <label htmlFor="notifications">
                                I want to receive notifications about new products to the e-mail address I provided.
                            </label>
                        </fieldset>
                        <fieldset>
                            <button type="submit" id="contact-submit" data-submit="...Sending">
                                Register
                            </button>
                        </fieldset>
                        <p className="register-terms">
                            By creating an account, you agree to the <a href="/contact">Privacy Policy</a> and{' '}
                            <a href="/contact">Terms of Use</a> of the store.
                        </p>
                        <p className="register-redirect">
                            Already have an account?<a href="/login">Login</a>
                        </p>
                    </form>
                </div>
            </section>

            {/* Include your footer component here */}
            <Footer />
        </div>
    );
};

export default RegisterPage;