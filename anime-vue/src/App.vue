<template>
  <div id="app">
    <a-layout>
      <a-layout-header style="background-color: #b2c6d2;">
        <header-nav @select-menu="handleSelectMenu" v-model:login-model="showModel"></header-nav>
      </a-layout-header>
      <a-layout>
        <a-layout-sider>
          <left-sidebar :menu-type="menuType"></left-sidebar>
        </a-layout-sider>
        <a-layout-content>
          <router-view></router-view>
          <LoginModel class="login-model" v-show="showModel" v-model:mshow-model="showModel"></LoginModel>
          <MaskLayer  v-model:visible-layer="showModel"></MaskLayer>
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
import {onMounted, ref, watch} from 'vue';
import HeaderNav from './components/Common/HeaderNav.vue';
import LeftSidebar from './components/Common/LeftSidebar.vue';
import {useStore} from "vuex";
import LoginModel from "@/components/Common/loginModel";
import MaskLayer from "@/components/Common/MaskLayer";

const store = useStore();// 使用 useStore 钩子获取 store 实例
const menuType = ref()
// 控制弹窗显示
const showModel = ref(false);

onMounted(() => {
  console.log('首页', showModel.value)
  init();
});

// 监听 prop 的变化以重新初始化 menuItems
watch(() => showModel.value, (newValue) => {
  console.log('首页', newValue)
});

const init = () => {
  handleSelectMenu(store.getters.getDefaultPathType)
}
//处理默认路由
const handleSelectMenu = (selectedItem) => {
  menuType.value = selectedItem;
}

// // 处理弹窗的确定按钮点击
// function handleOk() {
//   console.log('点击确认按钮')
//   showModel.value = false;
// }
//
// // 处理弹窗的取消按钮点击
// function handleCancel() {
//   console.log('点击取消按钮')
//   showModel.value = false;
// }


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

.login-model {
  position: fixed;
  left: 30%;
  top: 30%;
  width: 50%;
  height: 50%;
  z-index: 1002;
  text-align: center;

  background: #FFFFFF;
  border: 2px solid #CBCBCB;
  box-shadow: 0 0 80px 0 #00000033;
  border-radius: 4px;
}

</style>


