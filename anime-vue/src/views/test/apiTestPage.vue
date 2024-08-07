<template>
  <div class="self-home">
    <div class="self-home-inner">
      <div class="self-home-item" v-for="(item, index) in router.options.routes" :key="index"
           :style="{color: item.meta && item.meta.entry ? 'red' : 'rgba(0, 0, 0, 0.85)'}">
        {{ item.meta ? item.meta.title : item.name }}
      </div>
    </div>
  </div>
  <div>
    <div>
      <button @click="hello">hello</button>
      <button @click="getUsers">getUsers</button>
      <button @click="getAllUsers">getAllUsers</button>
      <button @click="queryData1">默认查询</button>
      <button @click="queryData2">未加密网关查询</button>
      <button @click="queryData3">处理网关查询</button>
      <button @click="jiami">加密处理</button>
      <button @click="jiemi">解密处理</button>


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
// import { encryptData,decryptData,selfencrypt } from '../../utils/encrypt'

const router = useRouter()
const tableData = ref([])
// const secretKey=ref('qazwsxedcrfv')
const encryptdate = ref('')
// const decryptdate=ref('')

//默认axios访问
const hello = async () => {
  try {
    console.log('queryData1')
    await axios1.get('http://localhost:8080/api/users/hello').then(res => {
      let response = res
      tableData.value = response
      console.log('response', response)
    })
  } catch (err) {
    console.warn(err.message)
  }
}

//默认axios访问
const getUsers = async () => {
// try {
  console.log('getUsers')
  // console.log('加密信息：',encryptData({ name: '岸本齐史' }, secretKey.value))
  //   await axios1.post('http://localhost:8080/api/users/getUsers',{
  //     encryptedData: selfencrypt({ name: '岸本齐史' }, secretKey.value)
  //   }).then(res => {
  //     let response = res
  //     tableData.value = response
  //     console.log('response', response)
  //   })
  // } catch (err) {
  //   console.warn(err.message)
  // }
}

//默认axios访问
const getAllUsers = async () => {
  try {
    console.log('queryData1')

    await axios1.post('http://localhost:8080/api/users/getAllUsers').then(res => {
      let response = res
      tableData.value = response
      console.log('response', response)
    })
  } catch (err) {
    console.warn(err.message)
  }
}


//默认axios访问
const queryData1 = async () => {
  try {
    console.log('queryData1')
    await axios1.get('https://mock.presstime.cn/mock/6523cdda2510f7001a40236f/hujs/datasource/search').then(res => {
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
const jiami = async () => {
  // let data='123456'
  console.log('加密数据', encryptdate.value)
  // encryptdate.value=selfencrypt({ name: data }, secretKey.value)
  console.log('encryptdate.value', encryptdate.value)
}

//解密
const jiemi = async () => {
  await axios1.get('https://image.anosu.top/pixiv/json?num=2&r18=18')
  console.log('加密数据', encryptdate.value)
  // decryptdate.value=decryptData(encryptdate.value, secretKey.value)
  // console.log('encryptdate.value',decryptdate.value)
}


//返回首页
const toHomePage = async () => {
  router.push('/') // 使用router实例的push方法进行导航
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
