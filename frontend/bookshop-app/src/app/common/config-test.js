// config-test.js

const AUTH_SERVICE_PORT = 8190;     // [LOCAL env] Auth service port (Default: DISABLED)
const BOOK_SERVICE_PORT = 8290;     // [LOCAL env] Bookshop service port (Default: DISABLED)

// endpoint template func
const createEndpoint = (port, endpoint) => `http://localhost:${port}/${endpoint}`;

const API_ENDPOINTS = {
    authenticate: createEndpoint(AUTH_SERVICE_PORT, 'auth/authenticate'),
    register: createEndpoint(AUTH_SERVICE_PORT, 'auth/register'),
    totalBooks: createEndpoint(BOOK_SERVICE_PORT, 'bookapp/api/v1/books/count'),
    genres: createEndpoint(BOOK_SERVICE_PORT, 'bookapp/api/v1/genres'),
    topSoldBooks: createEndpoint(BOOK_SERVICE_PORT, 'bookapp/api/v1/books/bestsellers'),
    recentlyAdded: createEndpoint(BOOK_SERVICE_PORT, 'bookapp/api/v1/books/recents'),
    favoriteBooksCount: createEndpoint(BOOK_SERVICE_PORT, 'bookapp/api/v1/favorites/count'),
    shoppingCartItemCount: createEndpoint(BOOK_SERVICE_PORT, 'bookapp/api/v1/cart/count'),
    // TODO -> add all other endpoints
};

export default API_ENDPOINTS;
