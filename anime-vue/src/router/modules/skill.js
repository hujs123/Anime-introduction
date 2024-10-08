export const skillRoutes = [
    {
        path: '/minio-page',
        name: 'minioPage',
        meta: {title: 'minio文件管理',type:'3',typename:'技术实现'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/skill/minioPage.vue') // 示例中的动态导入
    },
    {
        path: '/captcha-page',
        name: 'captchaPage',
        meta: {title: '验证码验证',type:'3',typename:'技术实现'},
        component: () => import('@/views/skill/captchaPage.vue')
    },
    {
        path: '/map-page',
        name: 'mapPage',
        meta: {title: '地图',type:'3',typename:'技术实现'},
        component: () => import('@/views/skill/mapPage.vue')
    },
    {
        path: '/html-to-pdf-page',
        name: 'htmlToPdfPage',
        meta: {title: 'html转pdf',type:'3',typename:'技术实现'},
        component: () => import('@/views/skill/htmlToPdfPage.vue')
    },
    {
        path: '/watermark-page',
        name: 'watermarkPage',
        meta: {title: '界面水印水印',type:'3',typename:'技术实现'},
        component: () => import('@/views/skill/watermarkPage.vue')
    },
    {
        path: '/router-list-Page',
        name: 'routerListPage',
        meta: {title: '路由列表',type:'3',typename:'技术实现'},
        component: () => import('@/views/skill/routerListPage.vue')
    }

]

export const skillWhites = []
