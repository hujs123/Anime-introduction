// Home.js
import { useNavigate } from 'react-router-dom';
import '../../style/HomeStyle.css'
import styled from "styled-components";

function Home() {
    const navigate = useNavigate();

    const handleGoToAbout = () => {
        navigate('/about'); // 在这里进行页面导航到 '/about'
    };

    return (
        <div>
            {/*样式一*/}
            <h1 style={{ color: 'red', fontSize: '25px', fontFamily: 'Arial' }}>这里是首页</h1>
            {/*样式二*/}
            <h1 className="red-text">这里是首页</h1>
            <button onClick={handleGoToAbout}>这里是首页</button>
            {/*<div style={{background:'#ff3f3f',width:'100px',height:'150px'}}></div>*/}
        </div>
    );
}

export default Home;
