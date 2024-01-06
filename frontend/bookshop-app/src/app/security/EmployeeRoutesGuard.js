import React from 'react';
import RoutesGuard from './RoutesGuard';

import Role from "../common/constants/Role";

const EmployeeRoutesGuard = ({ children }) => {

    const roleCheck = () => localStorage.getItem('user_role') === Role.ROLE_EMPLOYEE;

    return <RoutesGuard roleCheck={roleCheck}>{children}</RoutesGuard>;
};

export default EmployeeRoutesGuard