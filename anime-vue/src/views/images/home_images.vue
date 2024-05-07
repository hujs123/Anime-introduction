<template>
  <div>
    <div>{{ message }}</div>
    <button @click="posttest">发送POST请求</button>
    <button @click="toHomePage">返回首页</button>
  </div>
</template>
<script setup>
import {ref} from "vue";
import axios from "/src/api/api";
import { useRouter } from 'vue-router' // 使用useRouter hook来获取router实例
const router = useRouter() // 使用useRouter hook代替直接导入router实例

// 读取env文件title给网页title
const VITE_APP_SSO_URL = process.env.VUE_APP_TITLE

const message = ref("图片页面");
const posttest = async () => {
  await axios.post('/api/users/getUsers').then(response=>{
    console.log(response.data); // 打印响应数据
  }).catch(err=>{
    console.warn(err.message||err.msg)
  })
}

const toHomePage = async () => {
  console.log(VITE_APP_SSO_URL)
  router.push('/') // 使用router实例的push方法进行导航
}
</script>
