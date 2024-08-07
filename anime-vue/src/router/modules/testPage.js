export const testPageRoutes = [
    {
        path: '/test-page',
        name: 'testPage',
        meta: {title: '页面测试',type:'3',typename:'测试'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/test/testPage.vue') // 示例中的动态导入
    },
    {
        path: '/test-table',
        name: 'testTable',
        meta: {title: '表格测试',type:'3',typename:'测试'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/test/tableTestPage.vue') // 示例中的动态导入
    },
    {
        path: '/test-api',
        name: 'testApi',
        meta: {title: 'api测试',type:'3',typename:'测试'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/test/apiTestPage.vue') // 示例中的动态导入
    },
    {
        path: '/pinia-test-store',
        name: 'piniaTestStroe',
        meta: {title: 'piniastore测试',type:'3',typename:'测试'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/test/piniaStoreTestPage.vue') // 示例中的动态导入
    },
    {
        path: '/vuex-test-store',
        name: 'vuexTestStroe',
        meta: {title: 'vuexstore测试',type:'3',typename:'测试'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/test/vuexStoreTestPage.vue') // 示例中的动态导入
    }
]

export const testPageWhites = []
