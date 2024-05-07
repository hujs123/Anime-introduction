<!-- App.vue -->
<template>
  <div id="app">
    <nav>
      <!-- 使用 router-link 组件来导航，to 属性指定路由路径 -->
      <router-link to="/"></router-link>
      <!-- 假设你有一个指向 images 的路由 -->
<!--      <router-link to="/home-images"></router-link>-->
      <!-- 其他导航链接... -->
    </nav>

    <!-- 使用 router-view 组件来渲染当前路由对应的组件 -->
    <router-view/>

    <!-- 其他全局UI元素... -->
  </div>
</template>

<script>
export default {
  name: 'App'
  // 不需要在这里导入和注册 HomePage 组件，因为 Vue Router 会处理它
}
</script>

<style>
/* CSS 样式... */
</style>
