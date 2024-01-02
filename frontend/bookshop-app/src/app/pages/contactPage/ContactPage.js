import React from 'react';

import Topbar from '../../components/topbar/Topbar';
import Navbar from '../../components/navbar/Navbar';
import Menu from '../../components/menu/Menu';
import EncouragementBar from '../../components/encouragementBar/EncouragementBar';
import Footer from '../../components/footer/Footer';

import './contact.css';

const ContactPage = () => {
    return (
        <div>
            <Topbar />
            <Navbar />
            <Menu />
            <section className="contact-content">
                <h2 className="page-section-title contact-content-title">Contact Us</h2>
                <div className="contact-content-section">
                    <div className="contact-content-section-form-container">
                        <form id="contact" action="" method="POST">
                            <h3>Contact Form</h3>
                            <h4>Contact us for custom quote</h4>
                            <fieldset>
                                <input placeholder="Name" type="text" tabIndex="1" required autoFocus />
                            </fieldset>
                            <fieldset>
                                <input placeholder="Email Address" type="email" tabIndex="2" required />
                            </fieldset>
                            <fieldset>
                                <input placeholder="Phone Number (optional)" type="tel" tabIndex="3" required />
                            </fieldset>
                            <fieldset>
                                <input placeholder="Your Web Site (optional)" type="url" tabIndex="4" required />
                            </fieldset>
                            <fieldset>
                                <textarea placeholder="Type your message here...." tabIndex="5" required />
                            </fieldset>
                            <fieldset>
                                <button
                                    name="submit"
                                    type="submit"
                                    id="contact-submit"
                                    data-submit="...Sending"
                                    onClick={() => alert('Message sent! Our support will answer you soon.')}
                                >
                                    Submit
                                </button>
                            </fieldset>
                        </form>
                    </div>
                    <div className="contact-content-section-right-side">
                        <div className="contact-content-section-right-side-map">
                            <iframe
                                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2541.8775572272696!2d19.93932431605335!3d50.061299926847496!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47165dd514c3a7db%3A0x473bfb9e0e4b16f4!2sKrak%C3%B3w%20Rynek%20G%C5%82%C3%B3wny!5e0!3m2!1sen!2sbd!4v1603794290143!5m2!1sen!2sbd?q"
                                frameBorder="0"
                                allowFullScreen=""
                                aria-hidden="false"
                                tabIndex="0"
                            ></iframe>
                        </div>
                        <div className="contact-content-section-right-side-contact">
                            <p>
                                <i className="fa fa-map-marker-alt fa-lg"></i>1234 Alaska, Hong Kong, Poland
                            </p>
                            <p>
                                <i className="fa fa-envelope fa-lg"></i>bookshop@gmail.com
                            </p>
                            <p>
                                <i className="fa fa-phone fa-lg"></i>+18 26 248 54
                            </p>
                        </div>
                    </div>
                </div>
            </section>
            <EncouragementBar />
            <Footer />
        </div>
    );
};

export default ContactPage;