// encryptionUtils.js
import CryptoJS from 'crypto-js';

// 默认的 KEY 与 iv 如果没有给
const KEY = CryptoJS.enc.Utf8.parse("qazwsxedcrfv");
const IV = CryptoJS.enc.Utf8.parse('qazwsxed');


/**
 * AES加密 ：字符串 key iv  返回base64
 * 前后端交互加密方法
 */
export function selfencrypt(word, keyStr, ivStr) {
  let key = KEY;
  let iv = IV;
  if (keyStr) {
    key = CryptoJS.enc.Utf8.parse(keyStr);
    iv = CryptoJS.enc.Utf8.parse(ivStr);
  }
  let srcs = CryptoJS.enc.Utf8.parse(word);
  var encrypted = CryptoJS.AES.encrypt(srcs, key, {
    iv: iv,
    mode: CryptoJS.mode.CBC,
    padding: CryptoJS.pad.ZeroPadding
  });
  return CryptoJS.enc.Base64.stringify(encrypted.ciphertext);
}

//
/**
 * AES 解密 ：字符串 key iv  返回base64
 * 前后端交互解密方法
 * @return {string}
 */
export function selfdecrypt(word, keyStr, ivStr) {
  let key  = KEY;
  let iv = IV;

  if (keyStr) {
    key = CryptoJS.enc.Utf8.parse(keyStr);
    iv = CryptoJS.enc.Utf8.parse(ivStr);
  }

  let base64 = CryptoJS.enc.Base64.parse(word);
  let src = CryptoJS.enc.Base64.stringify(base64);

  let decrypt = CryptoJS.AES.decrypt(src, key, {
    iv: iv,
    mode: CryptoJS.mode.CBC,
    padding: CryptoJS.pad.ZeroPadding
  });

  let decryptedStr = decrypt.toString(CryptoJS.enc.Utf8);
  return decryptedStr.toString();
}
//前端自己加密方法
export function encryptData(data, secretKey) {
  const cipher = CryptoJS.AES.encrypt(JSON.stringify(data), secretKey);
  return cipher.toString();
}
//前端自己解密方法
export function decryptData(ciphertext, secretKey) {
  const bytes = CryptoJS.AES.decrypt(ciphertext, secretKey);
  return JSON.parse(bytes.toString(CryptoJS.enc.Utf8));
}
