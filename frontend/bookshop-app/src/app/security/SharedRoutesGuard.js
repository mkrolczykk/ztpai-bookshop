import React from 'react';
import RoutesGuard from './RoutesGuard';

import Role from "../common/constants/Role";

const SharedRoutesGuard = ({ children }) => {

    const roleCheck = () => {
        const userRole = localStorage.getItem('user_role');
        return userRole === Role.ROLE_USER || userRole === Role.ROLE_EMPLOYEE || userRole === Role.ROLE_ADMIN;
    };

    return <RoutesGuard roleCheck={roleCheck}>{children}</RoutesGuard>;
};

export default SharedRoutesGuard;