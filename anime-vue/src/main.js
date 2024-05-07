import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index' // 假设您的路由文件在router目录下

// 读取env文件title给网页title
// document.title = import.meta.env.VUE_APP_TITLE
document.title=process.env.VUE_APP_TITLE
const app = createApp(App)
app.use(router)
app.mount('#app')
