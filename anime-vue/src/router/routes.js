// 默认路由
let constantRoutes = []

// 路由name白名单
const NameWhitelist = ['demo', '404', '500', '']

//异常界面
import {errPageRoutes,errPageWhites} from "@/router/modules/errPage";
constantRoutes = constantRoutes.concat(errPageRoutes)

//
import {commonRoutes,commonWhites} from "@/router/modules/common";
constantRoutes = constantRoutes.concat(commonRoutes)

//中医
import {chineseMedicineRoutes,chineseMedicineWhites} from "@/router/modules/chineseMedicine";
constantRoutes = constantRoutes.concat(chineseMedicineRoutes)

//测试界面
//中医
import {testPageRoutes,testPageWhites} from "@/router/modules/testPage";
constantRoutes = constantRoutes.concat(testPageRoutes)
export const routeNameWhitelist = NameWhitelist
    .concat(errPageWhites)
    .concat(commonWhites)
    .concat(chineseMedicineWhites)
    .concat(testPageWhites)

export default constantRoutes
