import { createApp } from 'vue'
import App from './App.vue'
import router from '@/router' // 假设您的路由文件在router目录下
import { setupRouteGuards } from './router/routeGuards'; // 引入路由守卫文件
import Antd from 'ant-design-vue';
import vuetable from 'vxe-table'
import './style/common.less';

// 创建 Vue Router 实例后，调用 setupRouteGuards 函数
setupRouteGuards(router);

// 读取env文件title给网页title
document.title=process.env.VUE_APP_TITLE
const app = createApp(App)
app.use(router)
app.use(Antd)
.use(vuetable)
app.mount('#app')
