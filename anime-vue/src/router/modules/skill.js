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
        component: () => import('@/views/skill/captchaPage.vue') // 示例中的动态导入
    },
    {
        path: '/map-page',
        name: 'mapPage',
        meta: {title: '地图'},
        component: () => import('@/views/skill/mapPage.vue') // 示例中的动态导入
    },
    {
        path: '/html-to-pdf-Page',
        name: 'htmlToPdfPage',
        meta: {title: 'html转pdf'},
        component: () => import('@/views/skill/htmlToPdfPage.vue') // 示例中的动态导入
    },
    {
        path: '/watermark-Page',
        name: 'watermarkPage',
        meta: {title: '水印'},
        component: () => import('@/views/skill/watermarkPage.vue') // 示例中的动态导入
    }

]

export const skillWhites = []
