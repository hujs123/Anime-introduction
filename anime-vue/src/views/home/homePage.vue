<template>
  <div class="self-home">
    <div class="self-home-inner">
      <div class="self-home-item" v-for="(item, index) in router.options.routes" :key="index" @click="goPage(item.name)" :style="{color: item.meta && item.meta.entry ? 'red' : 'rgba(0, 0, 0, 0.85)'}">
        {{item.meta ? item.meta.title : item.name}}
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted} from 'vue'
import { useRouter } from 'vue-router'
import {useStore} from 'vuex'

const router = useRouter() // 使用useRouter hook代替直接导入router实例
const store=useStore()  //

onMounted(()=>{
  init()
})

const init=()=>{
  console.log('store.state.count',store.state.count)
  store.commit('setters',10)
  console.log('store.state.count',store.state.count)
  // console.log(store.commit()mutations.setter())
  console.log(store.getters.doubleCount)
  console.log(router.options.routes)
}
/**
 * 路由跳转
 * @param val
 */
const goPage = (val) => {
  console.log('跳转界面',val)
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
