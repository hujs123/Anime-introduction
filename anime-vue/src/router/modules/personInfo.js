
export const personInfoRoutes = [
    {
        path: '/person-info-page',
        name: 'PersonInfoPage',
        meta: {title: '人员管理'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/personInfo/personInfoPage.vue') // 示例中的动态导入
    }
    // ,
    // {
    //     path: '/person-info-',
    //     name: 'PersonInfo',
    //     meta: {title: '人员添加组件'},
    //     // 假设您有一个HomeImages组件，这里需要正确导入并使用
    //     component: () => import('@/views/personInfo/addPersonPage.vue') // 示例中的动态导入
    // },

]

export const personInfoWhites = []
