import React from 'react';

import logo from "./payments.png";

import './footer.css';

import { faEnvelope, faPhone, faMapMarkerAlt, faCaretRight } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

const Footer = () => {
    const menuResult = [
        ["/", "Start page"],
        ["/latest", "New books"],
        ["/bestsellers", "Bestsellers"],
        ["/contact", "Contact"],
        ["/login", "Log in"],
        ["/register", "Register"]
    ];

    return (
        <div className="footer-container">
            <div className="footer-contact">
                <div className="footer-contact-info">
                    <h2 className="footer-subtitle">Stay Connected with us</h2>
                    <p className="footer-contact-text">Stay connected with us and never miss out on the latest news, updates, and exclusive offers. Follow us on social media and subscribe to our newsletter for more exciting content and special deals.</p>
                    <p className="footer-contact-text"><FontAwesomeIcon icon={faEnvelope} className="footer-menu-icon" />bookshop@gmail.com</p>
                    <p className="footer-contact-text"><FontAwesomeIcon icon={faPhone} className="footer-menu-icon" />+18 26 248 54</p>
                    <p className="footer-contact-text"><FontAwesomeIcon icon={faMapMarkerAlt} className="footer-menu-icon" />1234 Alaska, Hong Kong, Poland</p>
                </div>
                <div className="footer-menu">
                    <div className="footer-menu-nav">
                        <h5 className="footer-subtitle">Menu</h5>
                        <div className="footer-menu-nav-options">
                            {menuResult.map((menu, index) => (
                                <a key={index} href={menu[0]} className="footer-menu-nav-option">
                                    <FontAwesomeIcon icon={faCaretRight} className="footer-menu-icon" />{menu[1]}
                                </a>
                            ))}
                        </div>
                    </div>
                </div>
            </div>
            <div className="footer-domain">
                <div className="footer-domain-domain">
                    <p className="mb-md-0 text-center text-md-left text-secondary">
                        &copy; <a className="footer-domain-domain" href="/">Book shop</a>. All Rights Reserved. Designed
                        by
                        <a className="footer-domain-designer"> Marcin Krolczyk</a>
                    </p>
                </div>
                <div className="footer-domain-payments">
                    <img className="footer-domain-payments-image" src={logo}
                         alt="Available payments"/>
                </div>
            </div>
        </div>
    );
};

export default Footer;