import React, { useState, useEffect } from 'react';

import Topbar from "../../components/topbar/Topbar";
import Navbar from "../../components/navbar/Navbar";
import Menu from "../../components/menu/Menu";
import WelcomeMessage from '../../components/welcomeMessage/WelcomeMessage';
// import BooksContainer from '../../components/booksContainer/BooksContainer';
// import OrdersContainer from '../../components/ordersContainer/OrdersContainer';
import Footer from "../../components/footer/Footer"

const DashboardPage = () => {
    const [role, setRole] = useState('');

    useEffect(() => {
        // Check if user is authenticated and has a valid role
        const isAuthenticated = localStorage.getItem('authenticated');
        const token = localStorage.getItem('auth_token');
        const roleId = sessionStorage.getItem('roleId');

        if (isAuthenticated && token && (roleId === 'user' || roleId === 'employee' || roleId === 'admin')) {
            setRole(roleId);
        }
    }, []);

    return (
        <div>
            <Topbar/>
            <Navbar/>
            <Menu/>

            <div className={`${role}-dashboard-content`}>
                <WelcomeMessage />

                {/*{role === 'user' && (*/}
                {/*    <section className="user-dashboard-content-favorite-books">*/}
                {/*        <h1 className="page-section-title user-dashboard-content-favorite-books-title">*/}
                {/*            My recently saved books*/}
                {/*        </h1>*/}
                {/*        /!* Add your user-specific content here *!/*/}
                {/*        <BooksContainer />*/}
                {/*    </section>*/}
                {/*)}*/}

                {/*{role === 'employee' && (*/}
                {/*    <section className="employee-dashboard-content-orders-history">*/}
                {/*        <h1 className="page-section-title employee-dashboard-content-orders-history-title">*/}
                {/*            Orders to be fulfilled*/}
                {/*        </h1>*/}
                {/*        /!* Add your employee-specific content here *!/*/}
                {/*        <OrdersContainer />*/}
                {/*    </section>*/}
                {/*)}*/}

                {/*{role === 'admin' && (*/}
                {/*    <section className="admin-dashboard-content-orders-history">*/}
                {/*        <h1 className="page-section-title admin-dashboard-content-orders-history-title">*/}
                {/*            Orders to be fulfilled*/}
                {/*        </h1>*/}
                {/*        /!* Add your admin-specific content here *!/*/}
                {/*        <OrdersContainer />*/}
                {/*    </section>*/}
                {/*)}*/}
            </div>

            {/* Include your footer component here */}
            <Footer />
        </div>
    );
};

export default DashboardPage;
