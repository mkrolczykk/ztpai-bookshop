import React from 'react';
import { Navigate } from 'react-router-dom';

const RouteGuard = ({ children }) => {
    const hasJWT = () => localStorage.getItem('auth_token') !== null;

    if (hasJWT()) {
        return <>{children}</>;
    } else {
        return <Navigate to="/login" replace={true} />;
    }
};

export default RouteGuard;