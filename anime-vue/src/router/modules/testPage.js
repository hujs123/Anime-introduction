export const testPageRoutes = [
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
        path: '/test-store',
        name: 'testStroe',
        meta: {title: '测试store'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/test/storeTestPage.vue') // 示例中的动态导入
    }
]

export const testPageWhites = []
