// config-staging.js

const GATEWAY_SERVICE_PORT = 8081;  // [LOCAL env] Backend services Gateway service port

// endpoint template func
const createEndpoint = (port, endpoint) => `http://localhost:${port}/${endpoint}`;

const API_ENDPOINTS = {
    authenticate: createEndpoint(GATEWAY_SERVICE_PORT, 'auth/authenticate'),
    register: createEndpoint(GATEWAY_SERVICE_PORT, 'auth/register'),
    employeesList: createEndpoint(GATEWAY_SERVICE_PORT, 'auth/employees'),
    totalBooks: createEndpoint(GATEWAY_SERVICE_PORT, 'bookapp/api/v1/books/count'),
    genres: createEndpoint(GATEWAY_SERVICE_PORT, 'bookapp/api/v1/genres'),
    topSoldBooks: createEndpoint(GATEWAY_SERVICE_PORT, 'bookapp/api/v1/books/bestsellers'),
    recentlyAdded: createEndpoint(GATEWAY_SERVICE_PORT, 'bookapp/api/v1/books/recents'),
    favoriteBooksCount: createEndpoint(GATEWAY_SERVICE_PORT, 'bookapp/api/v1/favorites/count'),
    shoppingCartItemCount: createEndpoint(GATEWAY_SERVICE_PORT, 'bookapp/api/v1/cart/count'),
    userFavoriteBooks: createEndpoint(GATEWAY_SERVICE_PORT, 'bookapp/api/v1/favorites'),
    ordersList: createEndpoint(GATEWAY_SERVICE_PORT, 'bookapp/api/v1/orders'),
    markAsFinished: createEndpoint(GATEWAY_SERVICE_PORT, 'bookapp/api/v1/orders/order/{order_id}/status/pending'),
    assignToEmployee: createEndpoint(GATEWAY_SERVICE_PORT, 'bookapp/api/v1/orders/order/assign'),
    // TODO -> add all other endpoints
};

export default API_ENDPOINTS;
