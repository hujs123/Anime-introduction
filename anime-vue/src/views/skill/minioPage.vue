<template>
  <div>
    <div>{{ message }}</div>
    <div>
      <div>
        <input type="file" @change="changeFile"/>
        <button @click="uploadFile">上传</button>
      </div>
      <div>
        <button @click="toHomePage">返回首页</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref} from "vue";
import {useRouter} from 'vue-router'
import axios from "axios";

const router = useRouter() // 使用useRouter hook代替直接导入router实例
// 读取env文件title给网页title
const VITE_APP_SSO_URL = process.env.VUE_APP_TITLE
const message = ref("图片页面");

const selectedFile = ref(null)
const changeFile = (e) => {
  console.log('选择文件')
  selectedFile.value = e.target.files[0];
}

//上传文件
const uploadFile = async () => {
  console.log('上传文件')
  const formData = new FormData();
  formData.append('file', selectedFile.value);
  await axios.post('/api/skills/uploadFile', formData).then(response => {
    console.log(response.data); // 打印响应数据
  }).catch(err => {
    console.warn(err.message || err.msg)
  })
}

const toHomePage = async () => {
  console.log(VITE_APP_SSO_URL)
  router.push('/') // 使用router实例的push方法进行导航
}

</script>
