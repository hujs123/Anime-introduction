<template>
  <div id="app">
    <!-- 使用 Ant Design Vue 的布局组件 -->
    <a-layout>
      <a-layout-header style="background-color: #b2c6d2">
        <!-- 导航栏 -->
        <header-nav @select-menu="handleSelectMenu"></header-nav>
      </a-layout-header>
      <a-layout>
        <a-layout-sider width="250px">
          <!-- 侧边栏 -->
          <!-- 假设你已经将 Left.vue 重命名为 LeftSidebar.vue 并调整了导入 -->
          <left-sidebar :menu-items="menuType"></left-sidebar>
        </a-layout-sider>
        <a-layout-content>
          <!-- Body，使用路由视图显示不同页面 -->
          <router-view></router-view>
        </a-layout-content>
      </a-layout>
    </a-layout>
  </div>
</template>

<script setup>
import {onMounted,ref} from 'vue';
import HeaderNav from './components/Common/HeaderNav.vue';
import LeftSidebar from './components/Common/LeftSidebar.vue';

const menuType=ref()

onMounted(() => {
  initMap();
});
const initMap = () => {
  console.log('主界面')
}
const handleSelectMenu = (selectedItem) => {
  // 这里可以更新menuItems，或者做其他逻辑处理
  // 例如，这里我们简单地将selectedItem添加到menuItems中（实际使用中可能需要更复杂的逻辑）
  menuType.value = [selectedItem, ...menuType.value.filter(item => item.id !== selectedItem.id)];
}

</script>

<style>
/* CSS 样式... */
</style>
