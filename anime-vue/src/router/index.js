import { createRouter, createWebHashHistory } from 'vue-router'
import routes from '@/router/routes'

// app router
export const router = createRouter({
    // history: createWebHistory(config.baseUrl), // History模式
    history: createWebHashHistory(),  // Hash模式
    routes,
    strict: true,
    scrollBehavior: () => ({ left: 0, top: 0 })
})

export default router
