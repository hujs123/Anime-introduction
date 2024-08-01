
export const skillRoutes = [
    {
        path: '/minio-page',
        name: 'minioPage',
        meta: {title: 'minio文件管理'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/skill/minioPage.vue') // 示例中的动态导入
    },
    {
        path: '/captcha-page',
        name: 'captchaPage',
        meta: {title: '验证码验证'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/skill/captchaPage.vue') // 示例中的动态导入
    },
    {
        path: '/map-page',
        name: 'mapPage',
        meta: {title: '地图'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/skill/mapPage.vue') // 示例中的动态导入
    }

]

export const skillWhites = []
