<template>
  <div id="app">
    <a-layout>
      <a-layout-header style="background-color: #b2c6d2">
        <header-nav @select-menu="handleSelectMenu"></header-nav>
      </a-layout-header>
      <a-layout>
        <a-layout-sider>
          <left-sidebar :menu-type="menuType"></left-sidebar>
        </a-layout-sider>
        <a-layout-content>
          <router-view></router-view>
        </a-layout-content>
      </a-layout>
    </a-layout>
  </div>
</template>

<!--<template>-->
<!--  <div id="app">-->
<!--    <nav>-->
<!--      &lt;!&ndash; 使用 router-link 组件来导航，to 属性指定路由路径 &ndash;&gt;-->
<!--      <router-link to="/"></router-link>-->
<!--    </nav>-->
<!--    <router-view/>-->
<!--  </div>-->
<!--</template>-->

<script setup>
import {onMounted, ref} from 'vue';
import HeaderNav from './components/Common/HeaderNav.vue';
import LeftSidebar from './components/Common/LeftSidebar.vue';

const menuType=ref()

onMounted(() => {
  initMap();
});
const initMap = () => {
}
const handleSelectMenu = (selectedItem) => {
  menuType.value =selectedItem;
}

</script>

<style scoped>
#app {
  height: 100vh; /* 使应用占据整个视口高度 */
  overflow: hidden; /* 防止滚动条出现 */
}

.ant-layout-header {
  padding: 0 20px; /* 添加一些内边距 */
  line-height: 64px; /* 调整头部高度 */
}

.ant-layout-sider {
  background: #fff; /* 设置侧边栏背景色 */
  border-right: 1px solid #e8e8e8; /* 添加右侧边框 */
}

.ant-layout-content {
  padding: 24px; /* 为内容区域添加内边距 */
  background: #f0f2f5; /* 设置内容区域背景色，使其与侧边栏和头部区分开 */
  min-height: calc(100vh - 128px); /* 确保内容区域有足够的高度 */
}
</style>


