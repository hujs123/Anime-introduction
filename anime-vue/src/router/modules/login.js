export const loginRoutes = [
    {
        path: '/login-page',
        name: 'loginPage',
        meta: {title: '登录'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/login/loginPage.vue') // 示例中的动态导入
    }
]

export const loginWhites = []
