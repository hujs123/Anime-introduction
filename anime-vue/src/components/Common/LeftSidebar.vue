<template>
  <aside class="left-sidebar">
    <ul class="sidebar-links">
      <li v-for="item in menuItems" :key="item.id">
        <router-link :to="item.path">{{ item.name }}</router-link>
      </li>
    </ul>
    <!-- 可以添加更多元素，如搜索框、用户信息等 -->
  </aside>
</template>

<script setup>
import { onMounted,ref} from 'vue';

const menuItems=ref()
onMounted(() => {
  init();
});
const init=()=>{
  console.log('主界面')
  menuItems.value=[
    { id: 1, name: '仪表盘', path: '/' },
    { id: 2, name: '设置', path: '/settings' },
    { id: 3, name: '用户管理', path: '/users' }
  ]
}

</script>

<style scoped>
.left-sidebar {
  width: 250px; /* 侧边栏宽度 */
  background-color: #f8f9fa; /* 背景颜色，可根据需要调整 */
  position: fixed; /* 固定定位，使侧边栏在页面滚动时保持位置不变 */
  height: 100vh; /* 侧边栏高度与视窗高度相同 */
  padding: 20px;
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1); /* 阴影效果 */
}

.sidebar-links {
  list-style: none; /* 移除列表项前面的默认标记 */
  padding: 0;
}

.sidebar-links li {
  margin-bottom: 10px; /* 菜单项之间的间距 */
}

.sidebar-links li a {
  text-decoration: none; /* 移除链接的下划线 */
  color: #343a40; /* 链接文字颜色，可根据需要调整 */
  font-size: 16px; /* 链接文字大小 */
  display: block; /* 使链接占满整个列表项 */
  padding: 5px 10px; /* 链接的内边距 */
  border-radius: 4px; /* 链接的边框圆角 */
  transition: background-color 0.3s; /* 背景色过渡效果 */
}

.sidebar-links li a:hover {
  background-color: #e9ecef; /* 鼠标悬停时的背景色 */
}
</style>
