import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';

import DashboardPage from "../pages/dashboardPage/DashboardPage";

const RouteGuard = () => {
    const hasJWT = () => localStorage.getItem('token') !== null;

    if (hasJWT()) {
        return (
            <>
                <Navigate to="/dashboard" replace={true} />
                <DashboardPage />
            </>
        );
    } else {
        return <Navigate to="/login" replace={true} />;
    }
};

export default RouteGuard;