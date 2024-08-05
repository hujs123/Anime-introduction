import HomePage from '@/views/home/homePage.vue' // 假设您的HomePage组件在views目录下

export const commonRoutes = [
    {
        path: '/',
        name: 'HomePage',
        meta: {title: '首页', entry: true,type:'1',typename:'首页'},
        component: HomePage
    }
]

export const commonWhites = []
