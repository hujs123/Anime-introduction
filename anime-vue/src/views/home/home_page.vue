<template>
  <div>
    <div>{{ message }}</div>
    <button @click="toHomeImages">前往图片界面</button>
    <button @click="toMaintenance">前往指标界面</button>
    <button @click="totest2">前往测试界面</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router' // 使用useRouter hook来获取router实例
const router = useRouter() // 使用useRouter hook代替直接导入router实例
const message = ref("首页")


const toHomeImages = () => {
  router.push('/home-images') // 使用router实例的push方法进行导航
}

const toMaintenance = () => {
  router.push('/data-source-maintenance') // 使用router实例的push方法进行导航
}
const totest2 = () => {
  router.push('/test-vue') // 使用router实例的push方法进行导航
}
</script>
