<template>
  <div>
    <div>{{ message }}</div>
    <button @click="posttest">你好</button>
  </div>
</template>
<script setup>
import { ref } from "vue";
import axios from "axios";

const message = ref("Hello, Vue 3 with Composition API!");
const posttest=()=>{
  console.log('你好');
  // 假设这是一个GET请求，使用正确的请求方法
  axios.post('/api/users/getUsers') // 使用完整的URL
      .then(response => {
        console.log(response.data); // 打印响应数据
        // 你可以在这里处理数据，比如保存到组件的data中
      })
      .catch(error => {
        console.error('Error fetching data:', error);
        // 处理错误，给用户反馈等
      });
}
</script>
