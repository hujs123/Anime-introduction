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


/**
 * 根据条件查询干员
 * @param param:{
 *     "name": "", 姓名
 *     "gender": "",性别
 *     "ethnicity": "",种族
 *     "position": "",职位
 *     "tags": "",标签
 *     "organize": ""组织
 * }
 * @returns {Promise<*|axios.AxiosResponse<any>|undefined>}
 */
export function getStaff(param){
    request('/api/staff/getStaff', param, 'POST').then(res => {
        let response = res
        return response
    })
}

