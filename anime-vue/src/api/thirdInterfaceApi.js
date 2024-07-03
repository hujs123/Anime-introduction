import {request} from "@/utils/request";


/**
 *
 * @param param
 * @returns {Promise<*|AxiosResponse<any>|axios.AxiosResponse<any>|undefined>}
 */
export function getAcgImage(param){
    //随机图片
    let url='https://image.anosu.top/pixiv/json?num=2&r18=18&keyword=胖次'
    let num=5
    let r18=18
    let keyword='美少女'
    return request(url+'?num='+num+'&r18='+r18+'&keyword='+keyword,param,'GET')
}
