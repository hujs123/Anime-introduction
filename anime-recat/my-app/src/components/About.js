// components/About.js
import React, {useEffect, useState} from 'react';
import axios from "axios";

function About() {
    // 使用 useState 来创建一个状态，初始值为空字符串
    const [inputValue, setInputValue] = useState('');

    // 定义一个状态来存储接口返回的数组数据
    const [dataArray, setDataArray] = useState([]);
    // 定义一个事件处理函数，当输入框的值发生变化时更新状态
    const handleInputChange = (event) => {
        setInputValue(event.target.value);
        console.log('试着',event.target.value)
    };
    useEffect(() => {
        // 在组件加载时发送 GET 请求到接口
        axios.post('http://172.30.10.31:7300/mock/652396bf63eb8c002142ec43/mockmain/querydrughouse')
            .then(response => {
                // 请求成功，将返回的数据存储到状态中
                setDataArray(response.data.result);
            })
            .catch(error => {
                // 请求失败，处理错误
                console.error('Error fetching data:', error);
            });
    }, []); // 空数组作为第二个参数，确保 useEffect 只在组件加载时执行一次


    const test = () => {
        // 在组件加载时发送 GET 请求到 Easy Mock 的接口
        // axios.post('http://172.30.10.31:7300/mock/652396bf63eb8c002142ec43/mockmain/querydrughouse')
        axios.post('http://172.30.10.31:7300/mock/652396bf63eb8c002142ec43/mockmain/querydrughouse')

            .then(response => {
                // 请求成功，将返回的数据存储到状态中
                setDataArray(response.data.result);
            })
            .catch(error => {
                // 请求失败，处理错误
                console.error('Error fetching data:', error);
            });
        console.log(dataArray)
    };
    const test2 = () => {
        // 在组件加载时发送 GET 请求到 Easy Mock 的接口
        axios.post('api/users/getUsers')
            .then(response => {
                // 请求成功，将返回的数据存储到状态中
                // setDataArray(response);
                console.log(response)
            })
            .catch(error => {
                // 请求失败，处理错误
                console.error('Error fetching data:', error);
            });
        console.log(dataArray)
    };
    return (
        <div>
            {/* 输入框 */}
            <input
                type="text"
                value={inputValue} // 将输入框的值绑定到状态上
                onChange={handleInputChange} // 当输入框的值发生变化时触发事件处理函数
            />

            {/* 根据状态中的数组数据渲染页面 */}
            <ul>
                {dataArray.map(item => (
                    <li key={item.housecode}>{item.housename}</li>
                ))}
            </ul>
            <h2>About</h2>
            <button onClick={test}>获取接口信息1</button>
            <button onClick={test2}>获取接口信息2</button>
        </div>
    );


}

export default About;
