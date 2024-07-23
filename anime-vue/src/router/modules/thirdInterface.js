
export const thirdInterfaceRoutes = [
    {
        path: '/third-interface-page',
        name: 'ThirdInterfacePage',
        meta: {title: '三方接口'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/thirdInterface/thirdInterfacePage.vue') // 示例中的动态导入
    }
]

export const thirdInterfaceWhites = []
