//校验，先判断是否为空（应不为空）
//将获取到的身份证号全部转换为大写（应为纯数字或者大写英文字母加数字）
//判断身份证长度（外国人身份证号新版为18位长度，旧版位15位长度，中国国民身份证为18位）
//外国人身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
//第一位校验是否是国外，2和3位校验受理地代码，4、5、6位为外国人国家地区代码，7-14位为出生日期，15-17位为顺序码，18位为校验码

// 1、读取号码。
// 2、判断是否为空
// 3、号码转换为大写
// 4、判断号码长度，15\18时正常
// 5、长度为15时（判断国家地区代码3、地址码4、出生日期6、顺序码1、校验码1）
// 6、长度为18时（判断身份表示码1（9为外国人）、受理地区2、所属国家或地区3、出生日期8、顺序码3、校验码1）
//
// 读取永久居住证注意信息：
// 旧版 英文姓名、性别、国籍、证件号码、中文姓名、出生日期、受理地代码、证件类型（I）
// 新版 英文姓名、英文姓名备用、性别、所属国籍或地区代码、出生日期、证件号码、证件类型(y)

// validData() {
//     let num = this.formdata.idcardno
//     if (
//         num === undefined ||
//         num === 'undefined' ||
//         num === null ||
//         num === '' ||
//         num.length === 0
//     ) {
//         this.tip = '身份证号不能为空'
//         this.$refs.idcardno.focus()
//         return false
//     }
//
//     num = num.toUpperCase()
//
//     let len, re
//     len = num.length
//     if (len === 0) return true
//
//     //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
//     if (!/(^(\d{15}|\d{17}(\d|X)|[A-Z]{3}\d{4}\d{6}\d{1}[0-9X]|9\d{16}[0-9X])$)/.test(num)) {
//         this.tip = '身份证输入错误，请重新输入'
//         this.$refs.idcardno.focus()
//         return false
//     }
//
//     //验证前两位地区是否有效
//     let aCity = {
//         11: '北京',
//         12: '天津',
//         13: '河北',
//         14: '山西',
//         15: '内蒙古',
//         21: '辽宁',
//         22: '吉林',
//         23: '黑龙江',
//         31: '上海',
//         32: '江苏',
//         33: '浙江',
//         34: '安徽',
//         35: '福建',
//         36: '江西',
//         37: '山东',
//         41: '河南',
//         42: '湖北',
//         43: '湖南',
//         44: '广东',
//         45: '广西',
//         46: '海南',
//         50: '重庆',
//         51: '四川',
//         52: '贵州',
//         53: '云南',
//         54: '西藏',
//         61: '陕西',
//         62: '甘肃',
//         63: '青海',
//         64: '宁夏',
//         65: '新疆',
//         71: '台湾',
//         81: '香港',
//         82: '澳门',
//         91: '国外'
//     }
//
//     if (!/(^[A-Z]{3}$)/.test(num.substr(0, 3))) {
//         if (num.substr(0, 1) !== '9') {
//             if (aCity[parseInt(num.substr(0, 2))] == null) {
//                 this.tip = '身份证地区校验未通过，请重新输入'
//                 this.$refs.idcardno.focus()
//                 return false
//             }
//         }
//     }
//
//     //当身份证为15位时的验证出生日期。
//     if (len === 15) {
//         if (!/(^[A-Z]{3}$)/.test(num.substr(0, 3))) {
//             re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/)
//             let arrSplit = num.match(re)
//             //检查生日日期是否正确
//             let dtmBirth = new Date(
//                 '19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]
//             )
//             let bGoodDay
//             bGoodDay =
//                 dtmBirth.getYear() === Number(arrSplit[2]) &&
//                 dtmBirth.getMonth() + 1 === Number(arrSplit[3]) &&
//                 dtmBirth.getDate() === Number(arrSplit[4])
//             if (!bGoodDay) {
//                 this.tip = '身份证出生日期格式错误，请重新输入'
//                 this.$refs.idcardno.focus()
//                 return false
//             }
//         } else {
//             re = new RegExp(/^([A-Z]{3})(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})$/)
//             let arrSplit = num.match(re)
//             //检查生日日期是否正确
//             let dtmBirth = new Date(
//                 '19' + arrSplit[3] + '/' + arrSplit[4] + '/' + arrSplit[5]
//             )
//             let bGoodDay
//             bGoodDay =
//                 dtmBirth.getYear() === Number(arrSplit[3]) &&
//                 dtmBirth.getMonth() + 1 === Number(arrSplit[4]) &&
//                 dtmBirth.getDate() === Number(arrSplit[5])
//             if (!bGoodDay) {
//                 this.tip = '身份证出生日期格式错误，请重新输入'
//                 this.$refs.idcardno.focus()
//                 return false
//             }
//         }
//     }1*7+3*9+0*10+5*5+2*8+7*4+1*2+9*1+9*6+6*3+0*7+3*9+2*10+8*5+2*8+0*4+9*2
//7+27+0+25+16+28+2+9+54+18+0+27+20+40+16+0+18
//     //当身份证号为18位时，校验出生日期和校验位。
//     if (len === 18) {
//         re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/)
//         let arrSplit = num.match(re)
//         //检查生日日期是否正确
//         let dtmBirth = new Date(
//             arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]
//         )
//         let bGoodDay
//         bGoodDay =
//             dtmBirth.getFullYear() === Number(arrSplit[2]) &&
//             dtmBirth.getMonth() + 1 === Number(arrSplit[3]) &&
//             dtmBirth.getDate() === Number(arrSplit[4])
//         if (!bGoodDay) {
//             this.tip = '身份证出生日期格式错误，请重新输入'
//             this.$refs.idcardno.focus()
//             return false
//         } else {
//             //检验18位身份证的校验码是否正确。
//             //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
//             let valnum
//             let arrInt = new Array(
//                 7,
//                 9,
//                 10,
//                 5,
//                 8,
//                 4,
//                 2,
//                 1,
//                 6,
//                 3,
//                 7,
//                 9,
//                 10,
//                 5,
//                 8,
//                 4,
//                 2
//             )
//             let arrCh = new Array(
//                 '1',
//                 '0',
//                 'X',
//                 '9',
//                 '8',
//                 '7',
//                 '6',
//                 '5',
//                 '4',
//                 '3',
//                 '2'
//             )
//             let nTemp = 0,
//                 i
//             for (i = 0; i < 17; i++) {
//                 nTemp += num.substr(i, 1) * arrInt[i]
//             }
//             valnum = arrCh[nTemp % 11]
//             if (valnum !== num.substr(17, 1)) {
//                 this.tip = '身份证校验位未通过，请重新输入'
//                 this.$refs.idcardno.focus()
//                 return false
//             }
//         }
//     }
//     return true
// }
