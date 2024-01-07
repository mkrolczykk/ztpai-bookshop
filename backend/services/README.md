# Bookshop backend services

## Project Modules

```
.
├── auth-service                # Authorization Service
├── book-service                # Bookshop Service
├── db                          # Database
├── gateway-service             # Gateway Service (backend proxy server)
├── gpt-service                 # GPT Service
├── modules                     # Backend common libraries, utils
├── notifications-service       # Notifications Service
.
```

The Gateway Service can be referred to as a "backend proxy server." This component 
serves as an intermediary between clients and the backend services, efficiently redirecting
traffic to the respective backend services. <br/>

In the context of microservices architecture, it acts as an API gateway, managing traffic, routing 
requests to the appropriate services, handling authentication, authorization, and other functions 
related to incoming client requests.
