<template>
  <div>
    {{message}}
    <div>
      <button @click="getStateCount()">pinia获取值</button>
      <button @click="setStateCount()">赋值</button>
      <button @click="clearState()">清空state</button>
      <button @click="toHomePage">返回首页</button>
    </div>
  </div>
</template>
<script setup>
import {ref} from "vue";
import {useRouter} from 'vue-router'
import {useUserStore} from '../../store/index'
const userStore = useUserStore()

const router = useRouter()
const message = ref('测试')

//默认axios访问
const getStateCount = async () => {
  console.log('userStore.getStateName',userStore.getStateName)
  console.log('userStore.fullName',userStore.fullName)
}
//axios拦截未加密访问
const setStateCount = async () => {
  console.log('userStore.getStateName',userStore.getStateName)
  userStore.setName('luhf')
  console.log('userStore.getStateName',userStore.getStateName)
}
//actions，decrementIfOdd方法
const clearState = async () => {
  console.log('userStore.getStateName',userStore.getStateName)
  userStore.setName('')
  console.log('userStore.getStateName',userStore.getStateName)
}


//返回首页
const toHomePage = async () => {
  router.push('/') // 使用router实例的push方法进行导航
}


</script>
