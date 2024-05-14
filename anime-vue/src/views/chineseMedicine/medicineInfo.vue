<template>
  <div>
    <div>{{ message }}</div>
    <button @click="toHomePage">返回首页</button>
  </div>
</template>
<script setup>
import {ref} from "vue";
import { useRouter } from 'vue-router' // 使用useRouter hook来获取router实例
const router = useRouter() // 使用useRouter hook代替直接导入router实例
// 读取env文件title给网页title
const VITE_APP_SSO_URL = process.env.VUE_APP_TITLE
const message = ref("中药大全");


const toHomePage = async () => {
  console.log(VITE_APP_SSO_URL)
  router.push('/') // 使用router实例的push方法进行导航
}




</script>
