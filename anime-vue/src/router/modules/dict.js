
export const dictRoutes = [
    {
        path: '/dict-page',
        name: 'DictPage',
        meta: {title: '字典管理',type:'2',typename:'功能'},
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('@/views/dict/dictPage.vue') // 示例中的动态导入
    }
]

export const dictWhites = []
