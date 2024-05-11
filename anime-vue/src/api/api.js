import {request} from "@/utils/request";

/**
 * hello
 * @param url
 * @param param
 * @param method
 * @returns {Promise<*|axios.AxiosResponse<any>|AxiosResponse<any>|undefined>}
 */
export function hello(url,param,method){
    return request(url,param,method)
}

/**
 * 获取用户信息
 * @param url
 * @param param
 * @param method
 * @returns {Promise<*|undefined>}
 */
export function getUsers(url,param,method){
    return request(url,param,method)
}
