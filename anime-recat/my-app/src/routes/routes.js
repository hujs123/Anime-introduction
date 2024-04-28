import { Routes, Route } from 'react-router-dom';
import HomePage from '../pages/home/homepage';
import AboutPage from '../components/About';
import NotFoundPage from '../components/NotFound';

const routes = () => {
    return (
        <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/about" element={<AboutPage />} />
            <Route path="*" element={<NotFoundPage />} />
        </Routes>
    );
};

export default routes;
