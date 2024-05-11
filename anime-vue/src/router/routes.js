// 默认路由
let constantRoutes = []

// 路由name白名单
const NameWhitelist = ['demo', '404', '500', '']

import {errPageRoutes,errPageWhites} from "@/router/modules/errPage";
constantRoutes = constantRoutes.concat(errPageRoutes)

import {commonRoutes,commonWhites} from "@/router/modules/common";
constantRoutes = constantRoutes.concat(commonRoutes)
export const routeNameWhitelist = NameWhitelist
    .concat(errPageWhites)
    .concat(commonWhites)

export default constantRoutes
