<template>
  <div class="home-page">
    <header class="header">
      <h1>欢迎来到我们的网站</h1>
      <button class="self-home-item" v-for="(item, index) in router.options.routes" :key="index" @click="goPage(item.name)"
           :style="{color: item.meta && item.meta.entry ? 'red' : 'rgba(0, 0, 0, 0.85)'}">
        {{ item.meta ? item.meta.title : item.name }}
      </button>
      <br>
<!--            <nav>-->
<!--              <router-link to="/login">登录</router-link>-->
<!--              <router-link to="/register">注册</router-link>-->
<!--              <router-link to="/products">产品</router-link>-->
<!--              <router-link to="/news">新闻</router-link>-->
<!--              <router-link to="/contact">联系我们</router-link>-->
<!--            </nav>-->
    </header>

    <main class="main-content">
      <section class="featured-products">
        <h2>特色产品</h2>
        <!-- 假设使用v-for循环展示产品列表，这里简化 -->
<!--        <div class="product-item" v-for="product in products" :key="product.id">-->
<!--          <img :src="product.image" alt="Product Image">-->
<!--          <h3>{{ product.name }}</h3>-->
<!--          <p>{{ product.description }}</p>-->
<!--        </div>-->
      </section>

      <section class="news-update">
        <h2>最新资讯</h2>
        <!-- 假设的新闻列表 -->
<!--        <article v-for="news in newsList" :key="news.id">-->
<!--          <h3>{{ news.title }}</h3>-->
<!--          <p>{{ news.summary }}</p>-->
<!--          <router-link :to="'/news/' + news.id">阅读更多</router-link>-->
<!--        </article>-->
      </section>
    </main>

    <footer class="footer">
      <p>版权所有 &copy; 2023</p>
    </footer>
  </div>
</template>

<script setup>
import {onMounted} from 'vue'
import {useRouter} from 'vue-router'
import {useStore} from 'vuex'
import {useUserStore} from '../../store/index'

const userStore = useUserStore()

const router = useRouter() // 使用useRouter hook代替直接导入router实例
const store = useStore()  //

onMounted(() => {
  init()
})

const init = () => {
  console.log('store.state.count', store.state.count)
  store.commit('setters', 10)
  console.log('store.state.count', store.state.count)
  // console.log(store.commit()mutations.setter())
  console.log(store.getters.doubleCount)
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
