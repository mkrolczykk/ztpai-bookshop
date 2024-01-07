import React, { useState, useEffect } from 'react';
import axios from 'axios';

import API_ENDPOINTS from '../../common/config-staging';
import Role from "../../common/constants/Role";

// components
import Topbar from "../../components/topbar/Topbar";
import Navbar from "../../components/navbar/Navbar";
import Menu from "../../components/menu/Menu";
import WelcomeMessage from '../../components/welcomeMessage/WelcomeMessage';
import BooksContainer from '../../components/booksContainer/BooksContainer';
import OrdersContainer from '../../components/ordersContainer/OrdersContainer';
import Footer from "../../components/footer/Footer";

// styles
import "./user-dashboard.css";
import "./employee-dashboard.css";
import "./admin-dashboard.css";

const DashboardPage = () => {

    const roleNameMap = {
        ROLE_USER: "user",
        ROLE_EMPLOYEE: "employee",
        ROLE_ADMIN: "admin",
    };
    const [userFavoriteBooks, setUserFavoriteBooks] = useState([]);
    const [ordersList, setOrdersList] = useState([]);
    const [employeesList, setEmployeesList] = useState([]);
    const [role, setRole] = useState('');

    useEffect(() => {
        const authToken = localStorage.getItem('auth_token');
        const userRole = localStorage.getItem('user_role');

        const fetchData = async () => {
            try {
                if (userRole === Role.ROLE_USER) {
                    const userFavoriteBooksResponse = await axios.get(API_ENDPOINTS.userFavoriteBooks);
                    setUserFavoriteBooks(userFavoriteBooksResponse.data);
                } else if (userRole === Role.ROLE_EMPLOYEE || userRole === Role.ROLE_ADMIN) {
                    const ordersListResponse = await axios.get(API_ENDPOINTS.ordersList);
                    const employeesListResponse = await axios.get(API_ENDPOINTS.employeesList);

                    setOrdersList(ordersListResponse.data);
                    setEmployeesList(employeesListResponse.data);
                }
            } catch (error) {
                console.error('Error fetching data: ', error);
            }
        };

        if (authToken && (userRole === Role.ROLE_USER || userRole === Role.ROLE_EMPLOYEE || userRole === Role.ROLE_ADMIN)) {
            setRole(userRole);
            fetchData();
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
                        <BooksContainer booksResult={userFavoriteBooks}/>
                    </section>
                )}
                {role === Role.ROLE_EMPLOYEE && (
                    <section className="employee-dashboard-content-orders-history">
                        <h1 className="page-section-title employee-dashboard-content-orders-history-title">
                            Orders to be fulfilled
                        </h1>
                        <OrdersContainer orders={ordersList} employees={employeesList}/>
                    </section>
                )}
                {role === Role.ROLE_ADMIN && (
                    <section className="admin-dashboard-content-orders-history">
                        <h1 className="page-section-title admin-dashboard-content-orders-history-title">
                            Orders to be fulfilled
                        </h1>
                        <OrdersContainer orders={ordersList} employees={employeesList}/>
                    </section>
                )}
            </div>
            <Footer />
        </div>
    );
};

export default DashboardPage;