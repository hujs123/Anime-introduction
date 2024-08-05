<template>
  <nav class="header-nav">
    <div class="logo">
      <img :src="logo" alt="Logo">
    </div>
    <ul class="nav-links">
      <li>
        <router-link to="/">首页</router-link>
      </li>
      <li>
        <router-link to="/about">关于我们</router-link>
      </li>
      <li>
        <router-link to="/services">服务</router-link>
      </li>
      <li>
        <router-link to="/contact">联系我们</router-link>
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
import {onMounted,ref} from 'vue';
import logo from '@/assets/images/logo.png';
const isLoggedIn = ref()
const userName = ref()
// const logo = ref('@/assets/images/logo.png')

onMounted(() => {
  init();
});
const init = () => {
  console.log('主界面')
  isLoggedIn.value = false
  userName.value = '访客'

}

</script>

<style scoped>
.header-nav {
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
