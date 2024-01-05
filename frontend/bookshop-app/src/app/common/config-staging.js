// config-staging.js

const GATEWAY_SERVICE_PORT = 8081;  // [LOCAL env] Backend services Gateway service port

// endpoint template func
const createEndpoint = (port, endpoint) => `http://localhost:${port}/${endpoint}`;

const API_ENDPOINTS = {
    authenticate: createEndpoint(GATEWAY_SERVICE_PORT, 'auth/authenticate'),
    register: createEndpoint(GATEWAY_SERVICE_PORT, 'auth/register'),
    // TODO -> add all other endpoints
};

export default API_ENDPOINTS;
