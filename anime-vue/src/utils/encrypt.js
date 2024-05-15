// encryptionUtils.js
import CryptoJS from 'crypto-js';

// 密钥和向量（IV）应该是安全的随机值，这里仅为示例使用固定值
// const secretKey = 'YourSecretKey'; // 在实际应用中，这个值应该通过安全的方式生成和存储
// const iv = CryptoJS.enc.Utf8.parse('YourIV'); // IV 必须是 16 字节长

//加密方法
export function encryptByAes(plainText,secretKey,iv) {
  const cipher = CryptoJS.AES.encrypt(plainText, secretKey, {
    iv: iv,
    mode: CryptoJS.mode.CBC,
    padding: CryptoJS.pad.Pkcs7,
  });
  return cipher.toString();
}

//解密方法
export function decryptByAes(encryptedText,secretKey,iv) {
  const bytes = CryptoJS.AES.decrypt(encryptedText, secretKey, {
    iv: iv,
    mode: CryptoJS.mode.CBC,
    padding: CryptoJS.pad.Pkcs7,
  });
  return bytes.toString(CryptoJS.enc.Utf8);
}
//加密
export function encryptData(data, secretKey) {
  const cipher = CryptoJS.AES.encrypt(JSON.stringify(data), secretKey);
  return cipher.toString();
}
//解密
export function decryptData(ciphertext, secretKey) {
  const bytes = CryptoJS.AES.decrypt(ciphertext, secretKey);
  return JSON.parse(bytes.toString(CryptoJS.enc.Utf8));
}
