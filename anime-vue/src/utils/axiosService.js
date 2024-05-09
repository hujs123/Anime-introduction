//axios请求
import axios from 'axios';

// 创建一个 axios 实例
const service = axios.create({
    baseURL: process.env.VUE_APP_BASE_URL, // 你可以在这里设置你的基础 URL
    timeout: 5000, // 请求超时时间
    // 你可以在这里添加其他 axios 配置项
});

// 请求拦截器
service.interceptors.request.use(
    config => {
        // 在发送请求之前做些什么
        // 例如，你可以在这里添加 token 到 headers
        // if (store.getters.token) {
        //   config.headers['X-Token'] = getToken()
        // }
        return config;
    },
    error => {
        // 对请求错误做些什么
        console.log(error); // for debug
        return Promise.reject(error);
    }
);

// 响应拦截器
service.interceptors.response.use(
    response => {
        // 对响应数据做点什么
        // 例如，你可以在这里处理错误码
        const res = response.data;
        if (res.code !== 200) {
            // 你可以在这里抛出错误或做其他处理
            return Promise.reject(new Error(res.message || 'Error'));
        }
        return res;
    },
    error => {
        console.log('err' + error); // for debug
        return Promise.reject(error);
    }
);

export default service;
