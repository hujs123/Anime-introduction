<template>
  <button class="self-home-item" v-for="(item, index) in router.options.routes" :key="index" @click="goPage(item.name)"
          :style="{color: item.meta && item.meta.entry ? 'red' : 'rgba(0, 0, 0, 0.85)'}">
    {{ item.meta ? item.meta.title : item.name }}
  </button>
<!--  <a-layout style="min-height: 100vh">-->
<!--    <a-layout-sider v-model:collapsed="collapsed" collapsible>-->
<!--      <div class="logo" />-->
<!--      <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">-->
<!--        <a-menu-item >-->
<!--          <pie-chart-outlined />-->
<!--          <span>首页</span>-->
<!--        </a-menu-item>-->

<!--        <a-sub-menu>-->
<!--          <template #title>-->
<!--            <span>-->
<!--              <user-outlined />-->
<!--              <span>功能模块</span>-->
<!--            </span>-->
<!--          </template>-->
<!--          <a-menu-item  v-for="(item, index) in router.options.routes" :key="index" @click="goPage(item.name)">{{ item.meta ? item.meta.title : item.name }} </a-menu-item>-->
<!--        </a-sub-menu>-->

<!--        <a-sub-menu>-->
<!--          <template #title>-->
<!--            <span>-->
<!--              <team-outlined />-->
<!--              <span>第三方接口</span>-->
<!--            </span>-->
<!--          </template>-->
<!--          <a-menu-item key="6">Team 1</a-menu-item>-->
<!--        </a-sub-menu>-->

<!--        <a-sub-menu>-->
<!--          <template #title>-->
<!--            <span>-->
<!--              <DesktopOutlined />-->
<!--              <span>测试页面</span>-->
<!--            </span>-->
<!--          </template>-->
<!--          <a-menu-item key="6">Team 1</a-menu-item>-->
<!--        </a-sub-menu>-->

<!--        <a-sub-menu>-->
<!--          <template #title>-->
<!--            <span>-->
<!--              <FileOutlined />-->
<!--              <span>路由管理</span>-->
<!--            </span>-->
<!--          </template>-->
<!--          <a-menu-item key="6">Team 1</a-menu-item>-->
<!--        </a-sub-menu>-->

<!--        <a-sub-menu>-->
<!--          <template #title>-->
<!--            <span>-->
<!--              <FileOutlined />-->
<!--              <span>其他</span>-->
<!--            </span>-->
<!--          </template>-->
<!--          <a-menu-item key="6">Team 1</a-menu-item>-->
<!--        </a-sub-menu>-->
<!--      </a-menu>-->
<!--    </a-layout-sider>-->
<!--  </a-layout>-->
</template>

<script setup>
import {onMounted} from 'vue'
// import {ref} from 'vue'
import {useRouter} from 'vue-router'
import {useStore} from 'vuex'
// import {  PieChartOutlined,  DesktopOutlined,  UserOutlined,  TeamOutlined,  FileOutlined,} from '@ant-design/icons-vue';
import {useUserStore} from '../../store/index'

const userStore = useUserStore()

const router = useRouter() // 使用useRouter hook代替直接导入router实例
const store = useStore()  //

// const collapsed = ref(false);
// const selectedKeys = ref (['1']);

onMounted(() => {
  init()
})

const init = () => {
  console.log('store.state.count', store.state.count)
  console.log('store.state.count', store.state.count)
  console.log("router.options.routes",router.options.routes)
  for(let i=0;i<router.options.routes.length;i++){
    let p=router.options.routes[i]
    if(p.meta.entry){
      console.log(p.meta.title)
    }
  }
}
/**
 * 路由跳转
 * @param val
 */
const goPage = (val) => {
  console.log('userStore', userStore.getStateName)
  console.log('跳转界面', val)
  router.push({name: val})
}
</script>

<style scoped lang="less">
.self-home {
  width: 100%;
  height: 100%;
  overflow: auto;
}

.self-home-inner {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  align-items: flex-start;

  .self-home-item {
    width: 100px;
    font-size: 16px;
    padding: 10px 12px;
    margin: 0 10px 10px 0;
    border: 1px solid #999999;
    text-align: center;
  }
}
</style>
