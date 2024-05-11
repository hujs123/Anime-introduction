目录结构
my-vue3-project/  
│  
├── public/                       # 静态资源目录，不会被webpack处理  
│   ├── favicon.ico  
│   └── index.html  
│  
├── src/                          # 源代码目录  
│   ├── assets/                   # 存放项目中需要导入的静态资源，如图片、字体文件等  
│   │   ├── images/  
│   │   └── fonts/  
│   ├── components/               # 存放可复用的Vue组件，通常按照功能或页面进行组织  
│   │   ├── Common/               # 存放通用组件  
│   │   │   └── ...  
│   │   ├── FeatureA/             # 存放与功能A相关的组件  
│   │   │   └── ...  
│   │   └── ...  
│   ├── views/                    # 存放页面级别的Vue组件，通常每个组件对应一个路由页面  
│   │   ├── Home.vue  
│   │   ├── About.vue  
│   │   ├── FeatureA.vue  
│   │   └── ...  
│   ├── router/                   # 存放Vue Router的配置文件  
│   │   └── index.js  
│   ├── store/                    # 存放Vuex状态管理的相关文件  
│   │   ├── index.js              # Vuex主文件  
│   │   ├── modules/              # Vuex模块  
│   │   │   ├── featureA.js  
│   │   │   └── ...  
│   │   └── ...  
│   ├── apis/                     # 存放API请求相关代码，可以是axios封装等  
│   │   ├── userApi.js  
│   │   ├── productApi.js  
│   │   └── ...  
│   ├── utils/                    # 存放工具函数或类  
│   │   ├── request.js            # 封装好的HTTP请求工具  
│   │   └── ...  
│   ├── App.vue                   # Vue 3项目的根组件，通常包含整个应用的布局和路由视图  
│   ├── main.js                   # Vue 3项目的入口文件，用于初始化Vue应用、导入全局样式和配置等  
│   └── ...  
│  
├── .gitignore                    # Git忽略文件  
├── babel.config.js               # Babel配置文件  
├── package.json                  # 项目依赖及脚本等配置  
├── vue.config.ts                 # Vue CLI配置文件  
├── README.md                     # 项目说明文件  
└── ...




import {createRouter, createWebHistory} from 'vue-router'
import HomePage from '../views/home/home_page.vue' // 假设您的HomePage组件在views目录下

const routes = [
{
path: '/',
name: 'HomePage',
meta: {title: '首页', entry: true},
component: HomePage
},
{
path: '/home-images',
name: 'HomeImages',
meta: {title: '图片首页'},
// 假设您有一个HomeImages组件，这里需要正确导入并使用
component: () => import('../views/images/home_images.vue') // 示例中的动态导入
},
{
path: '/data-source-maintenance',
name: 'dataSourceMaintenance',
meta: {title: '指标统计'},
// 假设您有一个HomeImages组件，这里需要正确导入并使用
component: () => import('../views/home/dataSourceMaintenance.vue') // 示例中的动态导入
},
{
path: '/test-page',
name: 'testPage',
meta: {title: '测试页面'},
// 假设您有一个HomeImages组件，这里需要正确导入并使用
component: () => import('../views/test/test_page.vue') // 示例中的动态导入
},
{
path: '/test-table',
name: 'testTable',
meta: {title: '表格'},
// 假设您有一个HomeImages组件，这里需要正确导入并使用
component: () => import('../views/test/test_table.vue') // 示例中的动态导入
},
{
path: '/test-api',
name: 'testApi',
meta: {title: 'api页面'},
// 假设您有一个HomeImages组件，这里需要正确导入并使用
component: () => import('../views/test/test_api.vue') // 示例中的动态导入
},
// 其他路由...
// 可以考虑添加一个404页面作为通配符路由
{
path: '/:catchAll(.*)*',
meta: {title: '404页面'},
component: () => import('../views/err_page/page_404.vue') // 示例中的动态导入
}
]

const router = createRouter({
history: createWebHistory(),
routes,
})

export default router

