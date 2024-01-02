import React, { useState, useEffect } from 'react';

import Topbar from "../../components/topbar/Topbar";
import Navbar from "../../components/navbar/Navbar";
import Menu from "../../components/menu/Menu";
import WelcomeMessage from '../../components/welcomeMessage/WelcomeMessage';
import BooksContainer from '../../components/booksContainer/BooksContainer';
import OrdersContainer from '../../components/ordersContainer/OrdersContainer';
import Footer from "../../components/footer/Footer"
import Role from "../../common/constants/Role";

import "./user-dashboard.css";
import "./employee-dashboard.css";
import "./admin-dashboard.css";

const DashboardPage = () => {

    const roleNameMap = {
        ROLE_USER: "user",
        ROLE_EMPLOYEE: "employee",
        ROLE_ADMIN: "admin",
    };

    const [role, setRole] = useState('');

    useEffect(() => {
        const authToken = localStorage.getItem('auth_token');
        const userRole = localStorage.getItem('user_role');

        if (authToken && (
            userRole === Role.ROLE_USER ||
            userRole === Role.ROLE_EMPLOYEE ||
            userRole === Role.ROLE_ADMIN)
        ) {
            setRole(userRole);
        }
    }, []);

    return (
        <div>
            <Topbar/>
            <Navbar/>
            <Menu/>
            <div className={`${roleNameMap[role]}-dashboard-content`}>
                <WelcomeMessage />
                {role === Role.ROLE_USER && (
                    <section className="user-dashboard-content-favorite-books">
                        <h1 className="page-section-title user-dashboard-content-favorite-books-title">
                            My recently saved books
                        </h1>
                        <BooksContainer booksResult={[]}/>
                    </section>
                )}
                {role === Role.ROLE_EMPLOYEE && (
                    <section className="employee-dashboard-content-orders-history">
                        <h1 className="page-section-title employee-dashboard-content-orders-history-title">
                            Orders to be fulfilled
                        </h1>
                        <OrdersContainer orders={[]} employees={[]}/>
                    </section>
                )}

                {role === Role.ROLE_ADMIN && (
                    <section className="admin-dashboard-content-orders-history">
                        <h1 className="page-section-title admin-dashboard-content-orders-history-title">
                            Orders to be fulfilled
                        </h1>
                        <OrdersContainer orders={[]} employees={[]}/>
                    </section>
                )}
            </div>
            <Footer />
        </div>
    );
};

export default DashboardPage;
