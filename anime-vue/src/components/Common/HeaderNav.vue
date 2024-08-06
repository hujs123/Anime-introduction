<template>
  <nav class="header-nav">
    <div class="logo">
      <img :src="logo" alt="Logo">
    </div>
    <ul class="nav-links">
      <li v-for="item in routerList" :key="item.path">
        <span @click.stop="selectMenu(item)">{{ item.typename }}</span>
      </li>
    </ul>
    <div class="search-box">
      <input type="text" placeholder="搜索...">
      <button>搜索</button>
    </div>
    <div class="user-info">
      <!-- 假设有一个用户登录状态 -->
      <span v-if="isLoggedIn">你好, {{ userName }}</span>
      <span v-else><router-link to="/login">登录</router-link></span>
    </div>
  </nav>
</template>

<script setup>
import {onMounted, ref,defineEmits} from 'vue';
import {useRouter} from "vue-router";
import logo from '@/assets/images/logo.png';

const router = useRouter() // 使用useRouter hook代替直接导入router实例
const emit = defineEmits(['select-menu'])
const isLoggedIn = ref()
const userName = ref()
let routerList = ref([])

const selectMenu = (item) => {
  emit('select-menu', item);
}
onMounted(() => {
  init();
});
const init = () => {
  console.log('主界面')
  isLoggedIn.value = false
  userName.value = '访客'

  // 假设 router.options.routes 已经定义
  for (let i = 0; i < router.options.routes.length; i++) {
    let found = false;
    for (let p = 0; p < routerList.value.length; p++) {
      if (router.options.routes[i].meta.type === routerList.value[p].type) {
        let param = {title: router.options.routes[i].meta.title, path: router.options.routes[i].meta.path};
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
          path: router.options.routes[i].meta.path
        }]
      });
    }
  }
// 可以在这里或任何地方添加对 routerList 的输出或处理
  console.log('routerList.value', routerList.value);
}

</script>

<style scoped>
.header-nav {
  height: 80px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px;
  background-color: #333;
  color: #fff;
}

.logo img {
  height: 50px; /* 根据需要调整 */
}

.nav-links {
  list-style: none;
  display: flex;
  gap: 20px; /* 使用CSS Grid或Flexbox的gap属性来添加间距 */
}

.nav-links li {
  /* 样式可以根据需要调整 */
}

.search-box {
  display: flex;
  align-items: center;
  gap: 5px;
}

.search-box input,
.search-box button {
  /* 样式可以根据需要调整 */
}

.user-info {
  /* 样式可以根据需要调整 */
}
</style>
