// import React from 'react';
//
// import './orders-container.css'; // Import your CSS file for styling
//
// const OrdersContainer = ({ orders, employees }) => {
//     const markAsFinished = (orderId) => {
//         // Your markAsFinished logic here
//     };
//
//     const assignToEmployee = (orderId, employeeId, employeeName) => {
//         // Your assignToEmployee logic here
//     };
//
//     const toggleOrderDropdown = (btn) => {
//         // Your toggleOrderDropdown logic here
//     };
//
//     return (
//         <div className="orders-container">
//             <section className="orders-section">
//                 <table className="orders-table">
//                     <thead>
//                     <tr>
//                         <th>Order ID</th>
//                         <th>Created at</th>
//                         <th>Total</th>
//                         <th>Currency</th>
//                         <th>Status</th>
//                         <th>Assigned to</th>
//                         <th>Action</th>
//                     </tr>
//                     </thead>
//                     <tbody>
//                     {orders.map((order) => (
//                         <tr key={order.getOrderId()} id={`order-row-${order.getOrderId()}`}>
//                             <td>{order.getOrderId()}</td>
//                             <td>{order.getOrderTime()}</td>
//                             <td>{order.getTotal()}</td>
//                             <td>{order.getCurrency()}</td>
//                             <td>{order.getOrderStatus()}</td>
//                             <td className="order-executor">
//                                 {order.getOrderExec() !== null ? (order.getOrderExecId() === sessionStorage.getItem('id') ? 'Me' : order.getOrderExec()) : '-'}
//                             </td>
//                             <td>
//                                 <div className="order-dropdown">
//                                     <button className="fa fa-caret-down fa-xl order-dropdown-btn" onClick={() => toggleOrderDropdown(btn)}></button>
//                                     <div className="order-dropdown-content">
//                                         <a className="finish-action" onClick={() => markAsFinished(order.getOrderId())}>
//                                             Set as finished
//                                         </a>
//                                         {((order.getOrderExec() === null) || (sessionStorage.getItem('roleId') === 'admin')) && (
//                                             <div className="assign-dropdown">
//                                                 <a className="assign-to" onClick={() => toggleOrderDropdown(btn)}>
//                                                     <span className="fa fa-caret-left fa-lg"></span>Assign to
//                                                 </a>
//                                                 <div className="assign-dropdown-content">
//                                                     {employees.map((employee) => (
//                                                         <React.Fragment key={employee.getEmployeeId()}>
//                                                             {sessionStorage.getItem('id') === employee.getEmployeeId() ? (
//                                                                 <a className="assign-option" onClick={() => assignToEmployee(order.getOrderId(), employee.getEmployeeId(), 'Me')}>
//                                                                     Me
//                                                                 </a>
//                                                             ) : (
//                                                                 <a
//                                                                     className="assign-option"
//                                                                     onClick={() => assignToEmployee(order.getOrderId(), employee.getEmployeeId(), `${employee.getEmployeeName()} ${employee.getEmployeeSurname()}`)}>
//                                                                     {`${employee.getEmployeeName()} ${employee.getEmployeeSurname()}`}
//                                                                 </a>
//                                                             )}
//                                                         </React.Fragment>
//                                                     ))}
//                                                 </div>
//                                             </div>
//                                         )}
//                                     </div>
//                                 </div>
//                             </td>
//                         </tr>
//                     ))}
//                     </tbody>
//                 </table>
//             </section>
//         </div>
//     );
// };
//
// export default OrdersContainer;