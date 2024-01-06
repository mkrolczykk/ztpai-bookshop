import React, { useState } from 'react';
import {useLocation, useNavigate} from 'react-router-dom';
import axios from 'axios';

import {setAuthToken} from '../../security/setAuthToken';

import Topbar from '../../components/topbar/Topbar';
import Navbar from '../../components/navbar/Navbar';
import Menu from '../../components/menu/Menu';
import Footer from '../../components/footer/Footer';

import API_ENDPOINTS from "../../common/config-test";

import './login.css';

const LoginPage = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [messages, setMessages] = useState([]);
    const location = useLocation();
    const messagesFromRegister = location.state && location.state.successMessage;
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();

        const loginPayload = {
            email,
            password,
        };

        axios
            .post(API_ENDPOINTS.authenticate, loginPayload)
            .then((resp) => {

                const token = resp.headers['authorization'];

                // add JWT token and user basic info to local storage
                localStorage.setItem('auth_token', token);
                localStorage.setItem('user_id', resp.headers['user-id']);
                localStorage.setItem('user_role', resp.headers['user-role']);
                localStorage.setItem('user_name', resp.data["name"]);
                localStorage.setItem('user_surname', resp.data["surname"]);
                localStorage.setItem('user_username', resp.data["username"]);

                // set token to axios common header
                setAuthToken(token);

                // redirect user to dashboard
                navigate('/dashboard');
            })
            .catch((err) => {
                // handle login error
                setMessages(['Login failed. Please check your credentials.']);
            });
    };

    return (
        <div>
            <Topbar />
            <Navbar />
            <Menu />
            <section className="login-content">
                <h2 className="page-section-title login-content-title">Login to the site</h2>
                <div className="login-section-form-container">
                    <form id="login" className="login" onSubmit={handleSubmit}>
                        <div className="messages">
                            {messages.map((message, index) => (
                                <div key={index}>{message}</div>
                            ))}
                            {messagesFromRegister && <div>{messagesFromRegister}</div>}
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
            <Footer />
        </div>
    );
};

export default LoginPage;