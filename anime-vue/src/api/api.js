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

export function getAcgImage(param){
    //随机图片
    let url='https://image.anosu.top/pixiv/json?num=2&r18=18&keyword=胖次'
    let num=5
    let r18=18
    let keyword='美少女'
    return request(url+'?num='+num+'&r18='+r18+'&keyword='+keyword,param,'GET')
}
