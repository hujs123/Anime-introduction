// src/http.js
import axios from 'axios';

// 创建一个 axios 实例
const service = axios.create({
    baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
    // withCredentials: true, // 跨域请求时是否需要使用凭据
    timeout: 5000 // 请求超时时间
});

// 添加请求拦截器
service.interceptors.request.use(
    config => {
        // 在发送请求之前做些什么
        // 例如，添加 token 到 headers
        if (localStorage.getItem('token')) {
            config.headers.Authorization = `Bearer ${localStorage.getItem('token')}`;
        }
        return config;
    },
    error => {
        // 对请求错误做些什么
        console.log(error); // for debug
        return Promise.reject(error);
    }
);

// 添加响应拦截器
service.interceptors.response.use(
    response => {
        // 对响应数据做点什么
        // 例如，检查状态码并返回相应的数据或错误
        const res = response.data;
        // if (res.code !== 200) {
        //     return Promise.reject(new Error(res.message || 'Error'));
        // }
        return res;
    },
    error => {
        // 对响应错误做点什么
        console.log('err' + error); // for debug
        return Promise.reject(error);
    }
);

export default service;
