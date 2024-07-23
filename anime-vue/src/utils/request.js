//不走加密网关
import axios from '@/utils/axiosService'
//走加密网关
import {encryptByAes,decryptByAes} from "@/utils/encrypt"
import CryptoJS from "crypto-js";

const secretKey = 'YourSecretKey'; // 在实际应用中，这个值应该通过安全的方式生成和存储
const iv = CryptoJS.enc.Utf8.parse('YourIV'); // IV 必须是 16 字节长

export async function request(url, params, method) {
    const isencrypt = process.env.API_ENCRYPT
    if (isencrypt) {
        console.log('加密', url, params)
        if (method == 'GET') {
            let param  = encryptByAes(params,secretKey,iv);
            let decryptres = await axios.get(url, param)
            return decryptByAes(decryptres)
        } else if (method == 'POST') {
            let param  = encryptByAes(params,secretKey,iv);
            let decryptres = await axios.post(url, param)
            return decryptByAes(decryptres)
        }
    } else {
        console.log('不加密', url, params)
        if (method == 'GET') {
            return await axios.get(url, params)
        } else if (method == 'POST') {
            return await axios.post(url, params)
        }
    }
}
