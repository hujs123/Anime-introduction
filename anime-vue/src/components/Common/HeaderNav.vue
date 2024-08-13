<template>
  <div>
    <nav class="header-nav">
      <div class="logo">
        <img :src="logo" alt="Logo">
      </div>
      <ul class="nav-links">
        <li v-for="item in routerList" :key="item.path">
          <span @click.stop="selectMenu(item)">{{ item.typename }}</span>
          <router-link :to="item.List[0].path"></router-link>
        </li>
      </ul>
      <div class="search-box">
        <input type="text" placeholder="搜索...">
        <button>搜索</button>
      </div>
      <div class="user-info">
        <!-- 假设有一个用户登录状态 -->
        <span v-if="isLoggedIn">你好, {{ userName }}</span>
        <span v-else style="cursor: pointer;color: #007bff /* 鼠标悬停时显示手指指针 */" @click="login">登录</span>
      </div>
    </nav>
  </div>
</template>

<script setup>
import {onMounted, ref, defineEmits, defineProps, watch} from 'vue';
import {useRouter} from "vue-router";
import logo from '@/assets/images/logo.png';


const router = useRouter() // 使用useRouter hook代替直接导入router实例
const emit = defineEmits(['select-menu', 'update:login-model'])
const isLoggedIn = ref()
const userName = ref()
let routerList = ref([])

const props = defineProps({
  loginModel: Boolean
});
// 控制弹窗显示
const showModel = ref(false);

onMounted(() => {
  init();
});
const init = () => {
  console.log('router.options.routes',router.options.routes)
  getRouterList()
}

// 监听 prop 的变化以重新初始化 menuItems
watch(() => props.loginModel, (newValue) => {
  showModel.value = newValue
  console.log('标题',newValue)
});

const selectMenu = (item) => {
  if (item.List && item.List.length > 0) {
    router.push(item.List[0].path); // 使用Vue Router的push方法导航
  }
  console.log('item', item.List[0].path)
  emit('select-menu', item.type);
}
const login = () => {
  showModel.value = true
  emit('update:login-model', showModel.value);
}


//获取路由
const getRouterList = () => {
  isLoggedIn.value = false
  userName.value = '访客'
  // 假设 router.options.routes 已经定义
  for (let i = 0; i < router.options.routes.length; i++) {
    let found = false;
    for (let p = 0; p < routerList.value.length; p++) {
      if (router.options.routes[i].meta.type === routerList.value[p].type) {
        let param = {title: router.options.routes[i].meta.title, path: router.options.routes[i].path};
        routerList.value[p].List.push(param);
        found = true;
        break;
      }
    }
    if (!found) {
      // 如果没有找到匹配的 type，则向 routerList 添加一个新项
      routerList.value.push({
        type: router.options.routes[i].meta.type,
        typename: router.options.routes[i].meta.typename, // 这里可能需要一个更具体的值，或者从其他地方获取
        List: [{
          title: router.options.routes[i].meta.title,
          path: router.options.routes[i].path
        }]
      });
    }
  }
// 可以在这里或任何地方添加对 routerList 的输出或处理
//   console.log('routerList.value', routerList.value);
}

</script>

<style scoped>
.header-nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
}

.logo img {
  height: 40px; /* 设置logo图片的高度 */
  margin-right: 20px; /* 与导航链接之间添加一些间隔 */
}

.nav-links {
  list-style: none;
  padding: 0;
  display: flex;
}

.nav-links li {
  margin-right: 10px; /* 导航项之间的间隔 */
}

.nav-links li span {
  cursor: pointer; /* 指示这是可点击的 */
  font-weight: bold; /* 使文本加粗 */
}

.search-box {
  display: flex;
  align-items: center;
  margin-left: 20px; /* 与用户信息部分之间添加间隔 */
}

.search-box input {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  margin-right: 8px; /* 与搜索按钮之间的间隔 */
}

.search-box button {
  padding: 8px 16px;
  background-color: #1890ff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.user-info {
  font-size: 14px;
}

.user-info span {
  margin-right: 10px; /* 如果有多个用户信息项，则添加间隔 */
}
</style>
