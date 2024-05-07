import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../views/home/home_page.vue' // 假设您的HomePage组件在views目录下

const routes = [
    {
        path: '/',
        name: 'HomePage',
        component: HomePage
    },
    {
        path: '/home-images',
        name: 'HomeImages',
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('../views/images/home_images.vue') // 示例中的动态导入
    },
    {
        path: '/data-source-maintenance',
        name: 'dataSourceMaintenance',
        // 假设您有一个HomeImages组件，这里需要正确导入并使用
        component: () => import('../views/home/dataSourceMaintenance.vue') // 示例中的动态导入
    },
    // 其他路由...
    // 可以考虑添加一个404页面作为通配符路由
    {
      path: '/:catchAll(.*)*',
      component: () => import('../views/err_page/page_404.vue') // 示例中的动态导入
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router
