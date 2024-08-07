import {createApp} from 'vue'
import App from './App.vue'
import router from '@/router' // 假设您的路由文件在router目录下
import {setupRouteGuards} from './router/routeGuards'; // 引入路由守卫文件
import Antd from 'ant-design-vue';
// import zhCN from 'ant-design-vue/es/locale/zh_CN'; // 引入中文语言包
import vuetable from 'vxe-table'
import 'vxe-table/lib/style.css'

//样式管理
import './style/common.less';

//vuex状态管理库
import store from './store/modules/common'

//pinia状态管理库
import {createPinia} from 'pinia'

// const cors = require('cors');

// 创建 Vue Router 实例后，调用 setupRouteGuards 函数
setupRouteGuards(router);

// 读取env文件title给网页title
document.title = process.env.VUE_APP_TITLE
const app = createApp(App)
// app.use(Antd, {locale: zhCN})
app.use(store)
app.use(createPinia())
app.use(router)
app.use(Antd)
app.use(vuetable)
app.mount('#app')
