import React from 'react';
import RoutesGuard from './RoutesGuard';

import Role from "../common/constants/Role";

const AdminRoutesGuard = ({ children }) => {

    const roleCheck = () => localStorage.getItem('user_role') === Role.ROLE_ADMIN;

    return <RoutesGuard roleCheck={roleCheck}>{children}</RoutesGuard>;
};

export default AdminRoutesGuard