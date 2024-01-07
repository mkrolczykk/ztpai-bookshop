import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

import './orders-container.css';

import { faCaretDown, faCaretLeft, faCheck } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import API_ENDPOINTS from '../../common/config-staging';
import Role from "../../common/constants/Role";

const OrdersContainer = ({ orders, employees }) => {
    const [operationResult, setOperationResult] = useState(null);
    const navigate = useNavigate();

    const markAsFinished = async (orderId) => {
        try {
            await axios.put(API_ENDPOINTS.markAsFinished.replace('{order_id}', orderId));
            setOperationResult({ type: 'markAsFinished', success: true });
            navigate(0);
        } catch (error) {
            console.error('Error marking order as finished: ', error);
            setOperationResult({ type: 'markAsFinished', success: false });
        }
    };

    const assignToEmployee = async (orderId, employeeId) => {
        try {
            await axios.post(API_ENDPOINTS.assignToEmployee, {
                orderId: orderId,
                employeeId: employeeId,
            });
            setOperationResult({ type: 'assignToEmployee', success: true });
            navigate(0);
        } catch (error) {
            console.error('Error assigning employee to order: ', error);
            setOperationResult({ type: 'assignToEmployee', success: false });
        }
    };

    useEffect(() => {
        console.log('Operation result:', operationResult);
    }, [operationResult]);

    const toggleOrderDropdown = (orderId) => {
        // TODO -> add logic if needed
    };

    const orderCounts = orders.reduce((acc, order) => {
        acc[order.orderId] = (acc[order.orderId] || 0) + 1;
        return acc;
    }, {});

    return (
        <div className="orders-container">
            <section className="orders-section">
                <table className="orders-table">
                    <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Created at</th>
                        <th>Total</th>
                        <th>Currency</th>
                        <th>Status</th>
                        <th>Assigned to</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    {orders.map((order) => (
                        <tr
                            key={`${order.orderId}${orderCounts[order.orderId] > 1 ? `_${order.currency}` : ''}`}
                            id={`order-row-${order.orderId}`}
                        >
                            <td>{`${order.orderId}${orderCounts[order.orderId] > 1 ? `_${order.currency}` : ''}`}</td>
                            <td>{order.orderTime}</td>
                            <td>{order.total}</td>
                            <td>{order.currency}</td>
                            <td>{order.orderStatus}</td>
                            <td className="order-executor">
                                {order.orderExec !== ' ' ? (
                                    order.orderExecId == localStorage.getItem('user_id') ? 'Me' : order.orderExec
                                ) : (
                                    '-'
                                )}
                            </td>
                            <td>
                                <div className="order-dropdown">
                                    <button
                                        className="order-dropdown-btn"
                                        onClick={() => toggleOrderDropdown(order.orderId)}
                                    >
                                        <FontAwesomeIcon icon={faCaretDown} size="xl" />
                                    </button>
                                    <div className="order-dropdown-content">
                                        <a className="finish-action" onClick={() => markAsFinished(order.orderId)}>
                                            <FontAwesomeIcon icon={faCheck} /> Set as finished
                                        </a>
                                        {(localStorage.getItem('user_role') === Role.ROLE_ADMIN) && (
                                            <div className="assign-dropdown">
                                                <a
                                                    className="assign-to"
                                                    onClick={() => toggleOrderDropdown(order.orderId)}
                                                >
                                                    <FontAwesomeIcon icon={faCaretLeft} size="lg" /> Assign to
                                                </a>
                                                <div className="assign-dropdown-content">
                                                    {employees.map((employee) => (
                                                        <React.Fragment key={employee.employeeId}>
                                                            {localStorage.getItem('user_id') ===
                                                            employee.employeeId ? (
                                                                <a
                                                                    className="assign-option"
                                                                    onClick={() =>
                                                                        assignToEmployee(
                                                                            order.orderId,
                                                                            employee.employeeId
                                                                        )
                                                                    }
                                                                >
                                                                    Me
                                                                </a>
                                                            ) : (
                                                                <a
                                                                    className="assign-option"
                                                                    onClick={() =>
                                                                        assignToEmployee(
                                                                            order.orderId,
                                                                            employee.employeeId
                                                                        )
                                                                    }
                                                                >
                                                                    {`${employee.employeeName} ${employee.employeeSurname}`}
                                                                </a>
                                                            )}
                                                        </React.Fragment>
                                                    ))}
                                                </div>
                                            </div>
                                        )}
                                    </div>
                                </div>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </section>
        </div>
    );
};

export default OrdersContainer;