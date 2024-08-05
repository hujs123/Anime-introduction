<!--<template>-->
<!--  <div id="app">-->
<!--    <nav>-->
<!--      &lt;!&ndash; 使用 router-link 组件来导航，to 属性指定路由路径 &ndash;&gt;-->
<!--      <router-link to="/"></router-link>-->
<!--    </nav>-->
<!--    <router-view/>-->
<!--  </div>-->
<!--</template>-->
<template>
  <div id="app">
    <!-- 使用 Ant Design Vue 的布局组件 -->
    <a-layout>
      <a-layout-header>
        <!-- 导航栏 -->
        <header-nav></header-nav>
      </a-layout-header>
      <a-layout>
        <a-layout-sider width="250px">
          <!-- 侧边栏 -->
          <!-- 假设你已经将 Left.vue 重命名为 LeftSidebar.vue 并调整了导入 -->
          <left-sidebar></left-sidebar>
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
import { onMounted } from 'vue';
import HeaderNav from './components/Common/HeaderNav.vue';
import LeftSidebar from './components/Common/LeftSidebar.vue';

onMounted(() => {
  initMap();
});
const initMap=()=>{
  console.log('主界面')
}

</script>

<style>
/* CSS 样式... */
</style>
