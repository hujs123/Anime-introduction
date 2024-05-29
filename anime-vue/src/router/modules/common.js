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
        path: '/login-page',
        name: 'loginPage',
        meta: {title: '登录'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/login/loginPage.vue') // 示例中的动态导入
    },
    //------------------------------
    {
        path: '/data-source-maintenance',
        name: 'dataSourceMaintenance',
        meta: { title: '待缴费处方列表', entry: true},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/home/dataSourceMaintenance.vue') // 示例中的动态导入
    },
    {
        path: '/login-page',
        name: 'loginPage',
        meta: { title: '处方医保结算' },
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/login/loginPage.vue') // 示例中的动态导入
    },
    {
        path: '/data-source-maintenance',
        name: 'dataSourceMaintenance',
        meta: { title: '处方自费结算' },
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/home/dataSourceMaintenance.vue') // 示例中的动态导入
    },
    {
        path: '/login-page',
        name: 'loginPage',
        meta: { title: '处方缴费结果' },
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/login/loginPage.vue') // 示例中的动态导入
    },
    {
        path: '/data-source-maintenance',
        name: 'dataSourceMaintenance',
        meta: { title: '药品项目二级页', entry: true},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/home/dataSourceMaintenance.vue') // 示例中的动态导入
    },
    {
        path: '/login-page',
        name: 'loginPage',
        meta: { title: '药品查询' },
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/login/loginPage.vue') // 示例中的动态导入
    },
    {
        path: '/data-source-maintenance',
        name: 'dataSourceMaintenance',
        meta: { title: '项目查询' },
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/home/dataSourceMaintenance.vue') // 示例中的动态导入
    },
    {
        path: '/login-page',
        name: 'loginPage',
        meta: { title: '电子票据入口', entry: true},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/login/loginPage.vue') // 示例中的动态导入
    },
    {
        path: '/data-source-maintenance',
        name: 'dataSourceMaintenance',
        meta: { title: '电子票据列表(打印多项)'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/home/dataSourceMaintenance.vue') // 示例中的动态导入
    },
    {
        path: '/login-page',
        name: 'loginPage',
        meta: { title: '电子票据列表(打印一项)'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/login/loginPage.vue') // 示例中的动态导入
    },
    {
        path: '/data-source-maintenance',
        name: 'dataSourceMaintenance',
        meta: { title: '电子票据扫码打印'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/home/dataSourceMaintenance.vue') // 示例中的动态导入
    },
    {
        path: '/login-page',
        name: 'loginPage',
        meta: { title: '检查列表1', entry: true},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/login/loginPage.vue') // 示例中的动态导入
    },
    {
        path: '/data-source-maintenance',
        name: 'dataSourceMaintenance',
        meta: { title: '检验列表1', entry: true},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/home/dataSourceMaintenance.vue') // 示例中的动态导入
    },
    {
        path: '/login-page',
        name: 'loginPage',
        meta: { title: '检查列表2' },
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/login/loginPage.vue') // 示例中的动态导入
    },
    {
        path: '/data-source-maintenance',
        name: 'dataSourceMaintenance',
        meta: {title: '检查列表(打印多项)'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/home/dataSourceMaintenance.vue') // 示例中的动态导入
    },
    {
        path: '/login-page',
        name: 'loginPage',
        meta: { title: '检查列表2' },
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/login/loginPage.vue') // 示例中的动态导入
    },    {
        path: '/data-source-maintenance',
        name: 'dataSourceMaintenance',
        meta: { title: '检验列表2' },
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/home/dataSourceMaintenance.vue') // 示例中的动态导入
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
