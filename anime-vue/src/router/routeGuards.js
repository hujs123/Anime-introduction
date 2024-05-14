/**
 * @name permission
 * @description 全局路由过滤、权限过滤
 */

// routeGuards.js
import { routeNameWhitelist } from '@/router/routes'

export function setupRouteGuards(router) {
  router.beforeEach((to, from, next) => {
    // 在这里添加你的路由守卫逻辑
    console.log('to',to.name)
    console.log('from',from.name)
    // 若为白名单，不需要登录可直接访问
    if (routeNameWhitelist.includes(to.name || '')) {
      return next()
    }
    // 检查用户是否已登录
    const isLoggedIn = localStorage.getItem('isLoggedIn');
    // const isLoggedIn = true;
    if (to.path !== '/login-page' && !isLoggedIn) {
      console.log(to.path)
      // 如果用户未登录且尝试访问非登录页面，重定向到登录页面
      console.log('未登录')
      next();
      // next({ name: 'loginPage' }) // 重定向到登录页面
    } else {
      // 否则，继续导航
      console.log('已登录')
      next();
    }
  });
// 添加 afterEach 守卫
  router.afterEach((to, from) => {
    // 这里是路由跳转后执行的代码
    // 注意：你不能在这里调用 next()，因为它已经是最后一个守卫了
    console.log('已经跳转到：', to.path);
    console.log('来自',from.name)
    // 你可以在这里添加其他你需要在路由跳转后执行的代码
    // 比如更新页面标题、发送统计信息、滚动到页面顶部等
  });
}
