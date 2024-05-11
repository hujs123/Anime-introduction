<template>
  <div>
    <div>
      <button @click="queryData1">默认查询</button>
      <button @click="queryData2">未加密网关查询</button>
      <button @click="queryData3">处理网关查询</button>
      <button @click="toHomePage">返回首页</button>
    </div>
  </div>
</template>
<script setup>
import {ref} from "vue";
import {useRouter} from 'vue-router'
import axios1 from "axios";
import axios2 from "@/utils/axiosService";
import {request} from "@/utils/request";

const router = useRouter()
const tableData = ref([])


//默认axios访问
const queryData1 = async () => {
  try {
    console.log('queryData1')
    await axios1.get('/hujs/datasource/search').then(res => {
      let response = res.data
      tableData.value = response.data
      console.log('response', response)
    })
  } catch (err) {
    console.warn(err.message)
  }
}
//axios拦截未加密访问
const queryData2 = async () => {
  try {
    console.log('queryData2')
    await axios2.get('/hujs/datasource/search').then(res => {
      let response = res.data
      tableData.value = response.data
      console.log('response', response)
    })
  } catch (err) {
    console.warn(err)
  }
}
//axios封装访问
const queryData3 = async () => {
  try {
    console.log('queryData3')
    await request('/hujs/datasource/search', '', 'GET').then(res => {
      let response = res.data
      tableData.value = response.data
      console.log('response', response)
    })
  } catch (err) {
    console.warn(err.message)
  }
}


//返回首页
const toHomePage = async () => {
  router.push('/') // 使用router实例的push方法进行导航
}


</script>
