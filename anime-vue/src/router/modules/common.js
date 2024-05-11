import HomePage from '@/views/home/homePage.vue' // 假设您的HomePage组件在views目录下

export const commonRoutes = [
    {
        path: '/',
        name: 'HomePage',
        meta: {title: '首页', entry: true},
        component: HomePage
    },
    {
        path: '/home-images',
        name: 'HomeImages',
        meta: {title: '图片首页'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/images/imagePage.vue') // 示例中的动态导入
    },
    {
        path: '/data-source-maintenance',
        name: 'dataSourceMaintenance',
        meta: {title: '指标统计'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/home/dataSourceMaintenance.vue') // 示例中的动态导入
    },
    {
        path: '/test-page',
        name: 'testPage',
        meta: {title: '测试页面'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/test/testPage.vue') // 示例中的动态导入
    },
    {
        path: '/test-table',
        name: 'testTable',
        meta: {title: '测试表格'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/test/tableTestPage.vue') // 示例中的动态导入
    },
    {
        path: '/test-api',
        name: 'testApi',
        meta: {title: '测试api'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/test/apiTestPage.vue') // 示例中的动态导入
    },
    {
        path: '/login-page',
        name: 'loginPage',
        meta: {title: '登录'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/login/loginPage.vue') // 示例中的动态导入
    },
]

export const commonWhites = []
