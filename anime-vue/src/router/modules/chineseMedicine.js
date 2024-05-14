export const chineseMedicineRoutes = [
    {
        path: '/medicine-info',
        name: 'medicineInfo',
        meta: {title: '药品信息'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/chineseMedicine/medicineInfo.vue') // 示例中的动态导入
    },
]

export const chineseMedicineWhites = []
