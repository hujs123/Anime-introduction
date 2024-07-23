
export const staffInfoRoutes = [
    {
        path: '/staff-info-page',
        name: 'StaffInfoPage',
        meta: {title: '干员管理'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/staffInfo/staffInfoPage.vue') // 示例中的动态导入
    }
]

export const staffInfoWhites = []
