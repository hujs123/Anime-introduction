<template>
  <div>
    {{message}}
    <div>
      <button @click="setStoreStateCount()">store赋值</button>
      <button @click="doubleStoreStateCount()">异步处理赋值二倍</button>
      <button @click="decrementIfOdd">actions-decrementIfOdd方法</button>
      <button @click="toHomePage">返回首页</button>
    </div>
  </div>
</template>
<script setup>
import {ref} from "vue";
import {useRouter} from 'vue-router'
import {useStore} from "vuex";

const router = useRouter()
const message = ref('测试')
const store=useStore();// 使用 useStore 钩子获取 store 实例

//默认axios访问
const setStoreStateCount = async () => {
  console.log('store.state.count', store.state.count)
  store.commit('setCount', 10) // 调用 mutation,赋值方法
  console.log('store.state.count', store.state.count)
}
//axios拦截未加密访问
const doubleStoreStateCount = async () => {
  let p=store.getters.getState(2)
  console.log(p)
}
//actions，decrementIfOdd方法
const decrementIfOdd = async () => {
  store.dispatch('decrementIfOdd').then(() => {
    console.log('store.state.count after decrementIfOdd', store.state.count)
  });
}


//返回首页
const toHomePage = async () => {
  router.push('/') // 使用router实例的push方法进行导航
}


</script>
