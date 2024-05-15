//Axios的拦截器实现HTTP响应拦截
import axios from 'axios';

// 创建一个 axios 实例
const axiosInstance = axios.create({
    baseURL: process.env.VUE_APP_BASE_URL, // 你可以在这里设置你的基础 URL
    timeout: 5000, // 请求超时时间
    // 你可以在这里添加其他 axios 配置项
});

// 请求拦截器
axiosInstance.interceptors.request.use(
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
axiosInstance.interceptors.response.use(
    response => {
        // 对响应数据做点什么
        if(response.status === 200){
            const res = response.data;
            if (res.code !== 0) {
                // 你可以在这里抛出错误或做其他处理
                return Promise.reject(new Error(res.message || 'Error'));
            }
            return res;
        }else{
            // 例如，你可以在这里处理错误码
            return  Promise.reject(response)
        }
    },
    error => {
        // 对响应错误做点什么
        // 这里可以处理网络错误、超时等问题
        // 比如，你可以在这里统一处理错误，并返回一个更友好的错误信息
        if(error.response){
            // 请求已发出，但服务器响应的状态码不在 2xx 范围内
            console.error('Error:', error.response.data, error.response.status, error.response.headers);
        }else if (error.request){
            // 请求已发出，但没有收到响应
            console.error('Error', error.request);
        }else{
            // 发送请求时发生了某些事情，导致请求没有被发出
            console.error('Error', error.message);
        }
        // 通常你会在这里返回一个rejected的Promise，并带上错误信息
        return Promise.reject(error);
    }
);

export default axiosInstance;
