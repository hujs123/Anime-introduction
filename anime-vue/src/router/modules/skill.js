
export const skillRoutes = [
    {
        path: '/minio-age',
        name: 'minioPage',
        meta: {title: 'minio文件管理'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/skill/minioPage.vue') // 示例中的动态导入
    }

]

export const skillWhites = []
