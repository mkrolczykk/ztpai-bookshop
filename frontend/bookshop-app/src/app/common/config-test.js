// config-test.js

const AUTH_SERVICE_PORT = 8190;     // [LOCAL env] Auth service port (Default: DISABLED)
const BOOK_SERVICE_PORT = 8290;     // [LOCAL env] Bookshop service port (Default: DISABLED)

// endpoint template func
const createEndpoint = (port, endpoint) => `http://localhost:${port}/${endpoint}`;

const API_ENDPOINTS = {
    authenticate: createEndpoint(AUTH_SERVICE_PORT, 'auth/authenticate'),
    register: createEndpoint(AUTH_SERVICE_PORT, 'auth/register'),
    // TODO -> add all other endpoints
};

export default API_ENDPOINTS;
