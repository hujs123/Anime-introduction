// 默认路由
export const errPageRoutes = [
    {
        path: '/:catchAll(.*)*',
        meta: {title: '404页面'},
        component: () => import('@/views/error/404Page.vue') // 示例中的动态导入
    },
    {
        path: '/404',
        component: () => import('@/views/error/404Page.vue'),
        name: '404',
        meta: { title: '404' }
    },
    {
        path: '/500',
        name: '500',
        component: () => import('@/views/error/500Page.vue'),
        meta: { title: '500' }
    }
]

export const errPageWhites = []
