// 默认路由
let constantRoutes = []

// 路由name白名单
const NameWhitelist = ['demo', '404', '500', '']

//首页
import {commonRoutes,commonWhites} from "@/router/modules/common";
constantRoutes = constantRoutes.concat(commonRoutes)

//异常界面
import {errPageRoutes,errPageWhites} from "@/router/modules/errPage";
constantRoutes = constantRoutes.concat(errPageRoutes)

//登录
import {loginRoutes,loginWhites} from "@/router/modules/login";
constantRoutes = constantRoutes.concat(loginRoutes)

//功能实现
import {skillRoutes,skillWhites} from "@/router/modules/skill";
constantRoutes = constantRoutes.concat(skillRoutes)

//字典
import {dictRoutes,dictWhites} from "@/router/modules/dict";
constantRoutes = constantRoutes.concat(dictRoutes)

//人员
import {personInfoRoutes,personInfoWhites} from "@/router/modules/personInfo";
constantRoutes = constantRoutes.concat(personInfoRoutes)

//人员
import {staffInfoRoutes,staffInfoWhites} from "@/router/modules/staffInfo";
constantRoutes = constantRoutes.concat(staffInfoRoutes)

//三方接口界面
import {thirdInterfaceRoutes,thirdInterfaceWhites} from "@/router/modules/thirdInterface";
constantRoutes = constantRoutes.concat(thirdInterfaceRoutes)

//测试界面
//中医
import {testPageRoutes,testPageWhites} from "@/router/modules/testPage";
constantRoutes = constantRoutes.concat(testPageRoutes)
export const routeNameWhitelist = NameWhitelist
    .concat(commonWhites)
    .concat(testPageWhites)
    .concat(skillWhites)
    .concat(dictWhites)
    .concat(personInfoWhites)
    .concat(loginWhites)
    .concat(staffInfoWhites)
    .concat(thirdInterfaceWhites)
    .concat(errPageWhites)

export default constantRoutes
