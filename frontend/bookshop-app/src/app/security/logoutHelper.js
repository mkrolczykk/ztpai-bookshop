import { useNavigate } from 'react-router-dom';

const LogoutHelper = () => {
    const navigate = useNavigate();

    const handleLogout = () => {
        // clear localStorage
        localStorage.clear();

        navigate('/login');
    };

    return {
        handleLogout,
    };
};

export default LogoutHelper;