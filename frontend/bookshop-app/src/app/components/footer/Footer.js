import React from 'react';

import logo from "./payments.png"

import './footer.css';

const Footer = () => {

    const menuResult = [
        ["/", "Start page"],
        ["/newBooks", "New books"],
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
                    <p className="footer-contact-text"><i className="fa fa-envelope fa-lg footer-menu-icon"></i>bookshop@gmail.com</p>
                    <p className="footer-contact-text"><i className="fa fa-phone fa-lg footer-menu-icon"></i>+18 26 248 54</p>
                    <p className="footer-contact-text"><i className="fa fa-map-marker-alt fa-lg footer-menu-icon"></i>1234 Alaska, Hong Kong, Poland</p>
                </div>
                <div className="footer-menu">
                    <div className="footer-menu-nav">
                        <h5 className="footer-subtitle">Menu</h5>
                        <div className="footer-menu-nav-options">
                            {menuResult.map((menu, index) => (
                                <a key={index} href={menu[0]} className="footer-menu-nav-option">
                                    <i className="fa fa-caret-right fa-lg footer-menu-icon"></i>{menu[1]}
                                </a>
                            ))}
                        </div>
                    </div>
                </div>
                {/* ... (other parts of the original PHP code) */}
            </div>
            <div className="footer-domain" style={{ '!important': true }}>
                <div className="footer-domain-domain">
                    <p className="mb-md-0 text-center text-md-left text-secondary">
                        &copy; <a className="footer-domain-domain" href="/Users/Marcin/Desktop/ZTPAI_semestr7/ztpai-bookshop/frontend/bookshop-app/public">Book shop</a>. All Rights Reserved. Designed
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