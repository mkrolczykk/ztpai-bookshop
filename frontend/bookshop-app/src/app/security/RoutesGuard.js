import React from 'react';
import { Navigate } from 'react-router-dom';

const RoutesGuard = ({ children, roleCheck }) => {

    const hasJWT = () => localStorage.getItem('auth_token') !== null;

    if (hasJWT() && roleCheck()) {
        return <>{children}</>;
    } else {
        return <Navigate to="/login" replace={true} />;
    }
};

export default RoutesGuard;